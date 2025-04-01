#include "sum_zero.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

static clock_t start_time;
static bool timeout_reached = false;

void checkTimeout(int timeout_ms) {
    if (timeout_ms > 0 && (clock() - start_time) * 1000 / CLOCKS_PER_SEC >= timeout_ms) {
        timeout_reached = true;
    }
}

bool findSubsets(int* numbers, int n, Subset* result, int* result_count, 
                SearchMode mode, int heuristic, bool verbose, 
                int timeout_ms, int* progress) {
    start_time = clock();
    timeout_reached = false;
    *result_count = 0;
    *progress = 0;

    long total_subsets = (1L << n) - 1;
    long processed = 0;

    for (long mask = 1; mask <= total_subsets && !timeout_reached; mask++) {
        int sum = 0;
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            if (mask & (1L << i)) {
                sum += numbers[i];
                count++;
            }
        }

        if (sum == 0) {
            result[*result_count].elements = malloc(count * sizeof(int));
            result[*result_count].size = count;
            
            int pos = 0;
            for (int i = 0; i < n; i++) {
                if (mask & (1L << i)) {
                    result[*result_count].elements[pos++] = numbers[i];
                }
            }
            
            (*result_count)++;
            
            if (mode == FIRST_MATCH) {
                *progress = 100;
                return true;
            }
        }

        processed++;
        *progress = (processed * 100) / total_subsets;
        checkTimeout(timeout_ms);
    }

    *progress = 100;
    return (*result_count > 0);
}

void printSubsets(Subset* subsets, int count) {
    if (count == 0) {
        printf("No subsets sum to 0.\n");
        return;
    }

    printf("Found %d subset%s that sum to 0:\n", count, count > 1 ? "s" : "");
    for (int i = 0; i < count; i++) {
        printf("  Subset %d: { ", i+1);
        for (int j = 0; j < subsets[i].size; j++) {
            printf("%d", subsets[i].elements[j]);
            if (j < subsets[i].size - 1) printf(", ");
        }
        printf(" }\n");
    }
}

void handleInput(int argc, char* argv[], int* numbers, int* n, 
                SearchMode* mode, int* heuristic, bool* verbose, 
                int* timeout, char** filename) {
    *mode = FIRST_MATCH;
    *heuristic = 0;
    *verbose = false;
    *timeout = 0;
    *filename = NULL;
    *n = 0;

    for (int i = 1; i < argc; i++) {
        if (strcmp(argv[i], "-mode") == 0 && i+1 < argc) {
            i++;
            if (strcmp(argv[i], "fullSearch") == 0) {
                *mode = FULL_SEARCH;
            } else if (strcmp(argv[i], "firstMatchSearch") == 0) {
                *mode = FIRST_MATCH;
            } else if (strcmp(argv[i], "heuristic") == 0 && i+1 < argc) {
                *mode = HEURISTIC;
                *heuristic = atoi(argv[++i]);
            }
        } else if (strcmp(argv[i], "-timeout") == 0 && i+1 < argc) {
            *timeout = atoi(argv[++i]);
        } else if (strcmp(argv[i], "-verbose") == 0) {
            *verbose = true;
        } else if (strcmp(argv[i], "-") == 0) {
            // Read from stdin
            if (i+1 < argc) {
                *filename = argv[++i];
            }
        } else if (*n < MAX_SIZE) {
            numbers[(*n)++] = atoi(argv[i]);
        }
    }
}

void printUsage(char* program_name) {
    fprintf(stderr, "Usage: %s [-|filename] [-mode [fullSearch|firstMatchSearch|heuristic heuristic_number]] [-timeout milliseconds] [-verbose]\n", 
            program_name);
}
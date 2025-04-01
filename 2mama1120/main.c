#include "sum_zero.h"
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char* argv[]) {
    int numbers[MAX_SIZE];
    int n = 0;
    SearchMode mode = FIRST_MATCH;
    int heuristic = 0;
    bool verbose = false;
    int timeout_ms = 0;
    char* filename = NULL;
    
    handleInput(argc, argv, numbers, &n, &mode, &heuristic, &verbose, &timeout_ms, &filename);
    
    if (n == 0) {
        fprintf(stderr, "Error: No numbers provided.\n");
        printUsage(argv[0]);
        return 1;
    }

    Subset results[MAX_SIZE];
    int result_count = 0;
    int progress = 0;
    
    bool found = findSubsets(numbers, n, results, &result_count, 
                           mode, heuristic, verbose, 
                           timeout_ms, &progress);
    
    if (timeout_ms > 0 && progress < 100) {
        printf("Timeout reached. Progress: %d%%\n", progress);
    }

    if (found) {
        printSubsets(results, result_count);
    } else if (progress == 100) {
        printf("No subsets sum to 0.\n");
    }

    for (int i = 0; i < result_count; i++) {
        free(results[i].elements);
    }

    return found ? 0 : 1;
}
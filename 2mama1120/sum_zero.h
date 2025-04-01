#ifndef SUBSET_SUM_H
#define SUBSET_SUM_H

#include <stdbool.h>
#include <time.h>

#define MAX_SIZE 100

typedef struct {
    int* elements;
    int size;
} Subset;

typedef enum {
    FULL_SEARCH,
    FIRST_MATCH,
    HEURISTIC
} SearchMode;

// Core functions
bool findSubsets(int* numbers, int n, Subset* result, int* result_count, 
                SearchMode mode, int heuristic, bool verbose, 
                int timeout_ms, int* progress);
void printSubsets(Subset* subsets, int count);

// Utility functions
void handleInput(int argc, char* argv[], int* numbers, int* n, 
                SearchMode* mode, int* heuristic, bool* verbose, 
                int* timeout, char** filename);
void printUsage(char* program_name);

#endif
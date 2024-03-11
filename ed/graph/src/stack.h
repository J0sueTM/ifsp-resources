#ifndef STACK_H
#define STACK_H

#include "./graph.h"

#include <stdlib.h>
#include <stdbool.h>

typedef struct StackNode StackNode;
typedef struct StackNode {
  Edge *edge;
  StackNode *next;
} StackNode;

typedef struct Stack {
  StackNode *root;
} Stack;

void end_stack(Stack *s);
void end_stack_node(StackNode *sn, bool should_recurse);
void push_stack(Stack *s, Edge *edge);
Edge *pop_stack(Stack *s);

#endif

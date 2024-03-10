#ifndef NODE_H
#define NODE_H

#include <stdio.h>
#include <stdlib.h>

#define MAX_NODE_CHILDREN 255

typedef struct Node Node;
typedef struct Node {
  int data, children_count;
  Node *children[MAX_NODE_CHILDREN];
} Node;

Node *create_node(int data, Node *parent);
void delete_node(Node *node);

#endif

#include "./node.h"

Node *create_node(int data, Node *parent) {
  Node *node = (Node *)malloc(sizeof(Node));
  if (!node) {
    fprintf(stderr, "failed to init node %s\n", data);

    return NULL;
  }

  node->data = data;
  node->children_count = 0;
  if (parent) {
    if ((parent->children_count + 1) > MAX_NODE_CHILDREN) {
      fprintf(stderr, "array overflow!\n");

      return NULL;
    }
    parent->children[parent->children_count] = node;
    ++parent->children_count;
  }

  return node;
}

void delete_node(Node *node) {
  if (!node) {
    return;
  }

  for (int i = 0; i < node->children_count; ++i) {
    delete_node(node->children[i]);
  }

  free(node);
}

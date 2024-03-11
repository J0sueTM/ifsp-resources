#include "./stack.h"

void end_stack(Stack *s) {
  if (!s) {
    return;
  }

  end_stack_node(s->root, true);
}

void end_stack_node(StackNode *sn, bool should_recurse) {
  if (!sn) {
    return;
  }

  if (should_recurse) {
    end_stack_node(sn->next, true);
  }

  free(sn);
}

void push_stack(Stack *s, Edge *edge) {
  StackNode *new_snode = (StackNode *)malloc(sizeof(StackNode));
  if (!new_snode) {
    fprintf(stderr, "failed to allocate new stack edge\n");

    return;
  }
  new_snode->edge = edge;
  new_snode->next = s->root;
  s->root = new_snode;
}

Edge *pop_stack(Stack *s) {
  if (!s) {
    return NULL;
  } else if (!s->root) {
    return NULL;
  }

  Edge *e = s->root->edge;
  StackNode *next_snode = s->root->next;

  end_stack_node(s->root, false);

  s->root = next_snode;

  return e;
}

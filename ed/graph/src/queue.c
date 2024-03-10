#include "queue.h"

void end_queue(Queue *q) {
  if (!q) {
    return;
  }

  while (q->start) {
    dequeue_node(q);
  }
}

void enqueue_node(Queue *q, Node *n) {
  QueueNode *new_qnode = (QueueNode *)malloc(sizeof(QueueNode));
  if (!new_qnode) {
    fprintf(stderr, "failed to allocate new queue node\n");

    return;
  }
  new_qnode->node = n;

  if (!q->start) {
    q->start = new_qnode;
  } else {
    QueueNode *cur_qnode = q->start;
    while (cur_qnode->next) {
      cur_qnode = cur_qnode->next;
    }

    cur_qnode->next = new_qnode;
  }

  q->end = new_qnode;
}

Node *dequeue_node(Queue *q) {
  if (!q) {
    return NULL;
  } else if (!q->start) {
    return NULL;
  }
  
  Node *node = q->start->node;
  QueueNode *next_qnode = q->start->next;
  free(q->start);
  q->start = next_qnode;

  return node;
}

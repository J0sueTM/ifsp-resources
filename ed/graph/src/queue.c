#include "queue.h"

void end_queue(Queue *q) {
  if (!q) {
    return;
  }

  while (q->start) {
    dequeue(q);
  }
}

void enqueue(Queue *q, Edge *e) {
  QueueNode *new_qnode = (QueueNode *)malloc(sizeof(QueueNode));
  if (!new_qnode) {
    fprintf(stderr, "failed to allocate new queue edge\n");

    return;
  }
  new_qnode->edge = e;

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

Edge *dequeue(Queue *q) {
  if (!q) {
    return NULL;
  } else if (!q->start) {
    return NULL;
  }
  
  Edge *edge = q->start->edge;
  QueueNode *next_qnode = q->start->next;
  free(q->start);
  q->start = next_qnode;

  return edge;
}

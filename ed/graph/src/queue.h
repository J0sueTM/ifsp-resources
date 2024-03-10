#ifndef QUEUE_H
#define QUEUE_H

#include "./node.h"

#include <stdlib.h>

typedef struct QueueNode QueueNode;
typedef struct QueueNode {
  Node *node;
  QueueNode *next;
} QueueNode;

typedef struct Queue {
  QueueNode *start, *end;
} Queue;

void end_queue(Queue *q);
void end_queue_node(QueueNode *qn);
void enqueue_node(Queue *q, Node *n);
Node *dequeue_node(Queue *q);

#endif

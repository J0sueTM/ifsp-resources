#ifndef QUEUE_H
#define QUEUE_H

#include "./graph.h"

#include <stdlib.h>

typedef struct QueueNode QueueNode;
typedef struct QueueNode {
  Edge *edge;
  QueueNode *next;
} QueueNode;

typedef struct Queue {
  QueueNode *start, *end;
} Queue;

void end_queue(Queue *q);
void enqueue(Queue *q, Edge *e);
Edge *dequeue(Queue *q);

#endif

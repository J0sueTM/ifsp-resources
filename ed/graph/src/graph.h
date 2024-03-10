#ifndef GRAPH_H
#define GRAPH_H

#include <stdio.h>
#include <stdlib.h>

#define MAX_EDGE_NGBS 255

typedef struct Edge Edge;
typedef struct Edge {
  int data, neighbour_count;
  Edge *neighbours[MAX_EDGE_NGBS];
} Edge;

Edge *create_edge(int data, Edge *neighbour);
void connect_edges(Edge *e, Edge *neighbour);
void delete_edge(Edge *e);

#endif

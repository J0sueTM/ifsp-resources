#include "./graph.h"

Edge *create_edge(int data, Edge *neighbour) {
  Edge *edge = (Edge *)malloc(sizeof(Edge));
  if (!edge) {
    fprintf(stderr, "failed to init edge %s\n", data);

    return NULL;
  }

  edge->data = data;
  edge->neighbour_count = 0;
  if (neighbour) {
    if ((neighbour->neighbour_count + 1) > MAX_EDGE_NGBS) {
      fprintf(stderr, "array overflow!\n");

      return NULL;
    }
    neighbour->neighbours[neighbour->neighbour_count] = edge;
    ++neighbour->neighbour_count;
  }

  return edge;
}

void connect_edges(Edge *e, Edge *neighbour) {
  if (!e || !neighbour) {
    return;
  } else if ((e->neighbour_count + 1) > MAX_EDGE_NGBS) {
    fprintf(stderr, "array overflow!\n");
    
    return;
  }
  
  e->neighbours[e->neighbour_count] = neighbour;
  ++e->neighbour_count;
}

void delete_edge(Edge *edge) {
  if (!edge) {
    return;
  }

  for (int i = 0; i < edge->neighbour_count; ++i) {
    delete_edge(edge->neighbours[i]);
  }

  free(edge);
}

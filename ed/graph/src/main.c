#include "./graph.h"
#include "./queue.h"

#include <stdbool.h>
#include <string.h>

#define MAX_VISITED_EDGES 255

typedef enum SearchMethod {
  SIMPLE, BFS, DFS
} SearchMethod;

Edge *populate_graph();
void print_graph_simple (Edge *e);
void print_graph_dfs(Edge *e /* TODO Stack *s */);
void print_graph_bfs(Queue *q, Edge **visited, int visited_count);

int main(int argc, char *argv[]) {
  SearchMethod sm = SIMPLE;
  if (argc > 1) {
    if (!strcmp(argv[1], "--dfs")) {
      sm = DFS;
    } else if (!strcmp(argv[1], "--bfs")) {
      sm = BFS;
    }
  }

  Edge *root = populate_graph();
  
  switch (sm) {
  case DFS:
    print_graph_dfs(root);

    break;
  case BFS: {
    Queue q;
    q.start = NULL;
    q.end = NULL;
    enqueue(&q, root);
    Edge *visited_edges[MAX_VISITED_EDGES];
    memset(visited_edges, 0x00, MAX_VISITED_EDGES * sizeof(Edge *));
    visited_edges[0] = root;
    
    print_graph_bfs(&q, visited_edges, 1);
  } break;
  case SIMPLE:
  default:
    print_graph_simple(root);

    break;
  }

  return 0;
}

Edge *populate_graph() {
  /*
    how the graph should look like after populated:

        h30<--b10<--[a0]->c34
             /  \   / \    ^
            v    v v   v  /
          f50<---d40   e80
            \     \     /
             \     \   /
              \     v v
               ---->g90

    [] highlights the root
   */
  Edge *a = create_edge(0, NULL);
  Edge *b = create_edge(10, a);
  Edge *c = create_edge(34, a);
  Edge *d = create_edge(40, a);
  Edge *e = create_edge(80, a);
  Edge *f = create_edge(50, b);
  Edge *g = create_edge(90, d);
  create_edge(30, b);

  connect_edges(b, d);
  connect_edges(d, f);
  connect_edges(e, c);
  connect_edges(f, g);
  connect_edges(e, g);

  return a;
}

/*
  NOTE: This function is supposed to repeat edges, its here
  for debugging purposes only!
 */
void print_graph_simple(Edge *e) {
  if (!e) {
    return;
  }

  for (int i = 0; i < e->neighbour_count; ++i) {
    print_graph_simple(e->neighbours[i]);
  }

  printf("%d ", e->data);
}

void print_graph_dfs(Edge *e /* TODO Stack *s */) {
  fprintf(stderr, "IMPLEMENT ME");
}

void print_graph_bfs(Queue *q, Edge **visited, int visited_count) {
  Edge *e = dequeue(q);
  if (!e) {
    return;
  }

  printf("%d ", e->data);

  int new_visited_count = visited_count;
  for (int i = 0; i < e->neighbour_count; ++i) {
    Edge *ne = e->neighbours[i];
    bool ne_visited = false;
    for (Edge **tmpe = visited; *tmpe != NULL; ++tmpe) {
      if ((*tmpe)->data == ne->data) {
        ne_visited = true;

        break;
      }
    }

    if (!ne_visited) {
      enqueue(q, ne);
      visited[new_visited_count - 1] = ne;
      ++new_visited_count;
    }
  }

  print_graph_bfs(q, visited, new_visited_count);
}

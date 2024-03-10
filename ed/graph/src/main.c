#include "./graph.h"
#include "./queue.h"

#include <string.h>

typedef enum SearchMethod {
  SIMPLE, BFS, DFS
} SearchMethod;

Edge *populate_graph();
void print_graph_simple (Edge *e);
void print_graph_dfs(Edge *e /* TODO Stack *s */);
void print_graph_bfs(Edge *e, Queue *q);

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
    print_graph_bfs(root, &q);
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

void print_graph_bfs(Edge *e, Queue *q) {
  // Node *pnode = dequeue_node(q);
  // if (!pnode) {
  //   return;
  // }
  //  
  // printf("\n%d", node->data);
  //  
  // for (int i = 0; i < node->children_count; ++i) {
  //   enqueue_node(q, node->children[i]);
  // }
}

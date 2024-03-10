#include "./node.h"
#include "./queue.h"

#include <string.h>

typedef enum SearchMethod {
  SIMPLE, BFS, DFS
} SearchMethod;

Node *populate_graph();
void print_graph_simple(Node *node);
void print_graph_dfs(Node *node /* TODO Stack *s */);
void print_graph_bfs(Node *node, Queue *q);

int main(int argc, char *argv[]) {
  SearchMethod sm = SIMPLE;
  if (argc > 0) {
    if (!strcmp(argv[0], "--bfs")) {
      sm = BFS;
    } else if (!strcmp(argv[0], "--dfs")) {
      sm = DFS;
    }
  }

  Node *root = populate_graph();

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

  // Queue q;
  // enqueue_node(&q, root);
  // enqueue_node(&q, na);
  //  
  // printf("\n%d\n", dequeue_node(&q)->data); // 0
  // enqueue_node(&q, nba);
  // printf("%d\n", dequeue_node(&q)->data); // 10
  // printf("%d\n", dequeue_node(&q)->data); // 50
  // end_queue(&q);
  
  return 0;
}

Node *populate_graph() {
  /*
    how the graph should look like after populated:

              a0
           _________
          /    |    \
        b10   c40   d80
        /     /  \
      e30   f50  g90

      simple: 30 10 50 90 40 80 0 
   */
  Node *a = create_node(0, NULL);
  Node *b = create_node(10, a);
  Node *c = create_node(40, a);
  create_node(80, a);
  create_node(30, b);
  create_node(50, c);
  create_node(90, c);

  return a;
}

void print_graph_simple(Node *node) {
  if (!node) {
    return;
  }

  for (int i = 0; i < node->children_count; ++i) {
    print_graph_simple(node->children[i]);
  }

  printf("%d ", node->data);
}

void print_graph_dfs(Node *node /* TODO Stack *s */) {
  fprintf(stderr, "IMPLEMENT ME");
}

void print_graph_bfs(Node *node, Queue *q) {
  fprintf(stderr, "IMPLEMENT ME");
}

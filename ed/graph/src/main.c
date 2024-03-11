#include "./graph.h"
#include "./queue.h"
#include "./stack.h"

#include <stdbool.h>
#include <string.h>

#define MAX_VISITED_EDGES 255

typedef enum SearchMethod {
  SIMPLE, BFS, DFS
} SearchMethod;

Edge *populate_graph();
bool has_edge_been_visited(Edge **visited, Edge *e);
void print_graph_simple (Edge *e);
void print_graph_dfs(Stack *s, Edge **visited, int visited_count);
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

  Edge *visited_edges[MAX_VISITED_EDGES];
  memset(visited_edges, 0x00, MAX_VISITED_EDGES * sizeof(Edge *));
  visited_edges[0] = root;

  switch (sm) {
  case DFS: {
    Stack s;
    s.root = NULL;
    push_stack(&s, root);

    print_graph_dfs(&s, visited_edges, 1);

    end_stack(&s);
  } break;
  case BFS: {
    Queue q;
    q.start = NULL;
    q.end = NULL;
    enqueue(&q, root);
    
    print_graph_bfs(&q, visited_edges, 1);

    end_queue(&q);
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

bool has_edge_been_visited(Edge **visited, Edge *e) {
  for (Edge **tmpe = visited; *tmpe != NULL; ++tmpe) {
    if ((*tmpe)->data == e->data) {
      return true;
    }
  }

  return false;
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

void print_graph_dfs(
  Stack *s,
  Edge **visited,
  int visited_count
) {
  Edge *e = pop_stack(s);
  if (!e) {
    return;
  }

  printf("%d ", e->data);

  int new_visited_count = visited_count;
  for (int i = 0; i < e->neighbour_count; ++i) {
    Edge *ne = e->neighbours[i];
    bool ne_visited = has_edge_been_visited(visited, ne);

    if (!ne_visited) {
      push_stack(s, ne);
      visited[new_visited_count - 1] = ne;
      ++new_visited_count;
    }
  }

  print_graph_dfs(s, visited, new_visited_count);
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
    bool ne_visited = has_edge_been_visited(visited, ne);

    if (!ne_visited) {
      enqueue(q, ne);
      visited[new_visited_count - 1] = ne;
      ++new_visited_count;
    }
  }

  print_graph_bfs(q, visited, new_visited_count);
}

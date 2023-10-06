#include "fila.h"

int main(void) {
  Fila fila(50);
  int op;
  do {
    op = fila.menu();
  } while (op != 0);

  return 0;
}

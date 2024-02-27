#include <stdlib.h>
#include <iostream>

struct No {
  int dado;
  No *prox;
};

class Fila {
public:
  unsigned int cap, qtt;
  No *ini, *fim;

  Fila(unsigned int cap);
  int menu();
  No *inserir(int dado);
  void listar(bool reverter);
};

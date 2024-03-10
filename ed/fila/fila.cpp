#include "fila.h"

Fila::Fila(unsigned int cap) {
  this->cap = cap;
  this->qtt = 0;
  this->ini = (No *)malloc(sizeof(Fila));
  this->ini->prox = NULL;
}

No *Fila::inserir(int dado) {
  if (this->qtt >= this->cap) {
    std::cout << "overflow";

    return NULL;
  }

  No *no = (No *)malloc(sizeof(Fila));
  no->dado = dado;
  no->prox = NULL;

  No *cur = this->ini;
  while (cur->prox != NULL) {
    cur = cur->prox;
  }

  cur->prox = no;
  this->fim = no;
  ++this->qtt;

  return no;
}

int Fila::menu() {
  std::cout << "\nOperacao: \n"
            << "0. sair\n"
            << "1. inserir\n"
            << "2. remover\n"
            << "3. pesquisar\n"
            << "4. listar\n";

  int op;
  std::cin >> op;
  switch (op) {
    case 1: {
      std::cout << "insira o valor: ";
      unsigned int dado;
      std::cin >> dado;

      this->inserir(dado);
    } break;
    case 2:
      break;
    case 3:
      break;
    case 4:
      this->listar(false);
      break;
    default:
      break;
  }

  return op;
}

void Fila::listar(bool reverter) {
  No *cur = this->ini->prox;
  while (cur != NULL) {
    std::cout << cur->dado;
    if (cur->prox != NULL) {
      std::cout << ", ";
    } else {
      std::cout << ".\n";
    }

    cur = cur->prox;
  }
}

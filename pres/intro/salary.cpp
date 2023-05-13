#include <iostream>

int main(void) {
  int c;
  float salario;
  float aumento[5] = {0.5f, 0.35f, 0.2f, 0.1f, 0.f};

  std::cin >> c >> salario;
  salario += salario * aumento[c - 1];
  // NOTE(J0sueTM): possible memory leak when using c
  // to retrieve aumento. Maybe use std::clamp ?
  
  std::cout << salario << std::endl;
}

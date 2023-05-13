#include <iostream>

int main(void) {
  int n, salario;

  std::cout << "menu de opcoes:\n1. Imposto\t 2. Novo salario\t 3. Classificacao\n";
  std::cin >> n >> salario;

  switch (n) {
  case 2: {
    float a;
    if (salario < 450.f)
      a = 100.f;
    else if (salario >= 450.f && salario < 750.f)
      a = 75.f;
    else if (salario >= 750.f && salario < 1500.f)
      a = 50.f;
    else
      a = 25.f;

    salario += a;
    std::cout << salario;
  } break;
  case 3: {
    std::string r = (salario >= 700.f) ? "Bem" : "Mal";
    std::cout << r << " remunerado";
  } break;
  default: {
    float pi;
    if (salario < 500.f)
      pi = 0.05f;
    else if (salario >= 500.f && salario <= 850.f)
      pi = 0.1f;
    else
      pi = 0.15f;

    salario += salario * pi;
    std::cout << salario;

  } break;
  }

  std::cout << std::endl;

  return 0;
}

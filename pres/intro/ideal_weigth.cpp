#include <iostream>

int main(void) {
  std::string nome, sexo;
  float altura;

  std::cin >> nome >> altura >> sexo;

  float pesoIdeal;
  if (!sexo.compare("homem")) {
    pesoIdeal = (72.7 * altura) - 58;
  } else {
    pesoIdeal = (62.1 * altura) - 44.7;
  }

  std::cout << pesoIdeal << std::endl;

  return 0;
}

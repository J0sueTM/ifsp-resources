#include <iostream>

int main(void) {
  float nota, media = 0;
  for (int i = 0; i < 3; ++i) {
    std::cin >> nota;
    media += nota;
  }
  media /= 3;

  if (media > 0 && media <= 3) {
    std::cout << "reprovado";
  } else if (media > 3 && media <= 6) {
    float notaExame = 12 - media;
    std::cout << "exame. Minimo: " << notaExame;
  } else {
    std::cout << "aprovado";
  }

  std::cout << std::endl;

  return 0;
}

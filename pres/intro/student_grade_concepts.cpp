#include <iostream>
#include <algorithm>
#include <numeric>

int main(void) {
  float notas[3];

  std::for_each(std::begin(notas), std::end(notas),
                [](auto &elem) {
                  std::cin >> elem;
                });
  notas[0] *= 0.2;
  notas[1] *= 0.3;
  notas[2] *= 0.5;

  float media = 0;
  media = std::accumulate(std::begin(notas), std::end(notas), media);

  std::string conceito;
  if (media > 0 && media <= 5) {
    conceito = "E";
  } else if (media > 5 && media <= 6) {
    conceito = "D";
  } else if (media > 6 && media <= 7) {
    conceito = "C";
  } else if (media > 7 && media <= 8) {
    conceito = "B";
  } else {
    conceito = "A";
  }

  std::cout << conceito << std::endl;

  return 0;
}

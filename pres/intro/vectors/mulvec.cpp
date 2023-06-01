#include <iostream>

int main(void) {
  int vec1[4], vec2[4], vecr[4];
  for (int i = 0; i < 4; ++i) {
    std::cout << "digite os ns. " << i + 1 << ": ";
    std::cin >> vec1[i] >> vec2[i];
    vecr[i] = vec1[i] + vec2[i];
  }

  std::cout << "resultado: ";
  for (int i = 0; i < 4; ++i) {
    std::cout << vecr[i] << " ";
  }

  return 0;
}

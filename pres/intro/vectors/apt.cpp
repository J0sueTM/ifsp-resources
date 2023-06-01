#include <iostream>
#include <limits>
#include <algorithm>

int main(void) {
  float apts[10], me = std::numeric_limits<float>::min(), mei = 0;
  for (int i = 0; i < 10; ++i) {
    std::cout << "apt " << i + 1 << ": ";
    std::cin >> apts[i];
    if (apts[i] > me) {
      me = apts[i];
      mei = i;
    }
  }

  std::cout << "maior gasto: " << me
            << ", do apt " << mei + 1
            << std::endl;

  return 0;
}

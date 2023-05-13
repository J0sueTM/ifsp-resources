#include <iostream>

int main(void) {
  int a;
  std::cin >> a;

  a = (a / 360 <= 0) ? a : a % 360;

  std::cout << (int)(a / 90) + 1 << std::endl;
  
  return 0;
}

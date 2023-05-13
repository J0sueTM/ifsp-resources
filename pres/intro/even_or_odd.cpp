#include <iostream>

int main(void) {
  int n;
  std::cin >> n;

  std::string o = (n % 2 == 0) ? "par" : "impar";
  std::cout << o << std::endl;

  return 0;
}

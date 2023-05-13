#include <iostream>
#include <algorithm>

int main(void) {
  int n[3];
  std::for_each(std::begin(n), std::end(n),
                [](auto &elem) {
                  std::cin >> elem;
                });
  std::sort(std::begin(n), std::end(n));
  std::for_each(std::begin(n), std::end(n),
                [](auto &elem) {
                  std::cout << elem << " ";
                });

  std::cout << std::endl;

  return 0;
}

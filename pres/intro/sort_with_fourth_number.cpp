#include <iostream>
#include <algorithm>

int main(void) {
  int n[4];

  std::for_each(std::begin(n), std::end(n),
                [](auto &elem) {
                  std::cin >> elem;
                });
  std::sort(std::rbegin(n), std::rend(n));
  std::for_each(std::begin(n), std::end(n),
                [](auto &elem) {
                  std::cout << elem << " ";
                });

  std::cout << std::endl;
  
  return 0;
}

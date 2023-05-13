#include <iostream>
#include <algorithm>

int main(void) {
  int i;
  float n[3];

  std::cin >> i;
  std::for_each(std::begin(n), std::end(n),
                [](auto &elem) {
                  std::cin >> elem;
                });

  switch (i) {
  case 1:
    std::sort(std::begin(n), std::end(n));

    break;
  case 2:
    std::sort(std::rbegin(n), std::rend(n));

    break;
  case 3:
  default: {
    std::sort(std::begin(n), std::end(n));
    float tmp_n = n[1];
    n[1] = n[2];
    n[2] = tmp_n;
  } break;
  }

  std::for_each(std::begin(n), std::end(n),
                [](auto &elem){
                  std::cout << elem << " ";
                });

  std::cout << std::endl;

  return 0;
}

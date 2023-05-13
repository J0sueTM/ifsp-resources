#include <iostream>
#include <algorithm>
#include <numeric>

int main(void) {
  int l[3];
  std::for_each(std::begin(l), std::end(l),
                [](auto &elem) {
                  std::cin >> elem;
                });

  std::string t;
  if (l[0] != l[1] && l[1] != l[2])
    t = "escaleno";
  else if (l[0] == l[1] && l[1] == l[2])
    t = "equilatero";
  else
    t = "isosceles";

  std::cout << t << std::endl;

  return 0;
}

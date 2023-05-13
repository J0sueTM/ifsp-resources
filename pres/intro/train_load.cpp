#include <iostream>

int main(void) {
  float a, p;
  std::cin >> a >> p;

  int c = 1;
  if (a >= 1.2f && a < 1.7f)
    c = 2;
  else if (a >= 1.7f)
    c = 3;

  int pcn = 1;
  if (p >= 60.f && p <= 90.f)
    pcn = 2;
  else if (p > 90.f)
    pcn = 3;

  char pc = '@' + ((pcn - 1) * 3 + c);
  std::cout << pc << std::endl;

  return 0;
}

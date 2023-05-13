#include <iostream>

int main(void) {
  float pa, mm;
  std::cin >> pa >> mm;

  bool a = true;
  float d = 0.f;
  if (mm < 500.f && pa < 30.f) {
    a = false;
    d = 0.1f;
  } else if ((mm >= 500.f && mm < 1200.f) &&
             (pa >= 30.f && pa < 80.f)) {
    a = false;
    d = 0.15f;
  } else {
    d = 0.2f;
  }

  float pn = (a) ? pa + pa * d : pa - pa * d;

  std::cout << pn << std::endl;

  return 0;
}

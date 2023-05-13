#include <iostream>

int main(void) {
  float s;
  std::cin >> s;

  if (s <= 500.f)
    s += s * 0.05f;
  else if (s > 500.f && s <= 1200.f)
    s += s * 0.12f;

  s += (s <= 600.f) ? 150.f : 100.f;

  std::cout << s << std::endl;

  return 0;
}

#include <iostream>

int main(void) {
  float s;
  int t;
  std::cin >> s >> t;

  float i;
  if (s < 200.f)
    i = 0.f;
  else if (s >= 200.f && s <= 450.f)
    i = 0.03f;
  else if (s > 450.f && s < 700.f)
    i = 0.08f;
  else
    i = 0.12f;

  int g;
  if (s <= 500.f) {
    if (t <= 3)
      g = 23;
    else if (t > 3 && t <= 6)
      g = 35;
    else
      g = 33;
  } else {
    g = (t > 3) ? 30 : 20;
  }

  float sl = s - (s * i) + g;
  char c;
  if (sl <= 350.f)
    c = 'A';
  else if (sl > 350.f && sl <= 600.f)
    c = 'B';
  else
    c = 'C';

  std::cout << c << std::endl;

  return 0;
}

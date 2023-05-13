#include <iostream>

int main(void) {
  float s, h, he;
  int d;

  std::cin >> s >> h >> d >> he;

  float vh = s * 0.2f;
  float sb = h * vh;
  sb += d * 32.f;
  sb += (vh * 0.5) * he;

  float i = 0.f;
  if (sb >= 200.f && sb <= 500.f)
    i = 0.1f;
  else if (sb > 500.f)
    i = 0.2f;

  float sl = sb - (sb * i);
  if (sl <= 350.f)
    sl += 100.f;
  else
    sl += 50.f;

  std::cout << sl << std::endl;

  return 0;
}

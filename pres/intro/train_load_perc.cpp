#include <iostream>

int main(void) {
  int c, cp;
  float p;
  std::cin >> c >> p >> cp;

  float pg;
  if (c >= 1 && c <= 4)
    pg = 10;
  else if (c >= 5 && c <= 7)
    pg = 25;
  else
    pg = 35;

  float i;
  switch (cp) {
  case 1:
    i = 0.f;
    break;
  case 2:
    i = 0.15f;
  case 3:
  default:
    i = 0.25f;
    break;
  }

  float g = p * 1000;
  float pt = g * pg;
  float ppi = pt * i;
  float pti = pt + ppi;
  
  std::cout << g << "g\t "
            << "$" << pt << "\t "
            << "$" << ppi << "\t "
            << "$" << pti << std::endl;
}

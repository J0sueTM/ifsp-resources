#include <iostream>

int main(void) {
  int ci, p, cc;
  std::cin >> ci >> p >> cc;

  int pkg = p * 1000;
  int ppkg;
  if (cc >= 10 && cc <= 20)
    ppkg = 100;
  else if (cc >= 21 && cc <= 30)
    ppkg = 250;
  else
    ppkg = 340;

  int ptpkg = pkg * ppkg;
  float i;
  switch (ci) {
  case 1:
    i = 0.35f;
    break;
  case 2:
    i = 0.25f;
    break;
  case 3:
    i = 15.f;
    break;
  case 4:
  default:
    i = 0.05f;
    break;
  }

  float ptt = ptpkg + ptpkg * i;

  std::cout << pkg << "kg\t "
            << "$" << ppkg << "\t "
            << i * 100 << "%\t "
            << "$" << ptt << std::endl;

  return 0;
}

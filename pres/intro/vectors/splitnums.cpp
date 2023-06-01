#include <iostream>
#include <algorithm>

int main(void) {
  const int vec_max = 8;
  int vec[vec_max], vecn[vec_max], nc = 0, vecp[vec_max], pc = 0;
  for (int i = 0; i < vec_max; ++i) {
    std::cout << "digite o numero " << i << ": ";
    std::cin >> vec[i];
    if (vec[i] >= 0) {
      vecp[pc] = vec[i];
      ++pc;
    } else {
      vecn[nc] = vec[i];
      ++nc;
    }
  }
  std::cout << std::endl;

  std::cout << "todos: ";
  for (int i = 0; i < vec_max; ++i) {
    std::cout << vec[i] << " ";
  }
  std::cout << std::endl;

  std::cout << "positivos: ";
  for (int i = 0; i < pc; ++i) {
    std::cout << vecp[i] << " ";
  }
  std::cout << std::endl;

  std::cout << "negativos: ";
  for (int i = 0; i < nc; ++i) {
    std::cout << vecn[i] << " ";
  }
  std::cout << std::endl;

  return 0;
}

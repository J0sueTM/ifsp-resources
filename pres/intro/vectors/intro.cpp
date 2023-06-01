#include <iostream>

int main(void) {
  const int vec_size = 5;
  int nums[vec_size];
  for (int i = 0; i < vec_size; ++i) {
    std::cout << "digite o numero " << i + 1 << ": ";
    std::cin >> nums[i];
  }

  std::cout << "os numeros digitados foram: ";
  for (int i = 0; i < vec_size; ++i) {
    std::cout << nums[i] << " ";
  }
  std::cout << std::endl;

  return 0;
}

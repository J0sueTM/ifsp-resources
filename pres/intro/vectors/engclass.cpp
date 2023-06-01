#include <iostream>

int main(void) {
  const int grade_count = 7;
  float grades[grade_count], median;
  for (int i = 0; i < grade_count; ++i) {
    std::cout << "digite a nota do aluno " << i + 1 << ": ";
    std::cin >> grades[i];
    median += grades[i];
  }
  median /= grade_count;

  std::cout << "media das notas: " << median << std::endl;

  return 0;
}

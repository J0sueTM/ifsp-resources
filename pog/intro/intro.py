def ex_01() -> None:
  name: str = input("digite seu nome: ")
  n1: float = float(input("digite sua n 1: "))
  n2: float = float(input("digite sua n 2: "))
  m: float = (n1 + n2) * 0.5

  print(f'{name}, sua media eh: {m}')

def ex_02() -> None:
  sb: float = float(input('digite seu salario base: R$'))
  sb -= sb * 0.1
  sb += 50

  print(f'seu salario sera de R${sb}')

def ex_03() -> None:
  name: str = input('digite seu nome: ')
  n1: float = float(input('digite sua nota 1: '))
  n2: float = float(input('digite sua nota 2: '))
  m: float = (n1 + n2) * 0.5

  status: str = ''
  if m >= 6:
    status = 'aprovado'
  elif m >= 4 and m < 6:
    status = 'recuperacao'
  else:
    status = 'reprovado'

  print(f'{name}, sua situacao foi {status}')

def ex_04() -> None:
  wp: int = int(input('digite a quantidade de pontos de agua: '))
  st: str = ''
  if wp <= 10:
    st = 'rochosa'
  elif wp > 10 and wp <= 40:
    st = 'firme'
  elif wp > 40 and wp <= 80:
    st = 'pantanosa'
  else:
    st = 'invalida'

  print(f'o solo foi classificado como {st}')

def main() -> None:
  # ex_01()
  # ex_02()
  # ex_03()
  ex_04()

if __name__ == '__main__':
  main()

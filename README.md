## YLab Homework 01
### 1. Stars
#### Программе передается 3 параметра: количество строк, количество столбцов, произвольный символоов. Необходимо вывести вывести фигуру, состоящую из заданного списка строк и заданного количества столбцов, и каждый элемент в которой равен указанному символу.

Ввод: n m c </br>
Вывод: фигура

Пример: </br>
Ввод:</br>
2 3 $ </br>

Вывод: </br>
$ $ $ </br>
$ $ $

### 2. PellNumbers
#### Числа Пелля задаются следующим соотношением:
> a(0) = 0, a(1) = 1; for n > 1, a(n) = 2*a(n-1) + a(n-2)
#### На вход подается число n (0 <= n <= 30), необходимо распечатать n-e число Пелля.

Пример: </br>
Ввод: </br>
5 </br>
Вывод: </br>
29

### 3. MultiTable
#### На вход ничего не подается, необходимо распечатать таблицу умножения чисел от 1 до 9 (включая).

Пример:</br>
Вывод:</br>
1 x 1 = 1 </br>
1 x 2 = 2</br>
…</br>
<часть вывода пропущена></br>
…</br>
9 x 9 = 81

### 4. Guess
#### Игра угадайка. При запуске программа загадывает число от 1 до 99 (включительно) и дает пользователю 10 попыток отгадать. Далее пользователь начинает вводить число. И тут возможен один из следующих вариантов:
- Пользователь отгадал число. В таком случае выводится строка
  “Ты угадал с N попытки”, где N - номер текущей попытки пользователя
- Пользователь ввел число, меньше загаданного. В таком случае выводится сообщение “Мое число меньше! У тебя осталось M попыток” где M - количество оставшихся попыток
- Пользователь ввел число, больше загаданного. В таком случае выводится сообщение “Мое число больше! У тебя осталось M попыток” где M - количество оставшихся попыток
- У пользователя закончились попытки и число не было угадано. В таком случае выводится сообщение “Ты ну угадал”

Получить случайный элемент от 1 до 99 (включительно):
int number = new Random().nextInt(99) + 1;

## YLab Homework 03
### 1. Transliterator
#### Правила транслитерации приведены ниже:
![изображение_2023-03-18_171221743](https://user-images.githubusercontent.com/34368106/226111346-4755f93c-4fe8-494f-b0c9-0110503fc3d5.png)

Реализовать интерфейс Transliterator. </br>
Метод transliterate должен выполнять транслитерацию входной строки в выходную, то
есть заменять каждый символ кириллицы на соответствующую группу символов
латиницы. Каждый символ кириллицы, имеющийся во входной строке входит в нее в
верхнем регистре.

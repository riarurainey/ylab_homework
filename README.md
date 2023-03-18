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

### 2. DatedMap
DatedMap - это структура данных, очень похожая на Map, но содержащая
дополнительную информацию: время добавления каждого ключа. При этом время
хранится только для тех ключей, которые присутствуют в Map. 

Реализовать DatedMapImpl путем реализации интерфейса DatedMap c методами:
+ <b>put.</b> Помещает в map пару ключ и значение. Как видно из описания метода, ключ и
значение - это строки (семантика Map#put)
+ <b>get.</b>
Возвращает значение, связанное с переданным в метод ключом. Если для
переданного ключа значение отсутствует - возвращается null (семантика Map#get)
+ <b>containsKey.</b> Метод, проверяющий, есть ли в map значение для данного ключа
(семантика Map#containsKey).
+ <b>remove.</b> Получая на вход ключ, удаляет из map ключ и значение, с ним связанное
(семантика Map#remove)
+ <b>ketSet.</b> Возвращает множество ключей, присутствующее в map (семантика
Map#keySet)
+ <b>getKeyLastInsertionDate.</b> Получая на вход ключ, проверяет, что для данного ключа
существует значение в map. Если существует - возвращает дату, когда оно было
добавлено. Если нет - возвращает null

В реализации данного класса можно использовать уже готовые структуры данных в
Java, такие как HashMap

### 3. Org Structure
Структура организации записана в виде строк в CSV файле. CSV - файл - это простой
текстовый файл, содержащий строки. Каждая строка представляет собой одну запись
(объект). Поля объекта разделены специальным символом ;. Первая строка файла
содержит поля имена полей, все дальнейшие сроки содержат непосредственно
данные.

Пример:
> id;boss_id;name;position </br>
> 1;;Иван Иванович;Генеральный директор </br>
> 2;1;Крокодилова Людмила Петровна;Главный бухгалтер </br>
> 3;2;Галочка;Бухгалтер </br>
> 4;1;Сидоров Василий Васильевич;Исполнительный директор </br>
> 5;1;Зайцев Валерий Петрович;Директор по ИТ</br>
> 6;5;Петя;Программист </br>

В файле поле id обозначает уникальный идентификатор сотрудника, boss_id
идентификатор начальника, name - имя сотрудника, position - должность. Таким
образом, данные в файле описывают следующую иерархию сотрудников:
![Opera Снимок_2023-03-18_182904_university ylab io](https://user-images.githubusercontent.com/34368106/226115337-e40f778b-5a86-403a-8297-b9c606d07d59.png)

Необходимо написать программу, получает на вход CSV файл формата, описанного
выше и формирует структуру объектов класса Employee.
Решение оформить в виде реализации интерфейса OrgStructureParser.

Метод parseStructure должен считывать данные из файла и возвращать ссылку на
Босса (Генерального директора) - сотрудника, атрибут boss_id которого не задан.
Cчитать, что такой сотрудник в файле ровно один.
P.S. subordinates - список прямых подчиненн


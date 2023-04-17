 # Домашнее задание к занятию «1. Многопоточное программирование. Работа с потоками».
  ## Задача 1. Интервал значений.
 #### Комментарии к решению:
 Мною были сделаны два варианта - с реализацией потоков через класс Thread и с использованием пула потоков с использованием
 интерфейса Runnable. Ниже приведены значения времени работы процессора, полученные из программы.
1. Время выполнения в исходном варианте 1 - Time: 83468ms
2. Использование процессора - 20%
3. Время выполнения в варианте c пулом из 4-х потоков - Time: 15066ms
4. Время выполнения в варианте c пулом из 8-ми потоков - Time: 11977ms
5. Время выполнения в варианте c пулом из 25-и потоков - Time: 12033ms
6. Время выполнения в многопоточном (25 потоков) варианте 1 - Time: 6416ms
7. Использование процессора в последних случаях - 100%.

 ### Описание задачи
Ваш коллега зачитался книгой по математической статистике и решил провести эксперимент. Он написал программу, которая
генерирует 25 строк размером 30 000 символов, которые состоят из символов 'a' и 'b'.
Для каждой строки он хочет найти размер наибольшего промежутка, состоящего из одних символов 'a'. Например, для строки
"aaababbaaaaabaa" ответом будет число 5 — 5 символов 'a' подряд.
Для этого он написал неэффективный алгоритм, который перебирает всевозможные индексы потенциального начала этого 
промежутка и всевозможные индексы конца, после чего проверяет, есть ли между ними символ 'b'.
Если символ 'b' не был найден, то программа запоминает размер этого промежутка при условии, что он оказался максимальным.
Менять этот алгоритм вам нельзя.
 ### Реализация
1. Реализуйте обработку каждой строки из массива texts в отдельном потоке.
2. До цикла создайте List<Thread> threads для хранения создаваемых потоков.
3. Поток создавайте через new Thread(...), в конструкторе передайте реализацию лямбдой интерфейса Runnable, в котором будет находиться нужное действие.
4. Не забудьте не только положить созданный объект потока в список потоков, но и запустить поток.
5. После цикла опишите ожидание запущенных потоков основным потоком программы:

for (Thread thread : threads) {
thread.join(); // зависаем, ждём когда поток объект которого лежит в thread завершится
}

Не изменяйте код программы, описанный в условиях задачи.

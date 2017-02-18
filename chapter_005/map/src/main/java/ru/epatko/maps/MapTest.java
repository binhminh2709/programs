package ru.epatko.maps;

import org.junit.Test;

import java.util.*;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         17.02.17.
 */
public class MapTest {


    Map<User, Object> map;
    Calendar birthday = new GregorianCalendar(1970, 01, 01);
    User userOne = new User("Name", 2, birthday);
    User userTwo = new User("Name", 2, birthday);

    @Test
    public void whenNotOverrideMethodsHashCodeAndEqualsThenHaveAddedTwoObjectsIntoMap() {
        map = new HashMap<>();
        User userOne = new User("Name", 2, birthday);
        User userTwo = new User("Name", 2, birthday);

        map.put(userOne, "one");
        map.put(userTwo, "two");
        System.out.println(map);
    }
//  Результат:
//        {ru.epatko.maps.User@5ce81285=one, ru.epatko.maps.User@78c03f1f=two}
//
//  Объяснение:
//        - метод put() в  HashMap вычисляет хэш ключа - вызывается метод hash(key) HashMap-а;
//        - метод hash() HashMap-а вычисляет хэш ключа на основании значения, возвращаемого методом key.hashCode();
//        - метод hashCode() - это метод Object-а, который, согласно документации, вычисляется путем преобразования
//          внутреннего адреса объекта в целое число;
//        - т.к. у нас два самостоятельных объекта (userOne и userTwo), то у них разные внутренние адреса
//          в JVM => hashCode() и hash() вернут разные значения для этих объектов;
//        - полученное от метода hash() число  вместе с ключом и элементом (значением, value) метод put() передает
//          в качестве параметра методу putVal();
//        - putVal():
//          ~ определяет позицию в массиве, куда будет помещен элемент,
//          ~ если массив равен null или его размер равен 0, то размер увеличивается и элемент кладется в ячейку (корзину),
//          ~ если массив не пустой и в ячейке (корзине) уже имеется элемент (группа элементов), то помещаемый элемент
//            сравнивается с каждым из имеющихся в ячейке элементов по хэшам (=) и ключам (equals() - по дефолту,
//            сравнивает внутренние адреса объектов). С версии Java SE 8  в заполненных группах для повышения производительности
//            (например, при множестве конфликтов) применяются сбалансированные двоичные деревья. Это увеличивает
//            производительность при индексации элементов и гарантирует поиск элемента за время O(log N),
//            но увеличивается время на добавление элемента в map,
//          ~ если у старого и нового элементов равны хэши и эквивалентны ключи, то на место старого элемента
//            под тем же ключом помещается новый элемент. В этом случае методы put() и putVal() возвращают значение
//            старого (замещенного) элемента,
//          ~ если хэши не равны или ключи не эквивалентны, то новый элемент добавляется в конец списка.
//
//  Итог: т.к. у объектов userOne и userTwo разные хэш-коды и разные внутренние адреса, то оба объекта были помещены
//        в map, а потом println-ом выведены на печать.


    @Test
    public void whenOverrideHashCodeAndNotOverrideEqualsThenHaveAddedOneObjectIntoMap() {
        map = new HashMap<>();
        UserH userOne = new UserH("Name", 2, birthday);
        UserH userTwo = new UserH("Name", 2, birthday);
        map.put(userOne, "one");
        map.put(userTwo, "two");

        System.out.println(userOne.equals(userTwo));



        System.out.println(map);
    }
}
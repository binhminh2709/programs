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
//        - метод put() в HashMap вычисляет хэш ключа - вызывается метод hash(key) HashMap-а;
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
//          ~ если массив не пустой и в ячейке (корзине) уже имеется элемент (группа элементов), то ключ помещаемого
//            элемента сравнивается с ключом каждого из имеющихся в ячейке элементов по хэшам (=) и (equals() (equals
//            по дефолту сравнивает внутренние адреса объектов). С версии Java SE 8  в заполненных группах для
//            повышения производительности (например, при множестве конфликтов) применяются сбалансированные двоичные
//            деревья. Это увеличивает производительность при индексации элементов и гарантирует поиск элемента
//            за время O(log N). Но при этом увеличивается время на добавление элемента в map,
//          ~ если у ключей старого и нового элементов равны хэши и ключи эквивалентны, то на место старого элемента
//            под тем же ключом помещается новый элемент. В этом случае методы put() и putVal() возвращают значение
//            старого (замещенного) элемента,
//          ~ если хэши не равны или ключи не эквивалентны, то новый элемент добавляется в конец списка. В этом случае
//            методы put() и putVal() возвращают null.
//
//  Итог: т.к. у объектов userOne и userTwo разные хэш-коды и разные внутренние адреса, то оба объекта были помещены
//        в map, что видно по результату исполнен я println(map).


    @Test
    public void whenOverrideHashCodeAndNotOverrideEqualsThenHaveAddedTwoObjectsIntoMap() {
        map = new HashMap<>();
        UserH userOne = new UserH("Name", 2, birthday);
        UserH userTwo = new UserH("Name", 2, birthday);

        map.put(userOne, "one");
        map.put(userTwo, "two");

        System.out.println(map);
    }
//  Результат:
//          {ru.epatko.maps.UserH@e1323e84=one, ru.epatko.maps.UserH@e1323e84=two}
//  Объяснение:
//          При добавлении значения по ключу в HashMap ключ нового элемента сравнивается с ключами имеющихся
//          в map-е элементов по хэшу и методом equals().
//          В данном случае у объектов-ключей userOne и userTwo будут одинаковые хэши, но результат вызова
//          userOne.equals(userTwo) будет false, т.к. equals()  по дефолту сравнивает адреса объектов во внутренней
//          памяти JVM.
//          Поэтому методы put() и  putVal() в HashMap решат, что userOne и userTwo являются двумя разными ключами
//          и занесут оба этих ключа с их значениями в map (в одну ячейку (корзину)).


    @Test
    public void whenOverrideEqualsAndNotOverrideHashCodeThenHaveAddedTwoObjectsIntoMap() {
        map = new HashMap<>();
        UserE userOne = new UserE("Name", 2, birthday);
        UserE userTwo = new UserE("Name", 2, birthday);

        map.put(userOne, "one");
        map.put(userTwo, "two");

        System.out.println(map);
    }
//  Результат:
//          {ru.epatko.maps.UserE@3d82c5f3=one, ru.epatko.maps.UserE@2b05039f=two}
//  Объяснение:
//          При добавлении значения по ключу в HashMap ключ нового элемента сравнивается с ключами имеющихся
//          в map-е элементов по хэшу и методом equals().
//          В данном случае у объектов-ключей userOne и userTwo будут разные хэши, а результат вызова
//          userOne.equals(userTwo) будет true, т.к. equals() переопределен и сравнивает поля объектов.
//          Поэтому методы put() и  putVal() в HashMap решат, что userOne и userTwo являются двумя разными ключами
//          и занесут оба этих ключа с их значениями в map.


    @Test
    public void whenOverrideEqualsAndHashCodeThenHaveAddedOneObjectIntoMap() {
        map = new HashMap<>();
        UserHE userOne = new UserHE("Name", 2, birthday);
        UserHE userTwo = new UserHE("Name", 2, birthday);

        map.put(userOne, "one");
        map.put(userTwo, "two");

        System.out.println(map);
    }
//  Результат:
//          {ru.epatko.maps.UserHE@e1323e84=two}
//  Объяснение:
//          При добавлении значения по ключу в HashMap ключ нового элемента сравнивается с ключами имеющихся
//          в map-е элементов по хэшу и методом equals().
//          В данном случае у объектов-ключей userOne и userTwo переопределены методы hashCode и equals (результат их
//          выполнения зависит от значения полей.
//          Т.к. значения полей у объектов-ключей userOne и userTwo одинаковы, то хэши этих объектов будут одинаковы и
//          userOne.equals(userTwo) даст true.
//          Поэтому методы put() и putVal() в HashMap решат, что userOne и userTwo являются одним ключом
//          и занесут в map сначала значение по ключу userOne ("one"), а следом перезапишут это значение
//          значением по ключу userTwo ("two").
//          После этого в map-е будет храниться только одно значение ("two"), которое можно получить по ключу
//          userOne или userTwo, т.к. для map-а это будет один и тот же ключ.

}

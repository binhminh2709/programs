 /*
  *  1. Сделать контейнер SimpleArray<T> добавить методы: add, update, delete, get.
  *  2. Сделать интерфейс Store<T extends Base> где Base - это абстрактный класс для моделей
  *  c методами String getId(); void setId(String id).
  *  3. Сделать два класса User, и Role которые наследуют данный класс.
  *  4. Сделать два хранилища UserStore и RoleStore. Внутри для хранения данных использовать SimpleArray.
  *  5. Методы добавить, обновить, удалить.
  *  6. Помните про инкапсуляцию. В методах store нельзя использовать методы c index.
  */

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 * 12.02.17.
 */
package ru.epatko.simpleArray;
/**
 * 6.1. Демонстрация работы GC:
 * Создать объект User c полями и перекрытым методом finalize.
 * Создать несколько объектов User и "руками" рассчитать, сколько он будет занимать памяти.
 * Нужно найти информацию, cколько памяти занимает пустой объект без полей.
 * Добиться состояния, когда виртуальная машина вызовет сборщик мусора самостоятельно (применить ключи xmx).
 * Объяснить поведение программы.
 *
 * 6.2. Эксперименты с различными GC:
 * Используя разные ключи запуска виртуальной машины, запустить различные виды сборщика мусора.
 * Оценить поведение срабатывания различных типов сборщиков мусора для программы из первой части данного модуля.
 * Какой из сборщиков мусора подойдет наиболее оптимально для приложения заявок из второго модуля?
 * Какой тип сборщика будет оптимален для сервеного приложения?
 *
 * 6.3. Реализация кэша на SoftReference:
 * Создать структуру данных типа кэш. Кэш должен быть абстрактный. То есть необходимо, чтобы можно было
 * задать ключ получения объекта кэша и в случае если его нет в памяти, задать поведение загрузки этого объекта в кэш.
 * Создать программу, эмулирующую поведение данного кэша. Программа должна считывать текстовые файлы из системы
 * и выдавать текст при запросе имени файла. Если в кэше файла нет, кэш должен загрузить себе данные.
 * По умолчанию в кэше нет ни одного файла. Текстовые файлы должны лежать в одной директории.
 * Пример. Names.txt, Address.txt - файлы в системе. При запросе по ключу Names.txt - кэш должен вернуть
 * содержимое файла Names.txt.

 *
 *
 *
 *
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 * 02.03.17.
 */
package ru.epatko.gc;
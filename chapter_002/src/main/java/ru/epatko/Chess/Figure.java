package ru.epatko.Chess;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         05.12.16.
 */
public abstract class Figure {
    final Cell position;
    private String type;

    public Figure(Cell source) {
        position = source;
    }

    public abstract Cell[] way(Cell dest) throws ImpossibleMoveException;
    /*Метод должен работать так. dest - задают ячейку куда следует пойти. Если фигура может туда пойти,
        то Вернуть массив ячеек. которые должна пройти фигура.
        Если фигура туда пойти не может. выбросить исключение ImpossibleMoveException*/
}

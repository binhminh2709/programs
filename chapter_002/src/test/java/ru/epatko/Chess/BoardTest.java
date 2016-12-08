package ru.epatko.Chess;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;


/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         07.12.16.
 */
public class BoardTest {

    @Test
    public void wenSetRightDestinationCellThenGetTrue() throws Exception {
        Board board = new Board();
        Cell source = new Cell(4, 7);
        Cell destination = new Cell(1, 4);
        board.cells[4][7] = new Bishop(source);
        assertThat(board.move(source, destination), is(true));
    }

    @Test
    public void wenSetRightDestinationCellThenGetFigureInTheDestinationCell() throws Exception {
        Board board = new Board();
        Cell source = new Cell(0, 7);
        Cell destination = new Cell(7, 0);
        Bishop bishop = new Bishop(source);
        board.cells[0][7] = bishop;
        board.move(source, destination);
        assertThat(board.cells[7][0] instanceof Bishop, is(true));
    }

    @Test
    public void wenSetRightDestinationCellThenGetNullInTheSourceCell() throws Exception {
        Board board = new Board();
        Cell source = new Cell(0, 0);
        Cell destination = new Cell(7, 7);
        Bishop bishop = new Bishop(source);
        board.cells[0][0] = bishop;
        board.move(source, destination);
        assertNull(board.cells[0][0]);
    }

    @Test
    public void wenSetWrongDestinationCellThenGetImpossibleMoveException() throws Exception {
        Board board = new Board();
        Cell source = new Cell(5, 4);
        Cell destination = new Cell(7, 7);
        Bishop bishop = new Bishop(source);
        board.cells[5][4] = bishop;
        board.move(source, destination);

    }
}
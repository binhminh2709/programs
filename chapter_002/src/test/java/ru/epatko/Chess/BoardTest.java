package ru.epatko.Chess;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
    public void wenSetRightDestinationCellThenGetRightRowFromFigureOnDestinationCell() throws Exception {
        Board board = new Board();
        Cell source = new Cell(0, 0);
        Cell destination = new Cell(7, 7);
        Bishop bishop = new Bishop(source);
        board.cells[0][0] = bishop;
        board.move(source, destination);
        assertThat(board.cells[7][7].position.getRow(), is(7));
    }

    @Test
    public void wenSetRightDestinationCellThenGetRightFigureOnDestinationCell() throws Exception {
        Board board = new Board();
        Cell source = new Cell(1, 0);
        Cell destination = new Cell(7, 6);
        Bishop bishop = new Bishop(source);
        board.cells[1][0] = bishop;
        board.move(source, destination);
        assertThat(board.cells[7][6] instanceof Bishop, is(true));
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

    @Test
    public void wenSetWrongWayThenGetOccupiedWayException() throws Exception {
        Board board = new Board();
        Cell source = new Cell(1, 0);
        Cell destination = new Cell(7, 6);
        Bishop bishop1 = new Bishop(source);
        Bishop bishop2 = new Bishop(destination);
        board.cells[1][0] = bishop1;
        board.cells[7][6] = bishop2;
        board.move(source, destination);
    }

    @Test
    public void wenSetWrongSourceCellThenGetException() throws Exception {
        Board board = new Board();
        Cell source = new Cell(1, 0);
        Cell destination = new Cell(7, 6);
        Bishop bishop = new Bishop(source);
        board.cells[2][1] = bishop;
        board.move(source, destination);
    }

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void wenThrowFigureNotFoundExceptionThenGetFigureNotFoundException()
                                                     throws FigureNotFoundException {
        thrown.expect(FigureNotFoundException.class);
        thrown.expectMessage("Message.");
        throw new FigureNotFoundException("Message.");
    }

    @Test
    public void wenThrowImpossibleMoveExceptionThenGetImpossibleMoveException()
                                                     throws ImpossibleMoveException {
        thrown.expect(ImpossibleMoveException.class);
        thrown.expectMessage("Message.");
        throw new ImpossibleMoveException("Message.");
    }

    @Test
    public void wenThrowOccupiedWayExceptionThenGetOccupiedWayException()
                                                     throws OccupiedWayException {
        thrown.expect(OccupiedWayException.class);
        thrown.expectMessage("Message.");
        throw new OccupiedWayException("Message.");
    }

}
package ru.epatko.ticTacToe;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         13.02.17.
 */
public class ConsolePrinter implements Printer {

    /**
     * Count of cells by vertical and horizontal in playing field.
     */
    private final int cells;

    /**
     * Default constructor.
     */
    public ConsolePrinter() {
        this.cells = 3;
    }

    /**
     * Constructor.
     *
     * @param cells count of cells by vertical and horizontal in playing field
     */
    public ConsolePrinter(int cells) {
        this.cells = cells;
    }

    /**
     * Print message to console.
     *
     * @param message message to print
     */
    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Print playing field to console.
     *
     * @param field playing field to print
     */
    @Override
    public void printField(String[][] field) {
        String cell;
        System.out.print("   ");
        for (int i = 0; i < this.cells; i++) {
            System.out.printf(" %s ", i + 1);
        }
        System.out.println();

        for (int i = 0; i < this.cells; i++) {
            System.out.printf("%3d", i + 1);
            for (int j = 0; j < this.cells; j++) {
                if (field[i][j] != null) {
                    cell = String.format("[%s]", field[i][j]);
                } else {
                    cell = "[ ]";
                }
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}

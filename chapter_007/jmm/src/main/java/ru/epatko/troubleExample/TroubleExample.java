package ru.epatko.troubleExample;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         12.03.17.
 */
public class TroubleExample {

    public static void main(String[] args) {

        ValueObject vObject = new ValueObject();
        vObject.setV(10);

        new Thread(new ThreadOne(vObject)).start();
        new Thread(new ThreadTwo(vObject)).start();

        System.out.printf("TroubleExample think that vObject.v = %d.%s",
                            vObject.getV(),
                            System.getProperty("line.separator"));
    }
}

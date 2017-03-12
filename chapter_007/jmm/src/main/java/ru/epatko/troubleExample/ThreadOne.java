package ru.epatko.troubleExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         12.03.17.
 */
public class ThreadOne implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ValueObject vObject;

    public ThreadOne(ValueObject vObject) {
        this.vObject = vObject;
    }

    @Override
    public void run() {
        int newValue = vObject.getV();

        for (int i = 0; i < 10; i++) {
            newValue -= i;
            vObject.setV(newValue);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                logger.info(e.getMessage());
            }
        }

        System.out.printf("ThreadOne think that vObject.v = %d. Realy vObject.v = %d.%s",
                            newValue,
                            vObject.getV(),
                            System.getProperty("line.separator"));

    }
}

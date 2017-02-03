package ru.epatko.newQualityControl;

import ru.epatko.qualityControl.Food;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         03.02.17.
 */
public interface RecycleFood extends Food {
    /**
     * Show recyclable flag.
     * @return - true or false.
     */
    boolean isRecyclable();
}

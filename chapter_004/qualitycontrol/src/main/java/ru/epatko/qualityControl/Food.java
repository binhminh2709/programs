package ru.epatko.qualityControl;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         29.01.17.
 */
public interface Food {

    /**
     * Set name.
     * @param name - product name.
     */
    void setName(String name);

    /**
     * Set product expiration date.
     * @param expirationDate - product expiration date.
     */
    void setExpirationDate(long expirationDate);

    /**
     * Set product creation date.
     * @param createDate - product creation date.
     */
    void setCreateDate(long createDate);

    /**
     * Set product quantity.
     * @param quantity - product quantity.
     */
    void setQuantity(double quantity);

    /**
     * Set product price.
     * @param price - product price.
     */
    void setPrice(double price);

    /**
     * Set discount.
     * @param discount - discount.
     */
    void setDiscount(double discount);
    /**
     * Set discount flag.
     */
    void setDiscountFlag();
    /**
     * Get discount flag.
     */
    boolean getDiscountFlag();

    /**
     * get product name.
     * @return -  product name.
     */
    String getName();

    /**
     * Get product expiration date.
     * @return - product expiration date (long).
     */
    long getExpirationDate();

    /**
     * Get product creation date.
     * @return - product creation date (long).
     */
    long getCreateDate();

    /**
     * Get product quantity.
     * @return - product quantity.
     */
    double getQuantity();

    /**
     * Get product price.
     * @return - product price.
     */
    double getPrice();

    /**
     * Get product discount.
     * @return - discount.
     */
    double getDiscount();
}

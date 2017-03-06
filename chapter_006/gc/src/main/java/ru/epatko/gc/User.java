package ru.epatko.gc;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         01.03.17.
 */
public class User {
    private String name;
    private int number;

    public User() {

    }

    public User(String name, int number) {
        this.name = name;
        this.number = number;
    }

    /**
     * Called by the garbage collector on an object when garbage collection
     * determines that there are no more references to the object.
     *
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Finalize number: " + this.number);

    }
}


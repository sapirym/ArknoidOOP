package listeners;

/**
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class Counter {
    private int counter;

    /**
     * constructor for Counter.
     *
     * @param counter - the number of the counter.
     */
    public Counter(int counter) {
        this.counter = counter;
    }

    /**
     * add number to current count.
     *
     * @param number - the number to increase.
     */
    public void increase(int number) {
        this.counter = this.counter + number;
    }

    /**
     * subtract number from current count.
     *
     * @param number - the number to decrease.
     */
    public void decrease(int number) {
        this.counter = this.counter - number;
    }

    /**
     * get current count.
     *
     * @return the current count.
     */
    public int getValue() {
        return counter;
    }
}

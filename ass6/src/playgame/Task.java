package playgame;

/**
 * Task interface.
 *
 * @param <T> the task
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public interface Task<T> {
    /**
     * runs the task.
     *
     * @return the T.
     */
    T run();
}

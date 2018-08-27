package playgame;

import animation.Animation;

/**
 * Menu interface.
 *
 * @param <T> the task.
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public interface Menu<T> extends Animation {
    /**
     * add the selection.
     *
     * @param key       - the key string.
     * @param message   - the string message.
     * @param returnVal - the val to return.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * add the selection.
     *
     * @return T.
     */
    T getStatus();

    /**
     * add sub menu.
     *
     * @param key     - the key string.
     * @param message - the message.
     * @param subMenu - the sub menu.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
    /**
     * check if key is already clicked.
     * @param s - the key.
     * */
    void isClicked(String s);
}

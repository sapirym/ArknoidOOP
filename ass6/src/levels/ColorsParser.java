package levels;

import java.awt.Color;

/**
 * ColorsParser class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class ColorsParser {
    /**
     * parse color definition and return the specified color.
     *
     * @param s - the string.
     * @return the color.
     */
    public java.awt.Color colorFromString(String s) {
        String[] splitWords;
        if (s.contains("color")) {
            s = s.replace("color", "");
            s = s.replace("(", "");
            s = s.replace(")", "");
            splitWords = s.split(" ");
            if (splitWords[0].equals("black")) {
                return Color.BLACK;
            } else if (splitWords[0].equals("blue")) {
                return Color.blue;
            } else if (splitWords[0].equals("cyan")) {
                return Color.cyan;
            } else if (splitWords[0].equals("gray")) {
                return Color.gray;
            } else if (splitWords[0].equals("lightGray")) {
                return Color.lightGray;
            } else if (splitWords[0].equals("orange")) {
                return Color.orange;
            } else if (splitWords[0].equals("pink")) {
                return Color.pink;
            } else if (splitWords[0].equals("red")) {
                return Color.red;
            } else if (splitWords[0].equals("white")) {
                return Color.white;
            } else if (splitWords[0].equals("yellow")) {
                return Color.yellow;
            }
            if (s.contains("RGB")) {
                splitWords = s.split(",");
                splitWords[0] = splitWords[0].replace("RGB", "");
                return new Color(Integer.parseInt(splitWords[0]), Integer.parseInt(splitWords[1])
                        , Integer.parseInt(splitWords[2]));
            }
        }
        return null;
    }
}

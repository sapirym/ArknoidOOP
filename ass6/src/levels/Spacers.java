package levels; /**
 * Created by lenovo on 14/06/2017.
 */

import java.util.List;
import java.util.TreeMap;

/**
 * Spacers class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class Spacers {
    private TreeMap<String, String> map;
    private int width;
    private List<String> spacers;
    private String symbol;

    /**
     * constructor for Spacers.
     * @param symbolOfSpacer - the symbol of the spacer.
     * @param listSpacers - the list of spacers.
     */
    public Spacers(String symbolOfSpacer, List<String> listSpacers) {
        symbol = symbolOfSpacer;
        this.spacers = listSpacers;
        map = new TreeMap<>();
        String str = null;
        for (int i = 0; i < spacers.size(); i++) {
            if (spacers.get(i).startsWith("sdef symbol:" + this.symbol)) {
                str = this.spacers.get(i);
                break;
            }
        }
        String[] split = str.split(":");
        this.map.put("width", split[2]);
        this.width = Integer.parseInt(this.map.get("width"));
    }

    /**
     * get the width of the spacer.
     * @return the width of the spacer.
     */
    public int getWidth() {
        return this.width;
    }

}


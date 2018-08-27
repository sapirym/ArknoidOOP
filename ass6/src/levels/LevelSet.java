package levels;

import java.util.List;

/**
 * LevelSet class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class LevelSet {
    private String levelName;
    private String levelKey;
    private List<LevelInformation> levelInformations;

    /**
     * constructor for LevelSet.
     *
     * @param levelInformations - the levels list.
     * @param levelKey - the key of the level.
     * @param levelName - the name of the level.
     */
    public LevelSet(String levelName, String levelKey, List<LevelInformation> levelInformations) {
        this.levelInformations = levelInformations;
        this.levelKey = levelKey;
        this.levelName = levelName;
    }

    /**
     * get the name of the level.
     *
     * @return string - the level name.
     */
    public String getName() {
        return levelName;
    }

    /**
     * get the key level.
     * @return string - the key.
     */
    public String getKey() {
        return levelKey;
    }

    /**
     * get the levels list.
     *
     * @return the levels list.
     */
    public List<LevelInformation> getLevels() {
        return levelInformations;
    }

}

package levels;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * LevelSpecificationReader class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class LevelSpecificationReader {
    private List<LevelInformation> levels;
    private ArrayList<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>>();

    /**
     * constructor for LevelSpecificationReader.
     */
    public LevelSpecificationReader() {
        levels = new ArrayList<>();
        listOfLists = new ArrayList<>();
    }

    /**
     * read the file.
     *
     * @param reader object.
     * @return level informations.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        addToLevelList(reader);
        for (List item : listOfLists) {
            this.levels.add(new PrepareLevelInformation(item).getLevelInformation());
        }
        return this.levels;
    }

    /**
     * arrange to level list.
     * @param reader object.
     */
    public void addToLevelList(java.io.Reader reader) {
        ArrayList<String> level = new ArrayList<String>();
        String pattern;
        BufferedReader br = new BufferedReader(reader);
        try {
            while ((pattern = br.readLine()) != null) {
                if (pattern.equals("")) {
                    continue;
                }
                if (!(pattern.matches("END_LEVEL")) && !pattern.matches("START_LEVEL")) {
                    if (!pattern.startsWith("#") && !pattern.isEmpty()) {
                        level.add(pattern);
                    }
                } else if (pattern.matches("END_LEVEL")) {
                    this.listOfLists.add(new ArrayList<String>(level));
                    level.clear();
                }
            }
        } catch (IOException e) {
            System.err.println("Cannot read file.");
            e.printStackTrace();
        }
        try {
            br.close();
        } catch (IOException e) {
            System.err.println("Could not close reader.");
            e.printStackTrace();
        }

    }
}

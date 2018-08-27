package levels;

import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * LevelSetReader class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class LevelSetReader {
    /**
     * read the file.
     *
     * @param reader - the reader object.
     * @return list of level sets.
     */
    public List<LevelSet> fromReader(Reader reader) {
        String[] levelNames = null;
        List<LevelSet> levelSets = new ArrayList<LevelSet>();
        List<LevelInformation> levelsList = new ArrayList<LevelInformation>();
        LineNumberReader numberReader = new LineNumberReader(reader);
        List<String> levelPath = new ArrayList<String>();
        String pattern;
        try {
            while ((pattern = numberReader.readLine()) != null) {
                if (numberReader.getLineNumber() % 2 == 1) {
                    levelNames = pattern.split(":");
                } else {
                    InputStream inputStream = ClassLoader.getSystemClassLoader()
                            .getResourceAsStream(pattern);
                    levelPath.add(pattern);
                    levelsList = new LevelSpecificationReader().fromReader(new InputStreamReader(inputStream));
                    levelSets.add(new LevelSet(levelNames[0], levelNames[1], levelsList));
                    inputStream.close();
                }
            }
        } catch (IOException e) {
            System.out.println("cannot read pattern.");
            e.printStackTrace();
        }
        return levelSets;
    }
}

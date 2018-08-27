package counters;

import playgame.ScoreInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * HighScoresTable class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class HighScoresTable {
    private static int size;
    private List<ScoreInfo> scoreInfos;

    /**
     * Create an empty high-scores table with the specified size.
     *
     * @param size - the size of the table.
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.scoreInfos = new ArrayList<ScoreInfo>(size);
    }
    /**
     * Create an empty high-scores table with the specified size.
     */
    public HighScoresTable() {
        this.scoreInfos = new ArrayList<ScoreInfo>();
    }

    /**
     * Read a table from file and return it.
     *
     * @param filename - the file.
     * @return new high scores table.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable highScoresTab = new HighScoresTable(5);
        try {
            highScoresTab.load(filename);
            return highScoresTab;
        } catch (Exception e) {
            return highScoresTab;
        }
    }

    /**
     * Add a high-score.
     *
     * @param score - the new score.
     */
    public void add(ScoreInfo score) {
        int indexPlace;
        if (getRank(score.getScores()) <= this.size) {
            indexPlace = (getRank(score.getScores()) - 1);
            scoreInfos.add(indexPlace, score);
        }
        if (scoreInfos.size() > this.size) {
            scoreInfos.remove(scoreInfos.size() - 1);
        }
    }

    /**
     * Return table size.
     *
     * @return the size of the table.
     */
    public static int size() {
        return size;
    }

    /**
     * Return the current high scores.
     *
     * @return the table..
     */
    public List<ScoreInfo> getHighScores() {
        return scoreInfos;
    }

    /**
     * return the rank of the current score.
     *
     * @param score - the score.
     * @return the rank.
     */
    public int getRank(int score) {
        int rank = 1;
        for (ScoreInfo scoreItem : this.getHighScores()) {
            if (score <= scoreItem.getScores()) {
                rank++;
            } else {
                break;
            }
        }
        return rank;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        scoreInfos.clear();
    }

    /**
     * loads the table.
     *
     * @param filename - the file name.
     * @throws IOException            - if the is a problem with the file.
     * @throws ClassNotFoundException - if the class not found.
     */
    public void load(File filename) throws IOException, ClassNotFoundException {
        this.clear();
        BufferedReader is = null;
        try {
            is = new BufferedReader(// wrapper that reads ahead
                    new InputStreamReader(// wrapper that reads characters
                            new FileInputStream(filename)));
            String playerScore;
            while ((playerScore = is.readLine()) != null) {
                String[] scores = playerScore.split(":");
                scoreInfos.add(new ScoreInfo(Integer.parseInt(scores[1]), scores[0]));
            }
        } catch (IOException e) {
            System.out.println("cannot read the file.");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println("cannot close the file.");
                }
            }
        }
    }

    /**
     * Save table data to the specified file.
     *
     * @param filename - the file.
     * @throws IOException - if the is a problem with the file.
     */
    public void save(File filename) throws IOException {
        PrintWriter os = null;
        try {
            os = new PrintWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(filename)));
            for (ScoreInfo oneScore : scoreInfos) {
                os.printf("%s:%d\n", oneScore.getName(), oneScore.getScores());
            }
        } catch (IOException e) {
            System.out.println(" Something went wrong while writing !");
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

}

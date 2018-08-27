package levels;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * BlocksDefinitionReader class.
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-06-28
 */
public class BlocksDefinitionReader {
    private static BlocksFromSymbolsFactory blocksFactory = new BlocksFromSymbolsFactory();
    private TreeMap<String, String> mapBlocks;
    private TreeMap<String, String> mapSpacers;

    /**
     * constructor for BlocksDefinitionReader.
     */
    public BlocksDefinitionReader() {
        this.mapBlocks = new TreeMap<>();
        this.mapSpacers = new TreeMap<>();
    }

    /**
     * read the file.
     * @param reader - the file reader.
     * @return the blocks factory.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        List<String> symbolSpacer;
        String line;
        BufferedReader br = new BufferedReader(reader);
        List<String> blocks = new ArrayList<>();
        List<String> symbols = new ArrayList<>();
        try {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty() && !line.startsWith("#")) {
                    if (line.startsWith("bdef symbol") || line.startsWith("default")) {
                        blocks.add(line);
                    } else if (line.startsWith("sdef symbol")) {
                        symbols.add(line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Could not read file.");
            e.printStackTrace();
        }
        try {
            br.close();
        } catch (IOException e) {
            System.err.println("Could not close reader.");
            e.printStackTrace();
        }
        symbolSpacer = makeSpacerSymbol(symbols);
        for (String s : symbolSpacer) {
            blocksFactory.addSpacer(s, new Spacers(s, symbols).getWidth());
        }
        String[] str;
        String[] str2;
        List<String> symbolBlocks = new ArrayList<>();
        for (int i = 0; i < blocks.size(); i++) {
            if (!blocks.get(i).startsWith("default")) {
                str = blocks.get(i).split(" ");
                str2 = str[1].split(":");
                symbolBlocks.add(str2[1]);
            }
        }
        for (String s : symbolBlocks) {
            blocksFactory.addToBlockMap(s, new MakeBlocks(s, blocks));
        }
        return blocksFactory;
    }

    /**
     * make list of symbols.
     * @param spacers - list of spacers.
     * @return list of strings.
     */
    public static List<String> makeSpacerSymbol(List<String> spacers) {
        List<String> symbols = new ArrayList<>();
        String[] str;
        String[] str2;
        for (int i = 0; i < spacers.size(); i++) {
            str = spacers.get(i).split(" ");
            str2 = str[1].split(":");
            symbols.add(str2[1]);
        }
        return symbols;
    }

}

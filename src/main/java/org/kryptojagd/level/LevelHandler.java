package org.kryptojagd.level;

import java.util.List;

/**
 * All available levels are provided through this class.
 * If necessary, levels are restarted here.
 *
 * @author Sven Strasser
 * @version 1.0
 */
public class LevelHandler {

    /**
     * Stores all available levels for the game.
     */
    private List<Level> levelList;

    public LevelHandler() {
        this.addConcreteLevels();
    }

    /**
     * Adds all available levels from a specific package to {@link LevelHandler#levelList}.
     */
    private void addConcreteLevels() {

    }


}

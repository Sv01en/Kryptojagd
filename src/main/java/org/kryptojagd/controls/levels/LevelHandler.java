package org.kryptojagd.controls.levels;

import org.kryptojagd.level.Level;

import java.util.ArrayList;

/**
 * Provides all levels in their initialize state.
 *
 * @author Sven Strasser
 * @version 1.0
 */
public class LevelHandler {

    private ArrayList<Level> allLevels;

    private ArrayList<Level> playedLevels = new ArrayList<>();

    private int levelPos;

    public LevelHandler(ArrayList<Level> levels) {
        this.allLevels = levels;
    }

    /**
     * Gets the requested level by the position in the list
     *
     * @param pos position in the list given as an integer
     * @return the requested level
     * @throws Exception if the position not valid
     */
    public Level getLevel(int pos) throws Exception {
        try {
            if (pos <= this.allLevels.size() && pos >= 0) {

                if (!this.playedLevels.contains(this.allLevels.get(pos))) {
                    this.levelPos = pos;
                    this.playedLevels.add(this.allLevels.get(pos));
                }
                return this.allLevels.get(pos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns the size of the list of all playable levels.
     * @return size as an integer
     */
    public int getPlayableLevelSize() {
        return this.playedLevels.size();
    }

    /**
     * Returns the position in the list of the current level
     * @return
     */
    public int getLevelPos() {
        return this.levelPos;
    }

    /**
     * Returns the list of all levels
     * @return arraylist
     */
    public ArrayList<Level> getAllLevels() {
        return this.allLevels;
    }

    /**
     * Checks if a level allready playable
     * @param level given level for checking process
     * @return true or false
     */
    public boolean checkPlayable(Level level) {
       return this.playedLevels.contains(level);
    }

    /**
     * Sets a new playable list of all levels
     * @param levels given level
     */
    public void setPlayedLevels(ArrayList<Level> levels) {
        this.playedLevels = levels;
    }

    /**
     * Returns the list of all playable levels
     * @return arraylist
     */
    public ArrayList<Level> getPlayedLevels() {
        return this.playedLevels;
    }
}

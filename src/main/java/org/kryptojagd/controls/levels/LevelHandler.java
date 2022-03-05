package org.kryptojagd.controls.levels;

import org.kryptojagd.level.Level;

import java.util.ArrayList;

/**
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

    public int getLevelPos() {
        return this.levelPos;
    }

    public ArrayList<Level> getAllLevels() {
        return this.allLevels;
    }

    public boolean checkPlayable(Level level) {
       return this.playedLevels.contains(level);
    }
}

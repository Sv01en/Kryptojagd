package org.kryptojagd.level;

import java.util.Comparator;

/**
 * Compares two levels by given id.
 *
 * @author Leah Schlimm, Amelie Reichert, Bartosz Treyde, Sven Strasser, Michail Petermann, Sonja Kuklok
 */
public class LevelComparator implements Comparator<Level> {

	/**
	 * Compares two levels.
	 *
	 * @param l1 given level
	 * @param l2 given level
	 * @return 0 or 1
	 */
    @Override
    public int compare(Level l1, Level l2) {
    	if (l1.getId() < l2.getId()) {
    		return -1;
    	} else if (l1.getId() == l2.getId()) {
    		return 0;
    	} else {
    		return 1;
    	}
    }
}

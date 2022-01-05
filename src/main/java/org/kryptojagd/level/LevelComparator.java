package org.kryptojagd.level;

import java.util.Comparator;

public class LevelComparator implements Comparator<Level> {
	
    @Override
    public int compare(Level l1, Level l2) {
    	if (l1.getId() < l2.getId()) {
    		return -1;
    	} else if (l1.getId() == l2.getId()) {
    		return 0;
    	} else {
    		return 1;
    	}
        
    	// return l1.getId().compareTo(l2.getId());
    }
}

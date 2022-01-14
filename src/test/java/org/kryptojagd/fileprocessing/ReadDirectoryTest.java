package org.kryptojagd.fileprocessing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import org.kryptojagd.fileprocessing.ReadDirectory;
import org.kryptojagd.level.Level;

class ReadDirectoryTest {

	@Test
	void testInitialize() throws Exception{
		ArrayList<Level> levels = ReadDirectory.initialize();
	}
	

}

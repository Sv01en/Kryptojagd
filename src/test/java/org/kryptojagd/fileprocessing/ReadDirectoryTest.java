package org.kryptojagd.fileprocessing;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.kryptojagd.fileprocessing.ReadDirectory;
import org.kryptojagd.level.Level;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Read directory test.
 */
class ReadDirectoryTest {

  /**
   * Test initialize.
   * Tests if the levels ar null and if the number of levels is correct.
   * @throws Exception the exception
   */
  @Test
  void testInitialize() throws Exception {
    ArrayList<Level> levels = ReadDirectory.initialize();
    for( Level level : levels){
      assertNotNull(level);
    }
    assertEquals(5, levels.size());
  }

  /**
   * Test read level directory.
   * Tests if the level is correctly implemented from the JSon files.
   */
  @Test
  void testReadLevelDirectory(){
    Level level = ReadDirectory.getLevel();
    assertNotNull(level);
    assertNotNull(level.getDecryptionTask());
    assertNotNull(level.getEncryptionTask());
    assertTrue(level.getTimeInSec() != 0);
  }


}

package com.krickert.algorithm;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import junit.framework.TestCase;

public class CalcMaxTreePathTest extends TestCase {

  public void testCalcMaxTreePathTest() {
    InputStream mockInput = new ByteArrayInputStream("1\n2 3\n4 6 5\n".getBytes());
    String output = "Maximum value of path in triangle: [10] and a path of: [[1, 3, 6]]";
    CalcMaxTreePath treePath = new CalcMaxTreePath();
    assertEquals(treePath.process(mockInput).toString(), output);
  }
}

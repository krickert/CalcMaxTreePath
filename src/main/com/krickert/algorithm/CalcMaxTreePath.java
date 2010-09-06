package com.krickert.algorithm;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.krickert.algorithm.data.MaxPathNode;

/**
 * If given a triangle in an input file, this application will calculate the
 * maximum path taken. For example:
 * 
 * <pre>
 * 1
 * 2 3
 * 3 5 4
 * </pre>
 * 
 * Will return a MaxNode with the values of 10, 1-3-5
 * 
 * @author krickert
 * 
 */
public class CalcMaxTreePath {

  public static void main(String args[]) throws IOException {
    InputStream in = getInputStream(args);
    CalcMaxTreePath treePathProcessor = new CalcMaxTreePath();
    MaxPathNode answer = treePathProcessor.process(in);
    System.out.println(answer);
  }

  /**
   * If there is a filename sent as an input then we'll use a file as an input
   * or else we'll assume STDIN
   * 
   * @param args
   *          the main args
   * @return the input stream we'll use for this app
   * @throws FileNotFoundException
   *           if the file was used and it is undefined
   */
  private static InputStream getInputStream(String[] args) throws FileNotFoundException {
    InputStream in;
    if (args[0] != null) {
      in = new BufferedInputStream(new FileInputStream(args[0]));
    } else {
      throw new IllegalArgumentException("No filename passed into processor.");
    }
    return in;
  }

  /**
   * Processes in and outputs the current max path for each line. Will throw an
   * exception if input is not already a triangle.
   * 
   */
  protected MaxPathNode process(InputStream in) {
    Scanner sc = new Scanner(in);
    ArrayList<MaxPathNode> currentLine = new ArrayList<MaxPathNode>(1);
    ArrayList<MaxPathNode> previousLine;
    int cursor;
    int rowNum = 0;
    int cursorValue;
    MaxPathNode currentMax = MaxPathNode.EMPTY_NODE;
    while (sc.hasNextLine()) {
      // reset cursor and max val
      cursor = 0;
      Scanner scLine = new Scanner(sc.nextLine());
      previousLine = currentLine;
      currentLine = new ArrayList<MaxPathNode>(rowNum + 1);
      boolean hasRight;
      while (scLine.hasNext()) {
        cursorValue = scLine.nextInt();
        hasRight = scLine.hasNext();
        MaxPathNode rightParent;
        MaxPathNode leftParent;

        // no left parent if we have a 0 cursor because it's a triangle
        leftParent = (cursor != 0) ? previousLine.get(cursor - 1) : MaxPathNode.EMPTY_NODE;

        // no right parent if we have the last point because it's a triangle
        rightParent = (hasRight) ? previousLine.get(cursor) : MaxPathNode.EMPTY_NODE;

        // dead stupid simple factory
        MaxPathNode node = MaxPathNode.createMaxPathNode(cursor, cursorValue, leftParent, rightParent);

        currentLine.add(node);// have to add to the current line to keep track
        currentMax = MaxPathNode.getMax(currentMax, node);// have to calculate
                                                          // the maximum
        cursor++;// move the cursor
      }
      rowNum++;
    }
    return currentMax;
  }
}

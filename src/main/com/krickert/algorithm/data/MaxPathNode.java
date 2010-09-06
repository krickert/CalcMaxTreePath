package com.krickert.algorithm.data;

import java.util.LinkedList;
import java.util.List;

/**
 * To figure out a maximum path, we will keep track of the maximum path for each
 * row as we go along. <br>
 * It's probably easier to understand by looking at a sample:<br>
 * 
 * <pre>
 * Row 0: 1
 * Row 1: 2 3
 * Row 2: 4 6 5
 * </pre>
 * 
 * There is no maximum path at the root (row 0), since there is nothing.<br>
 * For row 1: the max path of element 0 is <1,2> with a value of 3. For element
 * 1 the path is <1,3> with a value of 4.<br>
 * For row 2: the max path of element 0 is <1,3,4> with a value of 8. For
 * element 1 the path is <1,3,6> with a value of 10. For element 2 the path is
 * <1,3,5> with a value of 9.<br>
 * The max of row 3 is element 1 with a path of <1,3,6><br>
 * 
 * @author krickert
 * 
 */
public class MaxPathNode {

  /**
   * Simple representation of an empty node
   */
  public final static MaxPathNode EMPTY_NODE = new MaxPathNode(-1, new NodeValue(0, new LinkedList<Integer>()));

  protected final Integer key;
  protected final NodeValue value;

  /**
   * key = the index in the tree that this references. <br>
   * value = the max path to reach that particular node
   */
  public MaxPathNode(Integer key, NodeValue value) {
    this.key = key;
    this.value = value;
  }

  /**
   * @param left
   *          left parent
   * @param right
   *          right parent
   * @return the greater of the two
   */
  public static MaxPathNode getMax(MaxPathNode left, MaxPathNode right) {
    return (left.value.getNodeValue() > right.value.getNodeValue()) ? left : right;
  }

  /**
   * Creates a new path node - calculates the left and right values and figures
   * out the new maximum.
   * 
   * @param index
   *          - the reference to the nth element on the row, starting at 0.
   * @param nodeValue
   *          - the value of the current node to figure out the maximum value
   * @param left
   *          - the left parent, EMPTY_NODE if it does not exist
   * @param right
   *          - the right parent, EMPTY_NODE if it does not exist
   * @return the maximum path value along with the current path
   */
  public static MaxPathNode createMaxPathNode(Integer index, Integer nodeValue, MaxPathNode left, MaxPathNode right) {
    MaxPathNode maxParent = getMax(left, right);
    List<Integer> newMax = new LinkedList<Integer>(maxParent.value.getPath());
    newMax.add(nodeValue);
    MaxPathNode node = new MaxPathNode(index, new NodeValue(maxParent.value.getNodeValue() + nodeValue, newMax));
    return node;
  }

  @Override
  public String toString() {
    return "Maximum value of path in triangle: [" + this.value.getNodeValue() + "]" + " and a path of: [" + this.value.getPath() + "]";
  }
}

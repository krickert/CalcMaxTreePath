package com.krickert.algorithm.data;

import java.util.List;

public class NodeValue {
  protected final Integer value;
  protected final List<Integer> path;

  /**
   * Construction of a new node
   * 
   * @param total
   *          the
   * @param path
   */
  public NodeValue(Integer value, List<Integer> path) {
    this.value = value;
    this.path = path;
  }

  public Integer getNodeValue() {
    return value;
  }

  public List<Integer> getPath() {
    return path;
  }
}
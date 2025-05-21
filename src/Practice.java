import java.util.HashSet;
import java.util.Set;

/**
 * A utility class providing various graph traversal methods using DFS.
 */
public class Practice {

  /**
   * Prints the value of every vertex reachable from the given starting vertex,
   * including the starting vertex itself. Each value is printed on a separate line.
   * The order of printing is unimportant.
   *
   * Each vertex's value should be printed only once, even if it is reachable via multiple paths.
   * It is guaranteed that no two vertices will have the same value.
   *
   * If the given vertex is null, this method prints nothing.
   *
   * @param vertex The starting vertex for the traversal.
   */
  public <T> void printVertexVals(Vertex<T> vertex) {

    Set<T> visited = new HashSet<>();

    printVertexValsHelper(vertex, visited);

  }//end printVertexVals


  private <T> void printVertexValsHelper(Vertex<T>vertex, Set<T>visited) {

    if (vertex == null || visited.contains(vertex.data)) {

      return;

    }//end if


    System.out.println(vertex.data);
    visited.add(vertex.data);

    for (Vertex<T> neighbor : vertex.neighbors) {

      printVertexValsHelper(neighbor, visited);
    
    }//end for


  }//end printVertexValsHelper



  /**
   * Returns a set of all vertices reachable from the given starting vertex,
   * including the starting vertex itself.
   *
   * If the given vertex is null, an empty set is returned.
   *
   * @param vertex The starting vertex for the traversal.
   * @return A set containing all reachable vertices, or an empty set if vertex is null.
   */
  public <T> Set<Vertex<T>> reachable(Vertex<T> vertex) {

    Set<Vertex<T>> visited = new HashSet<>();
    reachableHelper(vertex, visited);

    return visited;

}//end reachable

private <T> void reachableHelper(Vertex<T> vertex, Set<Vertex<T>> visited) {

  if (vertex == null || visited.contains(vertex)) {

    return;

  }//end if

  visited.add(vertex);

  for (Vertex<T> neighbor : vertex.neighbors) {

    reachableHelper(neighbor, visited);

  }//end for


}//end reachableHelper



  /**
   * Returns the maximum value among all vertices reachable from the given starting vertex,
   * including the starting vertex itself.
   *
   * If the given vertex is null, the method returns Integer.MIN_VALUE.
   *
   * @param vertex The starting vertex for the traversal.
   * @return The maximum value of any reachable vertex, or Integer.MIN_VALUE if vertex is null.
   */
  public int max(Vertex<Integer> vertex) {
    
  if (vertex == null) {

    return Integer.MIN_VALUE;//returns Integer.MIN_VALUE if null

  }//end if

  Set<Vertex<Integer>> visited = new HashSet<>();
  return maxHelper(vertex, visited);

}//end max

private int maxHelper(Vertex<Integer> vertex, Set<Vertex<Integer>> visited) {
  if (visited.contains(vertex)) {

    return Integer.MIN_VALUE;

  }//end if

  visited.add(vertex);
  int max = vertex.data;

  for (Vertex<Integer> neighbor : vertex.neighbors) {

    max = Math.max(max, maxHelper(neighbor, visited));

  }//end for

  return max;

}//end maxHelper





  /**
   * Returns a set of all leaf vertices reachable from the given starting vertex.
   * A vertex is considered a leaf if it has no outgoing edges (no neighbors).
   *
   * The starting vertex itself is included in the set if it is a leaf.
   *
   * If the given vertex is null, an empty set is returned.
   *
   * @param vertex The starting vertex for the traversal.
   * @return A set containing all reachable leaf vertices, or an empty set if vertex is null.
   */
  public <T> Set<Vertex<T>> leaves(Vertex<T> vertex) {


  Set<Vertex<T>> result = new HashSet<>();
  Set<Vertex<T>> visited = new HashSet<>();

  leavesHelper(vertex, visited, result);
  return result;

}//end leaves

private <T> void leavesHelper(Vertex<T> vertex, Set<Vertex<T>>visited, Set<Vertex<T>>result) {

  if (vertex == null || visited.contains(vertex)) {

    return;

  }//end if

  visited.add(vertex);


  if (vertex.neighbors.isEmpty()) {

    result.add(vertex);
    return;

  }//end if

  for (Vertex<T> neighbor : vertex.neighbors) {

    leavesHelper(neighbor, visited, result);

  }//end for



}//end leavesHelper




  /**
   * Returns whether all reachable vertices (including the starting vertex) hold
   * odd values. Returns false if at least one reachable vertex (including the starting vertex)
   * holds an even value.
   * 
   * If the given vertex is null, returns true.
   * 
   * @param vertex The starting vertex
   * @return true if all reachable vertices hold odd values, false otherwise
   */
  public boolean allOdd(Vertex<Integer> vertex) {

  Set<Vertex<Integer>> visited = new HashSet<>();
  return allOddHelper(vertex, visited);

}//end allOdd


private boolean allOddHelper(Vertex<Integer> vertex, Set<Vertex<Integer>> visited) {

  if (vertex == null) {

    return true;

  }//end if

  if (visited.contains(vertex)) {
    return true;

  }//end if

  visited.add(vertex);

  if (vertex.data % 2 == 0) {

    return false;

  }//end if


  for (Vertex<Integer> neighbor : vertex.neighbors) {
    if (!allOddHelper(neighbor, visited)) {

      return false;

    }//end if

  }//end for

  return true;

}//end allOddHelper



  /**
   * Determines whether there exists a strictly increasing path from the given start vertex
   * to the target vertex.
   *
   * A path is strictly increasing if each visited vertex has a value strictly greater than
   * (not equal to) the previous vertex in the path.
   *
   * If either start or end is null, a NullPointerException is thrown.
   *
   * @param start The starting vertex.
   * @param end The target vertex.
   * @return True if a strictly increasing path exists, false otherwise.
   * @throws NullPointerException if either start or end is null.
   */
  public boolean hasStrictlyIncreasingPath(Vertex<Integer> start, Vertex<Integer> end) {

    if (start ==null || end ==null) {

    throw new NullPointerException("Error: Start/end vertex is null!");
    
  }//end if

  Set<Vertex<Integer>> visited = new HashSet<>();
  return hasStrictlyIncreasingPathHelper(start, end, visited);
  
}//end hasStrictlyIncreasingPath

private boolean hasStrictlyIncreasingPathHelper(Vertex<Integer> current, Vertex<Integer>target, Set<Vertex<Integer>> visited) {

  if (current == target) {

    return true;
  
  }//end if

  visited.add(current);

  for (Vertex<Integer> neighbor : current.neighbors) {

    if (!visited.contains(neighbor) && neighbor.data > current.data) {
      if (hasStrictlyIncreasingPathHelper(neighbor, target, visited)) {

        return true;

      }//end if
    }//end if

  }//end for

  return false;

}//end hasStrictlyIncreasingPathHelper



}//end practice.java

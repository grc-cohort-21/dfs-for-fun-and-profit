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
    Set<Vertex<T>> visited = new HashSet<>();
  dfsPrint(vertex, visited);
}

private <T> void dfsPrint(Vertex<T> vertex, Set<Vertex<T>> visited) {
  if (vertex == null || visited.contains(vertex)) return;

  visited.add(vertex);
  System.out.println(vertex.data);

  for (Vertex<T> neighbor : vertex.neighbors) {
    dfsPrint(neighbor, visited);
  }
  }

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
    Set<Vertex<T>> result = new HashSet<>();
    findReachable(vertex, result);
    return result;
  }

  private <T> void findReachable(Vertex<T> node, Set<Vertex<T>> result) {
    if (node == null || result.contains(node)) return;

    result.add(node);

    for (Vertex<T> neighbor : node.neighbors) {
      findReachable(neighbor, result);
    }
  
  }

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
      if (vertex == null) return Integer.MIN_VALUE;
      return findBiggest(vertex, new HashSet<>());
    }
    
    private int findBiggest(Vertex<Integer> node, Set<Vertex<Integer>> visited) {
      if (node == null || visited.contains(node)) return Integer.MIN_VALUE;
    
      visited.add(node);
      int biggest = node.data;
    
      for (Vertex<Integer> next : node.neighbors) {
        int value = findBiggest(next, visited);
        if (value > biggest) biggest = value;
      }
    
      return biggest;
    }
    

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
  findLeaves(vertex, visited, result); 
  return result;
}

private <T> void findLeaves(Vertex<T> node, Set<Vertex<T>> visited, Set<Vertex<T>> result) {
  if (node == null || visited.contains(node)) return;

  visited.add(node);

  if (node.neighbors.isEmpty()) {
    result.add(node); 
  }

  for (Vertex<T> next : node.neighbors) {
    findLeaves(next, visited, result);
  }
  }


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
    return checkOdd(vertex, new HashSet<>());
}

private boolean checkOdd(Vertex<Integer> node, Set<Vertex<Integer>> visited) {
  if (node == null) 
  
  return true;
  if (visited.contains(node)) 
  
  return true;
  visited.add(node);
  if (node.data % 2 == 0) 
  return false; 
  for (Vertex<Integer> next : node.neighbors) {
    if (!checkOdd(next, visited)) 
    return false;
  }

  return true;
  }

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
    return false;
  }
}

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
  public <T> void printVertexVals(Vertex<T> vertex) 
  {
    Set<Vertex<T>> visted = new HashSet<>();
    printVertexVals(vertex, visted);
  }

  public <T> void printVertexVals(Vertex<T> vertex, Set<Vertex<T>> visited)
  {
    if (vertex == null || visited.contains(vertex)) return;
    System.out.println(vertex.data);
    visited.add(vertex);
    for (Vertex<T> connection : vertex.neighbors) printVertexVals(connection, visited);
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
  public <T> Set<Vertex<T>> reachable(Vertex<T> vertex) 
  {
    Set<Vertex<T>> visited = new HashSet<>();
    return reachable(vertex, visited);
  }

  public <T> Set<Vertex<T>> reachable(Vertex<T> vertex, Set<Vertex<T>> visited)
  {
    if (vertex == null || visited.contains(vertex)) return visited;
    visited.add(vertex);
    for (Vertex<T> neighbor : vertex.neighbors) reachable(neighbor, visited);
    return visited;
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
  public int max(Vertex<Integer> vertex) 
  {
    Set<Vertex<Integer>> visited = new HashSet<>();
    int maxVal = Integer.MIN_VALUE;
    return max(vertex, visited, maxVal);
  }

  public int max(Vertex<Integer> vertex, Set<Vertex<Integer>> visited, int maxVal)
  {
    if (vertex == null || visited.contains(vertex)) return maxVal;
    
    visited.add(vertex);
    for (Vertex<Integer> neighbor : vertex.neighbors) 
    {
      maxVal = max(neighbor, visited, maxVal);
      if (vertex.data > maxVal) maxVal = vertex.data;
    }
    
    return maxVal;
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
    return null;
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

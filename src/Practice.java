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
    Set<Vertex<T>> visitedSet = new HashSet<>();
    printVertexVals(vertex, visitedSet);
  }

  public <T> void printVertexVals(Vertex<T> vertex, Set<Vertex<T>> visitedSet) {
    if(vertex == null || visitedSet.contains(vertex)) return;
    visitedSet.add(vertex);
    System.out.println(vertex.data);
    for(Vertex<T> neighbor : vertex.neighbors) {
      printVertexVals(neighbor, visitedSet);
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
    Set<Vertex<T>> reachableSet = new HashSet<>();
    return reachable(vertex, reachableSet);
  }

  public <T> Set<Vertex<T>> reachable(Vertex<T> vertex, Set<Vertex<T>> reachableSet) {
    if(vertex == null || reachableSet.contains(vertex)) return reachableSet;
    reachableSet.add(vertex);
    for(Vertex<T> neighbor : vertex.neighbors) {
      reachable(neighbor,reachableSet);
    }
    return reachableSet;
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
    Set<Vertex<Integer>> visitedSet = new HashSet<>();
    return max(vertex, visitedSet);
  }
  
  public int max(Vertex<Integer> vertex, Set<Vertex<Integer>> visitedSet) {
    if(vertex == null || visitedSet.contains(vertex)) return Integer.MIN_VALUE;
    visitedSet.add(vertex);
    int max = vertex.data;
    for(Vertex<Integer> neighbor : vertex.neighbors) {
      max = Math.max(max, max(neighbor,visitedSet));
    }
    return max;
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
    Set<Vertex<T>> visitedSet = new HashSet<>();
    Set<Vertex<T>> leafSet = new HashSet<>();
    return leaves(vertex, visitedSet, leafSet);
  }

    public <T> Set<Vertex<T>> leaves(Vertex<T> vertex, Set<Vertex<T>> visitedSet, Set<Vertex<T>> leafSet) {
    if(vertex == null || visitedSet.contains(vertex)) return leafSet;
    visitedSet.add(vertex);
    if(vertex.neighbors.isEmpty()) {
      leafSet.add(vertex);
    }
    for(Vertex<T> neighbor : vertex.neighbors) {
      leaves(neighbor, visitedSet, leafSet);
    }
    return leafSet;
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
    Set<Vertex<Integer>> visitedSet = new HashSet<>();
    return allOdd(vertex, visitedSet);
  }

   public boolean allOdd(Vertex<Integer> vertex, Set<Vertex<Integer>> visitedSet) {
    if(vertex == null || visitedSet.contains(vertex)) return true;
    visitedSet.add(vertex);
    if(vertex.data % 2 == 0) return false;
    boolean result = true;
    for(Vertex<Integer> neighbor : vertex.neighbors) {
     result = result && allOdd(neighbor, visitedSet);
    }
    return result;
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
    if(start == null || end == null) throw new NullPointerException("start vertex and end vertex cannot be null");
    Set<Vertex<Integer>> visitedSet = new HashSet<>();
    return hasStrictlyIncreasingPath(start, end, visitedSet);
  }

  public boolean hasStrictlyIncreasingPath(Vertex<Integer> start, Vertex<Integer> end, Set<Vertex<Integer>> visitedSet) {
    if(start.equals(end)) return true;
    if(visitedSet.contains(start)) return false; 
    visitedSet.add(start);
    for(Vertex<Integer> neighbor : start.neighbors) {
      boolean isIncreasing = neighbor.data > start.data;
      boolean validPath = hasStrictlyIncreasingPath(neighbor, end, visitedSet);
      if (isIncreasing && validPath) return true;
    }
    return false;
  }
}

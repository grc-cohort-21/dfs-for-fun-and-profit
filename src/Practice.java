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
    Set<Vertex<T>> myVists = new HashSet<>();
    printVertexValsHelper(vertex, myVists);
  }

  public <T> void printVertexValsHelper(Vertex<T> current, Set<Vertex<T>> vists)
  {
    if(current == null || vists.contains(current)) return;

    System.out.println(current.data);
    vists.add(current);

    for(Vertex<T> neighbor : current.neighbors)
    {
      printVertexValsHelper(neighbor, vists);
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
    Set<Vertex<T>> set = new HashSet<>();
    reachableHelper(vertex, set);
    return set;
  }

  public <T> void reachableHelper(Vertex<T> current, Set<Vertex<T>> vists)
  {
    if(current == null || vists.contains(current)) return;

    System.out.println(current.data);
    vists.add(current);

    for(Vertex<T> neighbor : current.neighbors)
    {
      reachableHelper(neighbor, vists);
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
    Set<Vertex<Integer>> set = new HashSet<>();
    return maxHelper(vertex, set);
  }

  public int maxHelper(Vertex<Integer> current, Set<Vertex<Integer>> set)
  {
    if(current == null || set.contains(current)) return Integer.MIN_VALUE;

    int maxVal = current.data;
    set.add(current);

    for(Vertex<Integer> neighbor : current.neighbors)
    {
      maxVal = Math.max(maxVal, maxHelper(neighbor, set));
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
    Set<Vertex<T>> set = new HashSet<>();
    Set<Vertex<T>> resultLeaf = new HashSet<>();
    leavesHelper(vertex, set, resultLeaf);

    return resultLeaf;
  }

 public <T> void leavesHelper(Vertex<T> current, Set<Vertex<T>> vists, Set<Vertex<T>> leaf)
  {
    if(current == null || vists.contains(current)) return;

    if(current.neighbors.isEmpty()) leaf.add(current);
    vists.add(current);

    for(Vertex<T> neighbor : current.neighbors)
    {
      leavesHelper(neighbor, vists, leaf);
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
    Set<Vertex<Integer>> set = new HashSet<>();

    if(vertex == null) return true;

    return allOddHelper(vertex, set);
  }

  public boolean allOddHelper(Vertex<Integer> current, Set<Vertex<Integer>> vists)
  {
    if(current == null || vists.contains(current)) return true;

    if(current.data % 2 == 0) return false;
    vists.add(current);

    for(Vertex<Integer> neighbor : current.neighbors)
    {
      if(!allOddHelper(neighbor, vists)) return false;
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
    if(start == null || end == null) return false;
    
    Set<Vertex<Integer>> set = new HashSet<>();

    return strictlyIncreasingHelper(start, end, set);
  }

  public boolean strictlyIncreasingHelper(Vertex<Integer> start, Vertex<Integer> end, Set<Vertex<Integer>> vists)
  {
    if(start == end) return true;

    vists.add(start);

    for(Vertex<Integer> neighbor : start.neighbors)
    {
      if(!vists.contains(neighbor) && neighbor.data > start.data)
      {
        if(strictlyIncreasingHelper(neighbor, end, vists) == true)
        {
          return true;
        }
      }
    }

    return false;
  }
}

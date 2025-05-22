import java.util.*;
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
    printVertexVals(vertex, visited);
  }

  private static <T> void printVertexVals(Vertex<T> vertex, Set<Vertex<T>> visited)
  {
    if(vertex == null || visited.contains(vertex))
    {
      return;
    }

    System.out.println(vertex.data);
    visited.add(vertex);

    for(Vertex<T> neighbor: vertex.neighbors)
    {
      printVertexVals(neighbor, visited);
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
    Set<Vertex<T>> reached = new HashSet<>();
    
    reachable(vertex, reached);

    return reached;
  }

  private static <T> void reachable(Vertex<T> vertex, Set<Vertex<T>> reached)
  {
    if(vertex == null || reached.contains(vertex))
    {
      return;
    }

    reached.add(vertex);

    for(Vertex<T> n : vertex.neighbors)
    {
      reachable(n, reached);
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
    List<Integer> nums = new ArrayList<>();

    return max(vertex, nums);    
  }

  private int max(Vertex<Integer> vertex, List<Integer> nums)
  {
    if(vertex == null || nums.contains(vertex.data))
    {
      return Integer.MIN_VALUE;
    }

    nums.add(vertex.data);
    int max = vertex.data;

    for(Vertex<Integer> n : vertex.neighbors)
    {
       max = Math.max(max, max(n, nums));
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
    Set<Vertex<T>> res = new HashSet<>();
    Set<Vertex<T>> visited = new HashSet<>();

    leaves(vertex, res, visited);

    return res;
  }
  
  private static <T> void leaves(Vertex<T> vertex, Set<Vertex<T>> res, Set<Vertex<T>> visited)
  {
    if(vertex == null || visited.contains(vertex))
    {
      return;
    }

    visited.add(vertex);

    if(vertex.neighbors.isEmpty())
    {
      res.add(vertex);
    }

    for(Vertex<T> n : vertex.neighbors)
    {
      leaves(n, res, visited);
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
    Set<Vertex<Integer>> visited = new HashSet<>();

    return allOdd(vertex, visited);
  }

  private boolean allOdd(Vertex<Integer> vertex, Set<Vertex<Integer>> visited)
  {
    if(vertex == null || visited.contains(vertex))
    {
      return true;
    }

    visited.add(vertex);

    if(vertex.data % 2 == 0)
    {
      return false;
    }

    for (Vertex<Integer> n : vertex.neighbors) 
    {
      if (!allOdd(n, visited)) 
      {
        return false;
      }
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
    Set<Vertex<Integer>> visited = new HashSet<>();

    if(start == null || end == null)
    {
      throw new NullPointerException("start or end are null");
    }

    return hasStrictlyIncreasingPath(start, end, visited);
  }

  private boolean hasStrictlyIncreasingPath(Vertex<Integer> current, Vertex<Integer> end, Set<Vertex<Integer>> visited)
  {
    if(current == end)
    {
      return true;
    }

    visited.add(current);

    for(Vertex<Integer> n : current.neighbors)
    {
      if(!visited.contains(n) && current.data < n.data)
      {
        if(hasStrictlyIncreasingPath(n, end, visited))
        {
          return true;
        }
      }
    }
    return false;
  }
}

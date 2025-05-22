import java.util.HashSet;
import java.util.Set;

/**
 * A utility class providing various graph traversal methods using DFS.
 */
public class Practice 
{

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
      Set<Vertex<T>> visited = new HashSet<>();
      printVertexVals(vertex, visited);

  }
public static <T> void printVertexVals(Vertex<T> vertex, Set<Vertex<T>> visited) 
  {   
    if(vertex == null || visited.contains(vertex)) return;
    
    System.out.println(vertex.data);
    visited.add(vertex);
  
    for(Vertex<T> neighbor : vertex.neighbors) 
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
  public <T> Set<Vertex<T>> reachable(Vertex<T> vertex) 
  {
    //this tracks what has been visisted, base case
    Set<Vertex<T>> visited = new HashSet<>(); 
    //helper method
    return reachable(vertex, visited); 
  }

     public <T> Set<Vertex<T>> reachable(Vertex<T> vertex, Set<Vertex<T>> visited)
     {
      //stop if null or already visited
      //if been to visited then just return visited
      if (vertex == null || visited.contains(vertex)) return visited;
      // track visited
      visited.add(vertex);
      //go inside and look for neighbors
      //the property nieghbors is a list 
      for (Vertex<T> neighbor : vertex.neighbors)
      {
        reachable(neighbor, visited);//recurse to visit neighbor
      }

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
    //if the unput is null return the min integer
    if (vertex == null) return Integer.MIN_VALUE;
    //this is the tracker set to see what we have visited, makes sure we dont go in loops
    Set<Vertex<Integer>> visited = new HashSet<>();
    //recurse teh vertex and return the max
    return max(vertex, visited);
    }

  private int max(Vertex<Integer> vertex, Set<Vertex<Integer>> visited) 
  {
    //if vertext is null or visited already retyr the min value
    //base case prevents endless loops
    if (vertex == null || visited.contains(vertex)) return Integer.MIN_VALUE;
    //mark vertex as visited so dont go there again
    visited.add(vertex);

    //starter variable max is current value
    int max = vertex.data;
    //visit each nieghbpr 
    for (Vertex<Integer> neighbor : vertex.neighbors) 
    {
      //recurse and find max from neighbor then compare to current max
      max = Math.max(max, max(neighbor, visited));
    }
    //after checks retrun max vale
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
  public <T> Set<Vertex<T>> leaves(Vertex<T> vertex)     
  {
    //tracks the leaves verts
    Set<Vertex<T>> result = new HashSet<>();
    //tracks visits to not visit again
    Set<Vertex<T>> visited = new HashSet<>();
    //calls help method that does the recurs dfs
    leaves(vertex, visited, result);
    //return the final result
    return result;
  }

  private <T> void leaves(Vertex<T> vertex, Set<Vertex<T>> visited, Set<Vertex<T>> result) 
  {
    //base case to not visit again or if nuull
    if (vertex == null || visited.contains(vertex)) return;
    //mark vertex as visited
    visited.add(vertex);

    //if current has no neighbors its a leaf. 
    if (vertex.neighbors.isEmpty()) 
    {
      result.add(vertex); // so we Add it to result set
    }
    //or we continru to look at the neighbors
    for (Vertex<T> neighbor : vertex.neighbors) 
    {
      //go through each neighbor
      leaves(neighbor, visited, result);
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
  
public boolean allOdd(Vertex<Integer> vertex) 
{
  //base case if null return true, assumes its all odd nothing to check 
    if (vertex == null) return true;
    //tracker varibale keeps track of vertices already visited
    Set<Vertex<Integer>> visited = new HashSet<>();
    //calls the function from the help method to recurse depth first search
    return allOdd(vertex, visited);
}

  private boolean allOdd(Vertex<Integer> vertex, Set<Vertex<Integer>> visited) 
  {
    //base case to check if null or visited already then return true
    if (vertex == null || visited.contains(vertex)) return true;
    //if the value mod 2 is even renders false
    if (vertex.data % 2 == 0) return false;
    //or it is visited , (even)
    visited.add(vertex);

    //go through neighbors and chekc them 
    for (Vertex<Integer> neighbor : vertex.neighbors) 
    {
      //if atleast one is even return false
      if (!allOdd(neighbor, visited)) 
      {
        return false;
      }
    }
    //all had odd numbers return true
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
 
  public boolean hasStrictlyIncreasingPath(Vertex<Integer> start, Vertex<Integer> end) 
  {
    //base case if null or end is null 
    //throws NullPointerException();
    if (start == null || end == null) throw new NullPointerException();
    //creating intMinVal variable to hold the value 
    int intMinVal = Integer.MIN_VALUE; 
    //create the set and tracker named visited
    Set<Vertex<Integer>> visited = new HashSet<>();

    //start dfs from the start?
    //this calls the overloaded method and return the overloaded method return 
    return hasStrictlyIncreasingPath(start, end, visited, intMinVal);
  }

  private boolean hasStrictlyIncreasingPath(Vertex<Integer> start, Vertex<Integer> end, Set<Vertex<Integer>> visited, int intMinVal) 
{
  //base case intMinVal has to be greater than current data 
  //if start is greater than intMinVal or visited already return false
    if (start.data <= intMinVal || visited.contains(start)) return false;

    //if start equals end we have found the path
    if (start == end) return true;
    //set as visited
    visited.add(start);
    //going through the neighbors to check 
    for (Vertex<Integer> neighbor : start.neighbors) 
    {
      //passing in start.data 
      if (hasStrictlyIncreasingPath(neighbor, end, visited, start.data)) 
      {
        return true;
      }
    }
    return false;
  }

}

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/**
 * Your implementation of various different graph algorithms.
 *
 * @author Rishi Machanpalli
 * @userid rmachanpalli3
 * @GTID 903693482
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null || !graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Arguments can't be null");
        }
        List<Vertex<T>> visitedSet = new ArrayList<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        visitedSet.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            Vertex<T> tempVertex = queue.remove();
            for (VertexDistance<T> w : graph.getAdjList().get(tempVertex)) {
                if (!visitedSet.contains(w.getVertex())) {
                    visitedSet.add(w.getVertex());
                    queue.add(w.getVertex());
                }
            }
        }
        return visitedSet;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * all points for this method.
     *
     * You may import/use java.util.Set, java.util.List, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null || !graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Start or graph is null.");
        }
        List<Vertex<T>> visitedSet = new ArrayList<>();
        dfsHelper(start, graph, visitedSet);
        return visitedSet;
    }
    /**
     * dfs Helper method.
     * @param vertex the vertex where the dfs on.
     * @param graph the graph to search through.
     * @param visitedSet a list of the verticies  visited.
     * @param <T> the generic typing of the data.
     */
    private static <T> void dfsHelper(Vertex<T> vertex, Graph<T> graph, List<Vertex<T>> visitedSet) {
        visitedSet.add(vertex);
        for (VertexDistance<T> w : graph.getAdjList().get(vertex)) {
            if (!visitedSet.contains(w.getVertex())) {
                dfsHelper(w.getVertex(), graph, visitedSet);
            }
        }
    }
    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Map, and java.util.Set and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (start == null || graph == null || !graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Start or graph is null.");
        }
        Set<Vertex<T>> visitedSet = new HashSet<>();
        Map<Vertex<T>, Integer> result = new HashMap<>();
        Queue<VertexDistance<T>> queue = new PriorityQueue<>();
        int full = 0;
        for (Vertex<T> v : graph.getVertices()) {
            if (v.equals(start)) {
                result.put(v, 0);
            } else {
                result.put(v, Integer.MAX_VALUE);
            }
            full++;
        }
        int count = 0;
        queue.add(new VertexDistance<>(start, 0));
        while (!queue.isEmpty() && count != full ) {
            VertexDistance<T> tempVertex = queue.remove();
            if (!visitedSet.contains(tempVertex.getVertex())) {
                visitedSet.add(tempVertex.getVertex());
                result.put(tempVertex.getVertex(), tempVertex.getDistance());
                for (VertexDistance<T> w : graph.getAdjList().get(tempVertex.getVertex())) {
                    if (!visitedSet.contains(w.getVertex())) {
                        queue.add(new VertexDistance<>(w.getVertex(), tempVertex.getDistance() + w.getDistance()));
                    }
                }
            }
        }
        return result;
    }
    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use PriorityQueue, java.util.Set, and any class that 
     * implements the aforementioned interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for this method (storing the adjacency list in a variable is fine).
     *
     * @param <T> the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */

    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null || !graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Start or graph is null.");
        }
        Set<Vertex<T>> visitedSet = new HashSet<>();
        Set<Edge<T>> edgeSet = new HashSet<>();
        Queue<Edge<T>> queue = new PriorityQueue<>();
        Map<Vertex<T>, List<VertexDistance<T>>> vMap = graph.getAdjList();
        for (VertexDistance<T> v: vMap.get(start)) {
            queue.add(new Edge<>(start, v.getVertex(), v.getDistance()));
        }
        visitedSet.add(start);
        while (!queue.isEmpty() && !(visitedSet.size() == graph.getVertices().size())) {
            Edge<T> tempEdge = queue.remove();

            if (!visitedSet.contains(tempEdge.getV())) {
                visitedSet.add(tempEdge.getV());
                edgeSet.add(new Edge<>(tempEdge.getU(), tempEdge.getV(), tempEdge.getWeight()));
                edgeSet.add(new Edge<>(tempEdge.getV(), tempEdge.getU(), tempEdge.getWeight()));
                for (VertexDistance<T> v: vMap.get(tempEdge.getV())) {
                    if (!visitedSet.contains(v.getVertex())) {
                        queue.add(new Edge<>(tempEdge.getV(), v.getVertex(), v.getDistance()));
                    }
                }
            }
        }
        if ((edgeSet.size() / 2) == graph.getVertices().size() - 1) {
            return edgeSet;
        } else {
            return null;
        }
    }
}
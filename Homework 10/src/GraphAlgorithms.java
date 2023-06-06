import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Yu-Hsin Chen
 * @userid ychen3558
 * @GTID 903789607
 * @version 11.0.16.1
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
        if (start == null) {
            throw new IllegalArgumentException("Start is null");
        }
        if (graph == null) {
            throw new IllegalArgumentException("Graph is null");
        }
        if (graph.getAdjList().get(start) == null) {
            throw new IllegalArgumentException("Start does not exist in graph");
        }

        List<Vertex<T>> visitedList = new LinkedList<>();
        Set<Vertex<T>> visitedSet = new HashSet<>();
        List<Vertex<T>> queue = new LinkedList<>();
        visitedList.add(start);
        visitedSet.add(start);
        queue.add(start);

        int graphSize = graph.getVertices().size();

        while (queue.size() != 0 && graphSize != visitedSet.size()) {
            Vertex<T> current = queue.remove(0);
            for (VertexDistance<T> vertex : graph.getAdjList().get(current)) {
                Vertex<T> adjacent = vertex.getVertex();
                if (!visitedSet.contains(adjacent)) {
                    visitedList.add((adjacent));
                    visitedSet.add(adjacent);
                    queue.add(adjacent);
                }
            }
        }
        return visitedList;
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
        if (start == null) {
            throw new IllegalArgumentException("Start is null");
        }
        if (graph == null) {
            throw new IllegalArgumentException("Graph is null");
        }
        if (graph.getAdjList().get(start) == null) {
            throw new IllegalArgumentException("Start does not exist in graph");
        }

        List<Vertex<T>> visitedList = new LinkedList<>();
        Set<Vertex<T>> visitedSet = new HashSet<>();
        int graphSize = graph.getVertices().size();
        dfsRecursive(start, graph, visitedList, visitedSet, graphSize);

        return visitedList;
    }

    /**
     * Recursive helper method to facilitate with the process of dfs search.
     * @param current the current vertex the dfs search is on
     * @param graph the graph to search through
     * @param visitedList the list of vertices that the search algorithm has visited
     * @param visitedSet the set of vertices that the search algorithm has visited
     * @param size the int size of vertices in the graph
     * @param <T> the generic typing of the data
     */
    private static <T> void dfsRecursive(Vertex<T> current, Graph<T> graph, List<Vertex<T>> visitedList,
                                         Set<Vertex<T>> visitedSet, int size) {
        visitedList.add(current);
        visitedSet.add(current);

        if (size == visitedList.size()) {
            return;
        }

        for (VertexDistance<T> vertex : graph.getAdjList().get(current)) {
            Vertex<T> adjacent = vertex.getVertex();
            if (!visitedSet.contains(adjacent)) {
                dfsRecursive(adjacent, graph, visitedList, visitedSet, size);
                if (size == visitedList.size()) {
                    return;
                }
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
        if (start == null) {
            throw new IllegalArgumentException("Start is null");
        }
        if (graph == null) {
            throw new IllegalArgumentException("Graph is null");
        }
        if (graph.getAdjList().get(start) == null) {
            throw new IllegalArgumentException("Start does not exist in graph");
        }

        Map<Vertex<T>, Integer> shortestPath = new HashMap<>();
        PriorityQueue<VertexDistance<T>> priorityQueue = new PriorityQueue<>();
        Set<Vertex<T>> visitedSet = new HashSet<>();
        int graphSize = graph.getVertices().size();

        priorityQueue.add(new VertexDistance<T>(start, 0));

        for (Vertex<T> temp1 : graph.getVertices()) {
            if (temp1.equals(start)) {
                shortestPath.put(temp1, 0);
            } else {
                shortestPath.put(temp1, Integer.MAX_VALUE);
            }
        }

        while (!priorityQueue.isEmpty() && visitedSet.size() != graphSize) {
            VertexDistance<T> current = priorityQueue.remove();

            if (!visitedSet.contains(current.getVertex())) {
                visitedSet.add((current.getVertex()));

                for (VertexDistance<T> temp : graph.getAdjList().get(current.getVertex())) {
                    int distance = shortestPath.get(current.getVertex());
                    int totalDistance = distance + temp.getDistance();

                    if (totalDistance < shortestPath.get(temp.getVertex())) {
                        shortestPath.put(temp.getVertex(), totalDistance);
                        priorityQueue.add(new VertexDistance<T>(temp.getVertex(), totalDistance));
                    }
                }
            }
        }
        return shortestPath;
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
        if (start == null) {
            throw new IllegalArgumentException("Start is null");
        }
        if (graph == null) {
            throw new IllegalArgumentException("Graph is null");
        }
        if (graph.getAdjList().get(start) == null) {
            throw new IllegalArgumentException("Start does not exist in graph");
        }
        Set<Vertex<T>> visitedSet = new HashSet<>();
        Set<Edge<T>> mstEdgeSet = new HashSet<>();
        PriorityQueue<Edge<T>> priorityQueue = new PriorityQueue<>();
        int graphSize = graph.getVertices().size();

        for (VertexDistance<T> temp1 : graph.getAdjList().get(start)) {
            priorityQueue.add(new Edge<>(start, temp1.getVertex(), temp1.getDistance()));
        }

        visitedSet.add(start);

        while (!priorityQueue.isEmpty() && visitedSet.size() != graphSize) {
            Edge<T> current = priorityQueue.remove();
            if (!visitedSet.contains(current.getV())) {
                mstEdgeSet.add(new Edge<>(current.getU(), current.getV(), current.getWeight()));
                mstEdgeSet.add(new Edge<>(current.getV(), current.getU(), current.getWeight()));
                visitedSet.add(current.getV());

                for (VertexDistance<T> temp : graph.getAdjList().get(current.getV())) {
                    if (!visitedSet.contains(temp.getVertex())) {
                        priorityQueue.add(new Edge<>(current.getV(), temp.getVertex(), temp.getDistance()));
                    }
                }
            }
        }
        if (mstEdgeSet.size() != 2 * (graphSize - 1)) {
            return null;
        }

        return mstEdgeSet;
    }
}
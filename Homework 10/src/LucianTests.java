import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Graph Algorithm Tests
 *
 * @author Lucian Tash
 * @version 1.0
 */
public class LucianTests {

    private Graph<Character> graph;
    public static final int TIMEOUT = 200;

    @Before
    public void start() {
        graph = createUndirectedGraph();
    }


    /**
     *          A — B
     *          |   \     G       <--- G is not connected to graph
     *      F — C —— D
     *           \  / \
     *            E    H
     *
     * @return the completed graph
     */
    private Graph<Character> createUndirectedGraph() {
        Set<Vertex<Character>> vertices = new HashSet<>();
        vertices.add(new Vertex<>('A'));
        vertices.add(new Vertex<>('B'));
        vertices.add(new Vertex<>('C'));
        vertices.add(new Vertex<>('D'));
        vertices.add(new Vertex<>('E'));
        vertices.add(new Vertex<>('F'));
        vertices.add(new Vertex<>('G'));
        vertices.add(new Vertex<>('H'));

        Set<Edge<Character>> edges = new LinkedHashSet<>();
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 1));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('C'), 7));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 1));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('D'), 6));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('A'), 7));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 10));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('E'), 2));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('F'), 5));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('B'), 6));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 10));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 3));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('H'), 4));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('C'), 2));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 3));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('C'), 5));
        edges.add(new Edge<>(new Vertex<>('H'), new Vertex<>('D'), 4));

        return new Graph<>(vertices, edges);
    }

    @Test(timeout = TIMEOUT)
    public void testBFS_A() {
        // Test starting at vertex A
        List<Vertex<Character>> result = GraphAlgorithms.bfs(new Vertex<>('A'), graph);
        List<Vertex<Character>> expected = new LinkedList<>();
        expected.add(new Vertex<>('A'));
        expected.add(new Vertex<>('B'));
        expected.add(new Vertex<>('C'));
        expected.add(new Vertex<>('D'));
        expected.add(new Vertex<>('E'));
        expected.add(new Vertex<>('F'));
        expected.add(new Vertex<>('H'));
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testBFS_B() {
        // Test starting at vertex B
        List<Vertex<Character>> result = GraphAlgorithms.bfs(new Vertex<>('B'), graph);
        List<Vertex<Character>> expected = new LinkedList<>();
        expected.add(new Vertex<>('B'));
        expected.add(new Vertex<>('A'));
        expected.add(new Vertex<>('D'));
        expected.add(new Vertex<>('C'));
        expected.add(new Vertex<>('E'));
        expected.add(new Vertex<>('H'));
        expected.add(new Vertex<>('F'));
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testBFS_C() {
        // Test starting at vertex C
        List<Vertex<Character>> result = GraphAlgorithms.bfs(new Vertex<>('C'), graph);
        List<Vertex<Character>> expected = new LinkedList<>();
        expected.add(new Vertex<>('C'));
        expected.add(new Vertex<>('A'));
        expected.add(new Vertex<>('D'));
        expected.add(new Vertex<>('E'));
        expected.add(new Vertex<>('F'));
        expected.add(new Vertex<>('B'));
        expected.add(new Vertex<>('H'));
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testBFS_D() {
        // Test starting at vertex D
        List<Vertex<Character>> result = GraphAlgorithms.bfs(new Vertex<>('D'), graph);
        List<Vertex<Character>> expected = new LinkedList<>();
        expected.add(new Vertex<>('D'));
        expected.add(new Vertex<>('B'));
        expected.add(new Vertex<>('C'));
        expected.add(new Vertex<>('E'));
        expected.add(new Vertex<>('H'));
        expected.add(new Vertex<>('A'));
        expected.add(new Vertex<>('F'));
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testBFS_E() {
        // Test starting at vertex E
        List<Vertex<Character>> result = GraphAlgorithms.bfs(new Vertex<>('E'), graph);
        List<Vertex<Character>> expected = new LinkedList<>();
        expected.add(new Vertex<>('E'));
        expected.add(new Vertex<>('C'));
        expected.add(new Vertex<>('D'));
        expected.add(new Vertex<>('A'));
        expected.add(new Vertex<>('F'));
        expected.add(new Vertex<>('B'));
        expected.add(new Vertex<>('H'));
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testBFS_F() {
        // Test starting at vertex F
        List<Vertex<Character>> result = GraphAlgorithms.bfs(new Vertex<>('F'), graph);
        List<Vertex<Character>> expected = new LinkedList<>();
        expected.add(new Vertex<>('F'));
        expected.add(new Vertex<>('C'));
        expected.add(new Vertex<>('A'));
        expected.add(new Vertex<>('D'));
        expected.add(new Vertex<>('E'));
        expected.add(new Vertex<>('B'));
        expected.add(new Vertex<>('H'));
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testBFS_G() {
        // Test starting at vertex G
        List<Vertex<Character>> result = GraphAlgorithms.bfs(new Vertex<>('G'), graph);
        List<Vertex<Character>> expected = new LinkedList<>();
        expected.add(new Vertex<>('G'));
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testBFS_H() {
        // Test starting at vertex H
        List<Vertex<Character>> result = GraphAlgorithms.bfs(new Vertex<>('H'), graph);
        List<Vertex<Character>> expected = new LinkedList<>();
        expected.add(new Vertex<>('H'));
        expected.add(new Vertex<>('D'));
        expected.add(new Vertex<>('B'));
        expected.add(new Vertex<>('C'));
        expected.add(new Vertex<>('E'));
        expected.add(new Vertex<>('A'));
        expected.add(new Vertex<>('F'));
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_A() {
        // Test starting at vertex A
        List<Vertex<Character>> result = GraphAlgorithms.dfs(new Vertex<>('A'), graph);
        List<Vertex<Character>> expected = new LinkedList<>();
        expected.add(new Vertex<>('A'));
        expected.add(new Vertex<>('B'));
        expected.add(new Vertex<>('D'));
        expected.add(new Vertex<>('C'));
        expected.add(new Vertex<>('E'));
        expected.add(new Vertex<>('F'));
        expected.add(new Vertex<>('H'));
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_B() {
        // Test starting at vertex B
        List<Vertex<Character>> result = GraphAlgorithms.dfs(new Vertex<>('B'), graph);
        List<Vertex<Character>> expected = new LinkedList<>();
        expected.add(new Vertex<>('B'));
        expected.add(new Vertex<>('A'));
        expected.add(new Vertex<>('C'));
        expected.add(new Vertex<>('D'));
        expected.add(new Vertex<>('E'));
        expected.add(new Vertex<>('H'));
        expected.add(new Vertex<>('F'));
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_C() {
        // Test starting at vertex C
        List<Vertex<Character>> result = GraphAlgorithms.dfs(new Vertex<>('C'), graph);
        List<Vertex<Character>> expected = new LinkedList<>();
        expected.add(new Vertex<>('C'));
        expected.add(new Vertex<>('A'));
        expected.add(new Vertex<>('B'));
        expected.add(new Vertex<>('D'));
        expected.add(new Vertex<>('E'));
        expected.add(new Vertex<>('H'));
        expected.add(new Vertex<>('F'));
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_D() {
        // Test starting at vertex D
        List<Vertex<Character>> result = GraphAlgorithms.dfs(new Vertex<>('D'), graph);
        List<Vertex<Character>> expected = new LinkedList<>();
        expected.add(new Vertex<>('D'));
        expected.add(new Vertex<>('B'));
        expected.add(new Vertex<>('A'));
        expected.add(new Vertex<>('C'));
        expected.add(new Vertex<>('E'));
        expected.add(new Vertex<>('F'));
        expected.add(new Vertex<>('H'));
        assertEquals(expected, result); // Check amount & order of vertices returned
    }
    @Test(timeout = TIMEOUT)
    public void testDFS_E() {
        // Test starting at vertex E
        List<Vertex<Character>> result = GraphAlgorithms.dfs(new Vertex<>('E'), graph);
        List<Vertex<Character>> expected = new LinkedList<>();
        expected.add(new Vertex<>('E'));
        expected.add(new Vertex<>('C'));
        expected.add(new Vertex<>('A'));
        expected.add(new Vertex<>('B'));
        expected.add(new Vertex<>('D'));
        expected.add(new Vertex<>('H'));
        expected.add(new Vertex<>('F'));
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_F() {
        // Test starting at vertex F
        List<Vertex<Character>> result = GraphAlgorithms.dfs(new Vertex<>('F'), graph);
        List<Vertex<Character>> expected = new LinkedList<>();
        expected.add(new Vertex<>('F'));
        expected.add(new Vertex<>('C'));
        expected.add(new Vertex<>('A'));
        expected.add(new Vertex<>('B'));
        expected.add(new Vertex<>('D'));
        expected.add(new Vertex<>('E'));
        expected.add(new Vertex<>('H'));
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_G() {
        // Test starting at vertex G
        List<Vertex<Character>> result = GraphAlgorithms.dfs(new Vertex<>('G'), graph);
        List<Vertex<Character>> expected = new LinkedList<>();
        expected.add(new Vertex<>('G'));
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_H() {
        // Test starting at vertex H
        List<Vertex<Character>> result = GraphAlgorithms.dfs(new Vertex<>('H'), graph);
        List<Vertex<Character>> expected = new LinkedList<>();
        expected.add(new Vertex<>('H'));
        expected.add(new Vertex<>('D'));
        expected.add(new Vertex<>('B'));
        expected.add(new Vertex<>('A'));
        expected.add(new Vertex<>('C'));
        expected.add(new Vertex<>('E'));
        expected.add(new Vertex<>('F'));
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_A() {
        // Test starting at vertex A
        Map<Vertex<Character>, Integer> result = GraphAlgorithms.dijkstras(new Vertex<>('A'), graph);
        Map<Vertex<Character>, Integer> expected = new HashMap<>();
        expected.put(new Vertex<>('A'), 0);
        expected.put(new Vertex<>('B'), 1);
        expected.put(new Vertex<>('C'), 7);
        expected.put(new Vertex<>('D'), 7);
        expected.put(new Vertex<>('E'), 9);
        expected.put(new Vertex<>('F'), 12);
        expected.put(new Vertex<>('G'), Integer.MAX_VALUE);
        expected.put(new Vertex<>('H'), 11);
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_B() {
        // Test starting at vertex B
        Map<Vertex<Character>, Integer> result = GraphAlgorithms.dijkstras(new Vertex<>('B'), graph);
        Map<Vertex<Character>, Integer> expected = new HashMap<>();
        expected.put(new Vertex<>('A'), 1);
        expected.put(new Vertex<>('B'), 0);
        expected.put(new Vertex<>('C'), 8);
        expected.put(new Vertex<>('D'), 6);
        expected.put(new Vertex<>('E'), 9);
        expected.put(new Vertex<>('F'), 13);
        expected.put(new Vertex<>('G'), Integer.MAX_VALUE);
        expected.put(new Vertex<>('H'), 10);
        assertEquals(expected, result); // Check amount & order of vertices returned
    }


    @Test(timeout = TIMEOUT)
    public void testDijkstras_C() {
        // Test starting at vertex C
        Map<Vertex<Character>, Integer> result = GraphAlgorithms.dijkstras(new Vertex<>('C'), graph);
        Map<Vertex<Character>, Integer> expected = new HashMap<>();
        expected.put(new Vertex<>('A'), 7);
        expected.put(new Vertex<>('B'), 8);
        expected.put(new Vertex<>('C'), 0);
        expected.put(new Vertex<>('D'), 5);
        expected.put(new Vertex<>('E'), 2);
        expected.put(new Vertex<>('F'), 5);
        expected.put(new Vertex<>('G'), Integer.MAX_VALUE);
        expected.put(new Vertex<>('H'), 9);
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_D() {
        // Test starting at vertex D
        Map<Vertex<Character>, Integer> result = GraphAlgorithms.dijkstras(new Vertex<>('D'), graph);
        Map<Vertex<Character>, Integer> expected = new HashMap<>();
        expected.put(new Vertex<>('A'), 7);
        expected.put(new Vertex<>('B'), 6);
        expected.put(new Vertex<>('C'), 5);
        expected.put(new Vertex<>('D'), 0);
        expected.put(new Vertex<>('E'), 3);
        expected.put(new Vertex<>('F'), 10);
        expected.put(new Vertex<>('G'), Integer.MAX_VALUE);
        expected.put(new Vertex<>('H'), 4);
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_E() {
        // Test starting at vertex E
        Map<Vertex<Character>, Integer> result = GraphAlgorithms.dijkstras(new Vertex<>('E'), graph);
        Map<Vertex<Character>, Integer> expected = new HashMap<>();
        expected.put(new Vertex<>('A'), 9);
        expected.put(new Vertex<>('B'), 9);
        expected.put(new Vertex<>('C'), 2);
        expected.put(new Vertex<>('D'), 3);
        expected.put(new Vertex<>('E'), 0);
        expected.put(new Vertex<>('F'), 7);
        expected.put(new Vertex<>('G'), Integer.MAX_VALUE);
        expected.put(new Vertex<>('H'), 7);
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_F() {
        // Test starting at vertex F
        Map<Vertex<Character>, Integer> result = GraphAlgorithms.dijkstras(new Vertex<>('F'), graph);
        Map<Vertex<Character>, Integer> expected = new HashMap<>();
        expected.put(new Vertex<>('A'), 12);
        expected.put(new Vertex<>('B'), 13);
        expected.put(new Vertex<>('C'), 5);
        expected.put(new Vertex<>('D'), 10);
        expected.put(new Vertex<>('E'), 7);
        expected.put(new Vertex<>('F'), 0);
        expected.put(new Vertex<>('G'), Integer.MAX_VALUE);
        expected.put(new Vertex<>('H'), 14);
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_G() {
        // Test starting at vertex G
        Map<Vertex<Character>, Integer> result = GraphAlgorithms.dijkstras(new Vertex<>('G'), graph);
        Map<Vertex<Character>, Integer> expected = new HashMap<>();
        expected.put(new Vertex<>('A'), Integer.MAX_VALUE);
        expected.put(new Vertex<>('B'), Integer.MAX_VALUE);
        expected.put(new Vertex<>('C'), Integer.MAX_VALUE);
        expected.put(new Vertex<>('D'), Integer.MAX_VALUE);
        expected.put(new Vertex<>('E'), Integer.MAX_VALUE);
        expected.put(new Vertex<>('F'), Integer.MAX_VALUE);
        expected.put(new Vertex<>('G'), 0);
        expected.put(new Vertex<>('H'), Integer.MAX_VALUE);
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_H() {
        // Test starting at vertex H
        Map<Vertex<Character>, Integer> result = GraphAlgorithms.dijkstras(new Vertex<>('H'), graph);
        Map<Vertex<Character>, Integer> expected = new HashMap<>();
        expected.put(new Vertex<>('A'), 11);
        expected.put(new Vertex<>('B'), 10);
        expected.put(new Vertex<>('C'), 9);
        expected.put(new Vertex<>('D'), 4);
        expected.put(new Vertex<>('E'), 7);
        expected.put(new Vertex<>('F'), 14);
        expected.put(new Vertex<>('G'), Integer.MAX_VALUE);
        expected.put(new Vertex<>('H'), 0);
        assertEquals(expected, result); // Check amount & order of vertices returned
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDFSStartNotInGraph() {
        GraphAlgorithms.dfs(new Vertex<>('L'), graph);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDFSNullGraph() {
        GraphAlgorithms.dfs(new Vertex<>('A'), null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDFSNullStart() {
        GraphAlgorithms.dfs(null, graph);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBFSStartNotInGraph() {
        GraphAlgorithms.bfs(new Vertex<>('L'), graph);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBFSNullGraph() {
        GraphAlgorithms.bfs(new Vertex<>('A'), null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBFSNullStart() {
        GraphAlgorithms.bfs(null, graph);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDijkstrasStartNotInGraph() {
        GraphAlgorithms.dijkstras(new Vertex<>('L'), graph);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDijkstrasNullGraph() {
        GraphAlgorithms.dijkstras(new Vertex<>('A'), null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDijkstrasNullStart() {
        GraphAlgorithms.dijkstras(null, graph);
    }
}
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class GraphOne {

    int[][] graph;
    int v;

    GraphOne(int v) {
        this.v = v;
        this.graph = new int[v][v];
    }

    void bfs(int start) {
        boolean[] visited = new boolean[v];
        Arrays.fill(visited, false);
        visited[start] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            int current = q.poll();
            System.out.print(current + " ");
            for (int i = 0; i < v; i++) {
                if (graph[current][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }



    public static void main(String[] args) {
        GraphOne graph = new GraphOne(6);
        graph.graph = new int[][] {
                { 0, 7, 9, 0, 0, 14 },
                { 7, 0, 10, 15, 0, 0 },
                { 9, 10, 0, 11, 0, 2 },
                { 0, 15, 11, 0, 6, 0 },
                { 0, 0, 0, 6, 0, 9 },
                { 14, 0, 2, 0, 9, 0 }
        };

        // graph.bfs(0);
        // graph.dfs();
        // graph.mst();

    }

} 
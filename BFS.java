import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    int graph[][];
    int v;
    BFS(int  v ){
        graph = new int[v][v];
        this.v = v;
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
        BFS graph = new BFS(9);

        graph.graph = new int[][]{
                { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, // Node 0
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 }, // Node 1
                { 0, 8, 0, 7, 0, 4, 2, 0, 0 }, // Node 2
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, // Node 3
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 }, // Node 4
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 }, // Node 5
                { 0, 0, 2, 0, 0, 2, 0, 1, 6 }, // Node 6
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 }, // Node 7
                { 0, 0, 0, 0, 0, 0, 6, 7, 0 } // Node 8
        };
        graph.bfs(0);
    }
}

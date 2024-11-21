import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {

    int v;
    int[][] graph;

    Dijkstra(int v) {
        this.v = v;
        this.graph = new int[v][v];
    }

    public void SingleSourceShortestPath(int start, int destination) {
        int[] cost = new int[v];
        boolean[] visited = new boolean[v];
        int[] parent = new int[v];
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        Arrays.fill(cost, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        Arrays.fill(parent, -1); // Initialize all parents to -1

        cost[start] = 0;
        q.add(new int[] { 0, start });

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int u = current[1];

            if (visited[u])
                continue;
            visited[u] = true;

            for (int i = 0; i < v; i++) {
                if (graph[u][i] != 0 && !visited[i]) {
                    int newCost = cost[u] + graph[u][i];
                    if (newCost < cost[i]) {
                        cost[i] = newCost;
                        parent[i] = u; // Update the parent
                        q.add(new int[] { newCost, i });
                    }
                }
            }
        }

        // Print the path from source to destination
        System.out.print("Shortest path from " + start + " to " + destination + ": ");
        printPath(parent, destination);
        System.out.println(); // End the path with a newline
    }

    private void printPath(int[] parent, int node) {
        if (node == -1) {
            return;
        }
        printPath(parent, parent[node]);
        System.out.print(node + " "); // Print the node in the path
    }

    public static void main(String[] args) {
        Dijkstra graph = new Dijkstra(9);
        graph.graph = new int[][] {
            { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
            { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
            { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
            { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
            { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
            { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
            { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
            { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
            { 0, 0, 2, 0, 0, 0, 6, 7, 0 }
        };

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the source node: ");
        int source = scanner.nextInt();
        System.out.print("Enter the destination node: ");
        int destination = scanner.nextInt();

        graph.SingleSourceShortestPath(source, destination);
        scanner.close();
    }
}

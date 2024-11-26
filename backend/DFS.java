package backend;
import java.util.Arrays;

public class DFS {

    int v;
    int[][] graph;

    DFS(int v) {
        this.v = v;
        this.graph = new int[v][v];
    }

    void dfsUtil(int start, boolean[] visited, int[] parent) {
        visited[start] = true;

        for (int i = 0; i < v; i++) {
            if (graph[start][i] != 0 && !visited[i]) {
                parent[i] = start; // Update parent of the current node
                System.out.print(start + " " + i + " ");
                dfsUtil(i, visited, parent);
            }
        }
    }

    void dfs() {
        int[] parent = new int[v];
        boolean[] visited = new boolean[v];

        Arrays.fill(visited, false);
        Arrays.fill(parent, -1);
        dfsUtil(0, visited, parent);
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java DFS <adj_matrix_values>");
            System.out.println("Example: java DFS \"0 4 0,4 0 8,0 8 0\"");
            return;
        }

        try {
            // Step 1: Parse the graph input string
            String graphInput = args[0];
            String[] rows = graphInput.split(","); // Split by commas to get each row of the adjacency matrix
            int v = rows.length; // Number of vertices (equal to the number of rows)

            // Step 2: Parse each row into the graph's adjacency matrix
            DFS graph = new DFS(v);
            for (int i = 0; i < v; i++) {
                String[] row = rows[i].trim().split(" "); // Split each row by spaces to get the values
                for (int j = 0; j < v; j++) {
                    graph.graph[i][j] = Integer.parseInt(row[j]);
                }
            }

            // Step 3: Perform DFS traversal
            graph.dfs();

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format in the adjacency matrix.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

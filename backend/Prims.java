package backend;

import java.util.Arrays;

public class Prims {

    int v; // Number of vertices
    int[][] graph; // Adjacency matrix representation of the graph

    // Constructor to initialize vertices and graph
    Prims(int v, int[][] graph) {
        this.v = v;
        this.graph = graph;
    }

    // Function to find the vertex with the minimum key value
    int findMinVertex(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < v; i++) {
            if (!mstSet[i] && key[i] < min) {
                min = key[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    // Function to apply Prim's Algorithm
    public void applyPrims() {
        int[] parent = new int[v]; // Array to store the MST
        int[] key = new int[v]; // Key values to pick the minimum weight edge
        boolean[] mstSet = new boolean[v]; // To represent vertices included in the MST

        // Initialize all keys as INFINITE
        Arrays.fill(key, Integer.MAX_VALUE);
        // Include the first vertex in MST
        key[0] = 0; // Make key of the first vertex 0
        parent[0] = -1; // First node is always the root of MST

        for (int count = 0; count < v - 1; count++) {
            // Pick the minimum key vertex not yet included in MST
            int u = findMinVertex(key, mstSet);

            mstSet[u] = true;

            for (int i = 0; i < v; i++) {
                if (graph[u][i] != 0 && !mstSet[i] && graph[u][i] < key[i]) {
                    parent[i] = u;
                    key[i] = graph[u][i];
                }
            }
        }

        // Print the MST
        printMST(parent);
    }

    // Function to print the MST
    void printMST(int[] parent) {
        for (int i = 1; i < v; i++) {
            System.out.print(parent[i] + " " + i + " ");
        }
    }

    public static void main(String[] args) {
        // Check for valid input
        if (args.length < 1) {
            System.out.println("Usage: java Prims \"<adj_matrix_values>\"");
            System.out.println("Example: java Prims \"0 2 0 6 0,2 0 3 8 5,0 3 0 0 7,6 8 0 0 9,0 5 7 9 0\"");
            return;
        }

        try {
            // Parse the graph input string
            String graphInput = args[0];
            String[] rows = graphInput.split(","); // Split by commas to get each row of the adjacency matrix
            int v = rows.length; // Number of vertices

            // Initialize the adjacency matrix
            int[][] adjMatrix = new int[v][v];
            for (int i = 0; i < v; i++) {
                String[] row = rows[i].trim().split(" "); // Split each row by spaces to get the values
                for (int j = 0; j < v; j++) {
                    adjMatrix[i][j] = Integer.parseInt(row[j]);
                }
            }

            // Create a Prims object and apply Prim's Algorithm
            Prims prims = new Prims(v, adjMatrix);
            prims.applyPrims();

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format in the adjacency matrix.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

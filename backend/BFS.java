package backend;

import java.util.*;

public class BFS {
    public static void main(String[] args) {
        // Check if arguments are provided
        if (args.length == 0) {
            System.out.println("Please provide a 2D array input as arguments (e.g., \"0 1 0 0, 1 0 1 0, 0 1 0 1, 0 0 1 0\")");
            return;
        }

      
        String input = args[0]; // Read the 2D array as a single string
        String[] rows = input.split(","); // Split by rows
        int[][] graph = new int[rows.length][]; // Initialize the adjacency matrix

        for (int i = 0; i < rows.length; i++) {
            String[] cols = rows[i].trim().split(" "); // Split by spaces for each row
            graph[i] = new int[cols.length];
            for (int j = 0; j < cols.length; j++) {
                graph[i][j] = Integer.parseInt(cols[j]); // Convert each element to an integer
            }
        }

        // Perform BFS starting from node 0
        bfs(graph, 0);
    }

    // BFS method with parent tracking
    public static void bfs(int[][] graph, int startNode) {
        int numNodes = graph.length;
        boolean[] visited = new boolean[numNodes]; // To track visited nodes
        int[] parent = new int[numNodes]; // To store the parent of each node
        Arrays.fill(parent, -1); // Initialize parent array with -1 (no parent initially)

        Queue<Integer> queue = new LinkedList<>(); // Queue to store the nodes for BFS

        // Start BFS from the given startNode
        visited[startNode] = true;
        parent[startNode] = -1; // Root node has no parent
        queue.add(startNode);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll(); // Get the next node from the queue

            // Print the current node with its parent
            if (parent[currentNode] != -1) {
                System.out.println(parent[currentNode] + " " + currentNode + " ");
            } else {
                // System.out.println(currentNode + " ");
            }

            // Visit all the neighbors of the current node
            for (int neighbor = 0; neighbor < numNodes; neighbor++) {
                if (graph[currentNode][neighbor] != 0 && !visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                    parent[neighbor] = currentNode; // Set the parent of the neighbor
                }
            }
        }
    }
}

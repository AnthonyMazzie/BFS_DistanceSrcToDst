import java.util.*;

class Main {
    static void BFS(Vector<Integer>[] adj, int src, int dist[], int paths[], int quantityNodes) {

        boolean[] visited = new boolean[quantityNodes];

        for (int i = 0; i < quantityNodes; i++) {
            visited[i] = false;
        }

        dist[src] = 0;
        paths[src] = 1;

        Queue<Integer> q = new LinkedList<>();

        q.add(src);
        visited[src] = true;

        while (!q.isEmpty()) {
            int curr = q.peek();
            q.poll();

            /* For all adjacent nodes of current node */
            for (int x : adj[curr]) {

                /* If the current vertex is not yet visited, add to queue */
                if (visited[x] == false) {
                    q.add(x);
                    visited[x] = true;
                }

                /* If new path is better (shorter), update distance, update path */
                if (dist[x] > dist[curr] + 1) {
                    dist[x] = dist[curr] + 1;
                    paths[x] = paths[curr];
                }
                /* Else if additional shortest paths found (of same weight/length/distance) */
                else if (dist[x] == dist[curr] + 1) {
                    paths[x] += paths[curr];
                }
            }
        }
    }

    static void findShortestPaths(Vector<Integer> adj[], int sourceNode, int destNode, int quantityNodes) {

        int[] dist = new int[quantityNodes];
        int[] paths = new int[quantityNodes];

        for (int i = 0; i < quantityNodes; i++)
            dist[i] = Integer.MAX_VALUE;

        for (int i = 0; i < quantityNodes; i++)
            paths[i] = 0;

        BFS(adj, sourceNode, dist, paths, quantityNodes);

        System.out.print("Number of shortest paths from " + sourceNode + " to " + destNode + ": ");
        for (int i = 0; i < quantityNodes; i++) {
            if(i == destNode){
                System.out.print(paths[i] + " ");
            }
        }
    }

    // Utility function, adds an edge to a directed graph
    static void addEdge(Vector<Integer> adj[],
                        int u, int v) {
        adj[u].add(v);
    }

    // Driver Code
    public static void main(String[] args) {
        int n = 13; // Number of vertices

        Vector<Integer>[] adj = new Vector[n];
        for (int i = 0; i < n; i++)
            adj[i] = new Vector<>();

        addEdge(adj, 0, 1);
        addEdge(adj, 0, 2);
        addEdge(adj, 0, 3);

        addEdge(adj, 1, 4);
        addEdge(adj, 2, 4);

        addEdge(adj, 4, 6);
        addEdge(adj, 4, 7);

        addEdge(adj, 6, 9);
        addEdge(adj, 7, 9);

        addEdge(adj, 3, 5);
        addEdge(adj, 5, 8);
        addEdge(adj, 8, 10);
        addEdge(adj, 10, 11);
        addEdge(adj, 11, 12);
        addEdge(adj, 12, 9);

        findShortestPaths(adj, 2, 9, n);
    }
}
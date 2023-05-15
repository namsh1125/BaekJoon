import java.util.*;
import java.io.*;

/*
 * 백준 1240번. 노드사이의 거리
 * https://www.acmicpc.net/problem/1240
 */

public class Main {
    private static int n, m;
    private static ArrayList<Edge>[] graph;
    private static ArrayList<Tc> tcList = new ArrayList<>();

    static class Edge implements Comparable<Edge> {
        int node, dist;

        public Edge(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge other) {
            return dist - other.dist;
        }
    }

    static class Tc {
        int node1, node2;

        public Tc(int node1, int node2) {
            this.node1 = node1;
            this.node2 = node2;
        }
    }

    public static void main(String[] args) throws IOException {
        initVariable();
        String result = getResult();
        printResult(result);
    }

    private static void initVariable() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, d));
            graph[b].add(new Edge(a, d));
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            tcList.add(new Tc(node1, node2));
        }

        br.close();
    }

    private static String getResult() {
        StringBuilder sb = new StringBuilder();

        for (Tc tc : tcList) {
            sb.append(distance(tc.node1, tc.node2) + "\n");
        }

        return sb.toString();
    }

    private static int distance(int node1, int node2) {
        boolean[] visited = new boolean[n + 1];

        PriorityQueue<Edge> queue = new PriorityQueue();
        queue.add(new Edge(node1, 0));

        while (!queue.isEmpty()) {
            Edge cur = queue.remove();

            for (Edge edge : graph[cur.node]) {
                if (!visited[edge.node]) {
                    if (edge.node == node2) return cur.dist + edge.dist;

                    visited[edge.node] = true;
                    queue.add(new Edge(edge.node, cur.dist + edge.dist));
                }
            }
        }

        return -1;
    }

    private static void printResult(String result) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}

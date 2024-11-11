package boj.graph.n2206;

import java.util.*;
/*
  맵상에서 0은 이동할 수 있는곳, 1은 벽
  1,1에서 n,m까지 이동하는 최단 경로(시작과끝칸 포함)
  최대 한개의 벽을 부수고 이동가능함
 */

public class Main {
    static class Node {
        int index;
        boolean broken;

        Node(int index, boolean broken) {
            this.index = index;
            this.broken = broken;
        }
    }

    static Set<Integer> walls = new HashSet<>();
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static List<List<Integer>> adjList = new ArrayList<>();

    static int n;
    static int m;

    static int toIndex(int x, int y) {
        return (x * m) + y;
    }

    static int bfs(int n, int m, int root, int target) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n * m][2];
        int[] result = new int[n * m];

        queue.add(new Node(root, false));
        visited[root][0] = true;
        result[root] = 1;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int node = currentNode.index;
            boolean broken = currentNode.broken;

            if (node == target) {
                return result[node];
            }

            int x = node / m;
            int y = node % m;
            for (int i = 0; i < 4; i++) {
                int vx = x + dx[i];
                int vy = y + dy[i];
                if (vx < 0 || vy < 0 || vx >= n || vy >= m)
                    continue;
                int newNode = toIndex(vx, vy);

                if (walls.contains(newNode)) {
                    if (!broken && !visited[newNode][1]) {
                        visited[newNode][1] = true;
                        queue.add(new Node(newNode, true));
                        result[newNode] = result[node] + 1;
                    }
                } else if (!visited[newNode][broken ? 1 : 0]) {
                    visited[newNode][broken ? 1 : 0] = true;
                    queue.add(new Node(newNode, broken));
                    result[newNode] = result[node] + 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String str = sc.next();
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '1')
                    walls.add(toIndex(i, j));
            }
        }
        int min = bfs(n, m, 0, n * m - 1);
        System.out.println(min);
    }
}

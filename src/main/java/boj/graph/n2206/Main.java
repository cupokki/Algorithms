package boj.graph.n2206;

import java.util.*;

/*
  맵상에서 0은 이동할 수 있는곳, 1은 벽
  1,1에서 n,m까지 이동하는 최단 경로(시작과끝칸 포함)
  최대 한개의 벽을 부수고 이동가능함
 */
public class Main {
    static Set<Integer> walls = new HashSet();
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
//    static List<List<Integer>> adjList = new ArrayList();

    static int n;
    static int m;

    static int toIndex(int x, int y) {
        return (x * m) + y;
    }

    static int bfs(int n, int m, int root, int target) {
        Queue<Integer> queue = new LinkedList<>();
//        boolean[] visited = new boolean[n * m];
        Set<Integer> visited = new HashSet<>();
        int[] result = new int[n * m];
//        visited[root] = true;
        visited.add(root);
        queue.add(root);
        result[root] = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            int x = node / m;
            int y = node % m;
            for(int i = 0; i < 4; i++){
                int vx = x + dx[i];
                int vy = y + dy[i];
                if (vx < 0 || vy < 0 || vx >= n || vy >= m)
                    continue;
                int newNode = toIndex(vx, vy);
                if (!visited.contains(newNode) && !walls.contains(newNode)) { // 벽이 아니라면 간다.
                    visited.add(newNode);
                    queue.add(newNode);
                    result[newNode] = result[node] + 1;
                    if (newNode == target) {
                        return result[newNode];
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
//        for (int i = 1; i <= n * m; i++) {
//            adjList.add(new ArrayList<>());
//        }
//        map = new boolean[n * m];
        for (int i = 0; i < n; i++) {
            String str = sc.next();
            for (int j = 0; j < m; j++) {
                if (str.charAt(j) == '1')
                    walls.add(toIndex(i, j));
//                    map[toIndex(i, j)] = true;
            }
        }
        // 첫번째도 벽으로 취급
//        map[0] = true;

        //간선추가
//        for (int u = 0; u < n * m; u++) {
//            int x = u / m;
//            int y = u % m;
//            for (int i = 0; i < 4; i++) {
//                int vx = x + dx[i];
//                int vy = y + dy[i];
//                if (vx < 0 || vy < 0 || vx >= n || vy >= m)
//                    continue;
//                int v = toIndex(vx, vy);
//                adjList.get(u).add(v);
//            }
//        }

        // 모든 벽에서 출발해서 시작점과 타킷 두갈래로 찾아보자
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n * m; i++) {
            if (walls.contains(i)) { // 벽이라면
                int part1 = bfs(n, m, i, 0);
                int part2 = bfs(n, m, i, n * m - 1);
                if (part1 < 0 || part2 < 0) {
                    continue;
                }
                min = Math.min(min, part1 + part2 + 1);
            }

        }
        if (min == Integer.MAX_VALUE)
            System.out.println(-1); //도달 불가시 -1
        else
            System.out.println(min); //도달 불가시 -1
    }

}

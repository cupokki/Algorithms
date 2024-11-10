package boj.graph.n7569;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 **  3차원 토마토상자, 토마토는 상하좌우위아래 6방향으로
 *   토마토가 다 익는 최소 며칠이 걸리나?, 도달하지 못하는 토마토가 있다면 -1
 *   문제중요 포인트
 *       1. 3차원 점이 노드인 그래프 구현
 *       2. 시작 노드가 여러개일 수 있다는점
 *       3. 여러개의 시작 노드가 한번에 계산되어야한다는 점
 */
public class Main {
    static int m; // 가로
    static int n;
    static int h;
    static int size;
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    static Queue<Integer> queue = new LinkedList<>();
    static boolean[] visited;

    static int toIndex(int x, int y, int z){
        return x + y * m + z * m * n;
    }

    static int[] toPoint(int index){
        int[] p = new int[3];
        p[2] = index / (m * n);
        p[1] = (index % (m * n)) / m;
        p[0] = index % m;
        return p;
    }

    static int bfs(){
        int days = 0;
        while(!queue.isEmpty()) {
            int qSize = queue.size();
            for (int i = 0; i < qSize; i++) {
                
                int node = queue.poll();
                for (int newNode : adjList.get(node)) {
                    if (!visited[newNode]) {
                        visited[newNode] = true;
                        queue.add(newNode);
                    }
                }
            }
            if (!queue.isEmpty())
                days++;
        }
        return days;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        h = sc.nextInt();
        size = m * n * h;
        visited = new boolean[size];

        // 노드 추가
        for(int i = 0; i < size; i++){
            adjList.add(new ArrayList<>());
        }

        // 상자 입력과 그래프 간선 추가
        boolean[] box = new boolean[size];
        for(int u = 0; u < size; u++) {
            int index = sc.nextInt();
            if (index == 1){
                queue.add(u); // 시작할 노드들
                visited[u] = true; // 시작 노드는 방문 표시
            } else if (index == -1) {
                box[u] = true; // 막힌 부분 기록
            }
        }

        // 간선 추가
        int[] dx = {0, 0, 0, 1, -1, 0};
        int[] dy = {0, 0, 1, 0, 0, -1};
        int[] dz = {1, -1, 0, 0, 0, 0};
        for(int u = 0; u < size; u++){
            int[] p = toPoint(u);
            for(int i = 0; i < 6; i++){
                int x = p[0] + dx[i];
                int y = p[1] + dy[i];
                int z = p[2] + dz[i];
                if(x < 0 || y < 0 || z < 0 || x >= m || y >= n || z >= h)
                    continue;
                int v = toIndex(x, y, z);
                if(box[v]) // 이동할 곳이 막혀있다면
                    continue;
                adjList.get(u).add(v); // 모두 아니라면 간선 연결
            }
        }

        // 최소 일수 계산
        int minDays = bfs();
        for (int i = 0; i < size; i++){
            if (!visited[i] && !box[i]){
                minDays = -1;
                break;
            }
        }
        System.out.println(minDays);
    }
}

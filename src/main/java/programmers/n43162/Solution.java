package programmers.n43162;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /*
    네트워크상 컴퓨터들이 인접행렬로 주어진다.
    몇개의 네트워크가 존재하는지 구하라.
     */
    public static int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(n, visited, computers, i);
                answer++;
            }

        }

        return answer ;
    }
    static void bfs(int n, boolean[] visited, int[][] computers, int s) {
        visited[s] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);

        while (!q.isEmpty()) {
           int cur = q.poll();
           for (int next = 0; next < n; next++) {
               if (!visited[next] && computers[cur][next] == 1) {
                   q.offer(next);
                   visited[next] = true;
               }
           }
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(
           3,
           new int[][]{
                   {1, 1, 0},
                   {1, 1, 0},
                   {0, 0, 1}
           }
        ));

        System.out.println(solution(
                3,
                new int[][]{
                        {1, 1, 0},
                        {1, 1, 1},
                        {0, 1, 1}
                }
        ));
    }
}

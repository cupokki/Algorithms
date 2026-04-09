package programmers.n72413;

import java.util.*;

public class Solution {
    /*
    간선 그래프에서 택시를 타고 목적지로 이동한다.
    두 사람이 집에 가능 비용의 합을 구한다. 둘이 합승하여 최소가 될 수 있다면 합승한 비용을 계산하고,
    아니라면 둘의 이동거리 합을 최솟값으로 삼는다.
    
    아 같이 갈 수 있는 곳에서 다시 갈라져서 가는것
    한 루트로 가는게 아니라
    */
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
            graph[i][i] = 0;
        }

        for (int[] e : fares)
            graph[e[0]][e[1]] = graph[e[1]][e[0]] = e[2];


        // 점 k를 경우하는 경우
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][k] == Integer.MAX_VALUE || graph[k][j] == Integer.MAX_VALUE) continue;
                    graph[i][j] = graph[j][i] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        // s -> k -> a  +  k -> b
        for (int k = 1; k <= n; k++) {
            if (graph[s][k] == Integer.MAX_VALUE || graph[k][a] == Integer.MAX_VALUE || graph[k][b] == Integer.MAX_VALUE) continue;
            answer = Math.min(answer, graph[s][k] + graph[k][a] + graph[k][b]);
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(6, 4, 6, 2,new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}}));
        System.out.println(solution(7, 3, 4, 1,new int[][]{{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}}));
        System.out.println(solution(6, 4, 5, 6,new int[][]{{2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}}));
    }
}
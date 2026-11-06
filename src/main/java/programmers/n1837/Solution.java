package programmers.n1837;

public class Solution {
    /*
    손님이 자주 탑승하는 위치 추천
    오류를 최소한으로 수정하여 정확한 이동경로를 구한다.
    
    Goal
        - 최소 오류 수정 횟수
        - 수정 불가한 로그라면 -1을 출력
    
    Given infomation
        - 승객의 승하차 위치
        - 양방향그래프
        - n: 거점 개수
        - m: 간선 수
        - k: 로그 수
        - 시작과 끝은 바뀔 수 없음    


    */
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int answer = 0;

        boolean[][] adjacentMatrix = new boolean[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            adjacentMatrix[i][i] = true;
        }

        for (int[] edge : edge_list) {
            adjacentMatrix[edge[0]][edge[1]] = true;
            adjacentMatrix[edge[1]][edge[0]] = true;
        }

        int[][] dist = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
//                for (int k = 0; k < ) {
//
//                }
            }

        }


        int cur = 1;
        for (int i = 0; i < k; i++) {
            // gps_log[i + 1]까지 닿지 못하면 i, i + 1까지 최단경로를 덧댄다.
            // i + 1까지 최단경로가 없다면? i + 2, i + 3 ... i + a, (a < k - i)

        }


        return answer;
    }




     public static void main(String[] args) {
         Solution sol = new Solution();
         System.out.println(sol.solution(7, 10, new int[][]{{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}}, 6, new int[]{1, 2, 3, 3, 6, 7})); //1
         System.out.println(sol.solution(7, 10, new int[][]{{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}}, 6, new int[]{1, 2, 4, 6, 5, 7})); // 0
     }
}
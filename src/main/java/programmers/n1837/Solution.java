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

    결선: 다음 로그와 현재 로그 위치가 직접 연결 되어있지않다
        1) 현재로그가 오류이다.
        2) 다음로그가 오류이다.
        3) 둘다 아니라면, 전로그(i - 1) 기준으로 탐색 수행
            ㄴ 더이상 수행 불가면 return -1;

    한 지점을 수정하여 수정횟수를 소모했는데, 다음 나오는 결선구간에 수정시 불가능한 경우?
    */
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int answer = 0;

        boolean[][] adjMatrix = new boolean[n + 1][n + 1];
        for (int[] edge : edge_list) {
            adjMatrix[edge[0]][edge[1]] = true;
            adjMatrix[edge[1]][edge[0]] = true;
        }

        for (int i = 1; i <= n; i++) {
            adjMatrix[i][i] = true;
        }

        // t시점에서 v에 도착했을때 최소오류 수정 횟수
        int[][] dp = new int[k][n + 1];

        for (int i = 0; i < k; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE / 2;
            }
        }

        dp[0][gps_log[0]] = 0;

        for (int t = 0; t < k; t++) {
            for (int v = 1; v < n; v++) {
//                dp[t][v] = Math.min(dp[t - 1][u]) + (gps_log[t] != v ? 1 : 0);
            }
        }

        answer = dp[k][n];

        return answer;
    }


    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(7, 10, new int[][]{{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}}, 6, new int[]{1, 2, 3, 3, 6, 7})); //1
        System.out.println(sol.solution(7, 10, new int[][]{{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}}, 6, new int[]{1, 2, 4, 6, 5, 7})); // 0
    }
}
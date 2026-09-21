package programmers.n133500;

public class Solution {
    /*
    - 번호가 매겨진 n개의 등대 (n은 10만 이하의 자연수)
    - 등대사이에 n-1개의 뱃길이 존재
    - 뱃길의 한쪽끝의 등대는 반드시 불이 밝혀져 있도록한다.
    - 최소 등대의 수를 구하라.

    말단 노드가 문제에 사용되지않는다? 노드 축소?
    나와 연결된 반대쪽 노드는 반드시 불이 밝혀진다. 반대쪽 노드는 다시 말단 노드가 된다.
    */
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;

        boolean[] turnOnIdx = new boolean[n];
        boolean[][] graph = new boolean[n][n];


        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = i + 1; j < n; j++) {
                if (graph[i][j]) cnt++;
            }

            if (cnt == 1) {

                for (int j = 0; j < n; j++)  if (graph[i][j]) turnOnIdx[j] = true;
            }
        }


        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(8, new int[][]{{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}})); // 2
        System.out.println(sol.solution(10, new int[][]{{4, 1}, {5, 1}, {5, 6}, {7, 6}, {1, 2}, {1, 3}, {6, 8}, {2, 9}, {9, 10}})); // 3

    }
}
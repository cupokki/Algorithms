package programmers.n49191;

import java.util.*;

public class Solution {
    /*
    n명의 차례로 번호가 매겨진 선수,
    result는 (a, b) 쌍, a번 선수가가 b번 선수를 항상 이겼다는 의미
    모순된 입력은 없음

    선수는 100명 이하
    경기수 n는 4500이하

    일부 경기기록이 분실된 results가 주어질때, 순위가 확실한 사람의 수를 출력
    방향 그래프로 연결하고, 말단 노드부터 검사?
    */
    public static int solution(int n, int[][] results) {
        int answer = 0;

        boolean[][] graph = new boolean[n + 1][n + 1];

        for (int[] edge : results) {
            graph[edge[0]][edge[1]] = true;
        }



        return answer;
    }

    public static void main(String[] args) {

        System.out.println(solution(5, new int[][] {{4, 3}, {4, 2}, {3,2}, {1, 2}, {1, 2}, {2, 5}}));
    }
}
package programmers.n43236

import java.util.*;

public class Solution {
    /*
    출발지로부터 dist만큼 떨어진 곳에 도착지
    그 사이 바위들이 놓여있어, n개를 제거한다.
    각 지점 사이 거리의 최솟값 중 가장 큰 값을 반환하라

    distance는 10억이하의 자연수
    바위는 5만개 이하
    n은 5만개 이하

    노드간 거리를 구해놓고, nlogn인가
    */
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        Arrays.sort(rocks);
        int[] dist = new int[rocks.length + 1];
        dist[0] = rocks[0];
        for (int i = 1; i <= rocks.length; i++) {
            dist[i] = rocks[i] - rocks[i - 1];
        }

        int low = 0;
        int high = distance;
        while (low <= high) {
            int mid = (low + high) / 2;
        }
        return answer;
    }

     public static void main(String[] args) {
         Solution sol = new Solution();
         System.out.println(sol.solution(25, new int[]{2, 14, 11, 21, 17}, 2)); // 4
     }
}
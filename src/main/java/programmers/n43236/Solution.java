package programmers.n43236;

import java.util.*;

public class Solution {
    /*
    출발지로부터 dist만큼 떨어진 곳에 도착지
    그 사이 바위들이 놓여있어, n개를 제거한다.
    각 지점 사이 거리의 최솟값 중 가장 큰 값을 반환하라

    distance는 10억이하의 자연수
    바위는 5만개 이하
    n은 5만개 이하

    제거할 바위가 많을떄가 문제인데.



    뭘정렬하고 그럴껀데

    노드간 거리를 구해놓고, nlogn인가
    */
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        Arrays.sort(rocks);

        int low = 0;
        int high = distance;
        while (low <= high) {
            int mid = (low + high) / 2;

            int cnt = 0;

            int prevDist = 0;
            for (int i = 0; i < rocks.length; i++) {
                if (mid > rocks[i] - prevDist)
                    cnt++;
                else
                    prevDist = rocks[i];
            }

            if (cnt >= mid) {
                // 더 쪼인다.
                low = mid + 1;
                answer = Math.min(answer, mid);

            } else {
                // 더 늘려야한다.
                high = mid - 1;

            }
        }

        return answer;
    }


    public static void main(String[] args) {
         Solution sol = new Solution();
         System.out.println(sol.solution(25, new int[]{2, 14, 11, 21, 17}, 2)); // 4
     }
}
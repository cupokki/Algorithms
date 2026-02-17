package programmers.n42884;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    /*
    고속도로를 이동하는 차량의 경로가 주어진다.
    모든 차량이 한번은 단속카메라를 만나게 하려면 최소 몇개의 카메라가 필요한가.

    구간이 빨리 끝나는 순으로 정렬하고
    route[i][1]전에 시작하는 것을 빼버린다.
     */
    public static int solution(int[][] routes) {
        int answer = 0;
        int n = routes.length;

        Arrays.sort(routes, Comparator.comparing((int[] r) -> r[1]));
        for (int i = 0; i < n;) {
            int end = routes[i][1];
            answer++;
            i++;
            while (i < n && routes[i][0] <= end) {
                i++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}}));
    }
}

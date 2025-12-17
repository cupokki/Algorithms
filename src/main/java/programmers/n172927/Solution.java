package programmers.n172927;

import java.util.*;

public class Solution {
    /*
    다이아, 철, 돌 광물이 있고, 다이아, 철, 돌로 만든 곡괭이가 존재한다.
    곡괭이로 채광할때 재질과 대상 광물에 대해 피로도 소모표가 존재한다.
    모든 곡괭이는 5회만 사용할 수 있다.
    한번 선택한 곡괭이는 부숴질때까지 사용한다.
    광물이 차례로 주어질 때, 곡괭이를 적절히 사용하여 모든 광물을 캐거나, 곡괭이가 모두 소모 될 때까지 수행한다.
    최소의 피로도를 출력하라.
    
    picks 배열은 차례대로 다이아, 철, 돌 곡괭이 수를 의미한다.
    */
    public static int solution(int[] picks, String[] minerals) {
        enum MINERALS {
            diamonds,
            iron,
            stone;
        }
        int answer = 0;
        int[][] energy = {
                {1, 1, 1},
                {5, 1, 1},
                {25, 5, 1}
        };

        int len = minerals.length;
        int[] state = new int[3];
        int[] dp = new int[len + 1];

        for (int i = 0; i < 3; i++) {
            // 직전의 값을 가져온다.
            for (int j = 0; j < len; j++) {
                // 곡괭이를 빼고, 현재것을 썼을때와 비교하여 작은 것을 선택
                if (state[i] < picks[i]) {
                    int n = MINERALS.valueOf(minerals[j]).ordinal();

                }

            }
        }


        return answer;
    }

    // public static void main(String[] args) {
    //     System.out.println(solution(new int[]{1, 3, 2}, new String[]{"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"})); // 12
    //     System.out.println(solution(new int[]{0, 1, 1}, new String[]{"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"})); // 50
    // }
}
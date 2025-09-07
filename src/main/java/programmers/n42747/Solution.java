package programmers.n42747;

import java.util.Arrays;

public class Solution {
    /*
    n개 논문중 h번 이상 인용된 논문이 h개 이상이고
    나머지 논문이 h번 이하로 인용되었다면
    h의 최댓값은 H-index
    citations는 h의 집합
    */
    public static int solution(int[] citations) {
        Arrays.sort(citations);
        int len = citations.length;

        for (int i = 0; i < len; i++) {
            int h = len - i; // h 이상 인용된 수
            if (citations[i] >= h) {
                return h;
            }
        }
        return 0;
    }

    public static void main(String[] args){
        System.out.println(solution(new int[]{3, 0, 6, 1, 5}));
    }
}

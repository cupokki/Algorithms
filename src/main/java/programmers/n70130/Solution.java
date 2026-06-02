package programmers.n70130;

import java.util.*;

public class Solution {
    /*
    부분 수열x는 전체에서 몇개의 원소를 빼서 만든 배열
    가장 긴 스타수열의 길이를 출력한다.
    스타 수열이 없다면 0을 출력한다.

    스타수열은 항상 2n개이며,
    연속된 두 원소를 순서대로 나누어 부분수열을 만들 때,
        모든 부분수열은 공통된 값의 원소가 존재한다.
        단, 부분수열의 두 수는 같지않다.

    a의 길이는 50만 이하의 자연수이며, 원소는 0이상 a의 길이 미만이다.
    */
    public static int solution(int[] a) {
        int answer = 0;

        // // 빈도순으로 정렬
        int[] frequency = new int[a.length + 1];
        for (int num : a) frequency[num]++;

        for (int common = 0; common < a.length; common++) {
            if(answer >= frequency[common] * 2) continue; // 공통원소 보다 두배는 많아야한다.

            int cnt = 0;
            int i = 0;
            while(i < a.length - 1) {
                if ((a[i] == common || a[i + 1] == common) && a[i] != a[i + 1]) {
                    cnt += 2;
                    i += 2; // 올바른 쌍을 찾음
                } else {
                    i += 1; // 해당없음
                }
            }

            answer = Math.max(answer, cnt);
        }

        return answer;
    }

     public static void main (String[] args) {
//         System.out.println(solution(new int[]{0}));
//         System.out.println(solution(new int[]{5, 2, 3, 3, 5, 3}));
         System.out.println(solution(new int[]{0, 3, 3, 0, 7, 2, 0, 2, 2, 0}));
     }
}
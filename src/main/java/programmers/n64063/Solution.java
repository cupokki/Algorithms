package programmers.n64063;

import java.util.*;
import java.util.stream.LongStream;

public class Solution {
    /*
    k개의 방이 있는 호텔에 방 배정 규칙
    1. 순서대로 한명씩
    2. 고객은 원하는 방번호를 제출
    3. 빈방이라면 배로 배정
    4. 이미 배정되어 있다면, 희망번호보다 크며, 가장 최소인 방에 배정

    고객의 희망번호가 주어질때, 각 고객이 실제로 배정 받는 방 번호를 반환한다.
    k : [1,10^12]

    최대 희망 번호는 20만 이하.
    */
    public long[] solution(long k, long[] room_number) {
        int n = room_number.length; // 10억이하

        long[] answer = new long[n];
        long[] possibleRoom = new long[(int)k + 1];
        for (int i = 1; i <= k; i++) {
            possibleRoom[i] = i;
        }

        for (int i = 0; i < n; i++) {
            long wishNum = (int)room_number[i];
            List<Long> temp = new ArrayList<>();

            while (possibleRoom[(int)wishNum] != wishNum) {
                temp.add(wishNum);
                wishNum = possibleRoom[(int)wishNum]; // 다음 방 후보로
            }
            answer[i] = wishNum;

            possibleRoom[(int)wishNum] = wishNum + 1; // 기본

            for (long num : temp) {
                possibleRoom[(int)num] = wishNum + 1;
            }

            answer[i] = wishNum;
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(10, new long[]{1, 3, 4, 1, 3, 1})));
    }
}

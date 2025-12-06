package programmers.n147354;

import java.util.*;

public class Solution {
    /*
    데이터의 첫번쨰 칼럼은 PK
    이 테이블에 대한 해시함수는 아래 규칙으로 정의한다.
    1. 튜플을 col번쨰 칼럼의 값을 기준으로 오름차순 정렬, 동일 시 기본키 기준 내림차순 정렬
    2. 정렬된 데이터에서 i번쨰 튜플에 S_i는 각 컬럼의 값을 i로 나눈 나머지들의 합으로 정의
    3. row_begin, row_end 까지의 S_i값을 누적하여 xor 값 을 해시로 반환
     */
    public static int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;

        Arrays.sort(data, (a, b) -> {
            if (a[col - 1] == b[col - 1]) {
                return b[0] - a[0];
            }
            return a[col - 1] - b[col - 1];
        });

        for (int i = row_begin; i <= row_end; i++) {
            int s = 0;
            for (int v : data[i - 1]) {
                s += (v % i);
            }
            answer ^= s;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] data = {
                {2, 2, 6},
                {1, 5, 10},
                {4, 2, 9},
                {3, 8, 3}
        };
        System.out.println(solution(data, 2, 2, 3));
    }
}

package programmers.n12899;

import java.util.Arrays;

public class Solution {
    // 124만을 사용하는 숫자체계. 3진법과 같으나 표기하는 수가 1, 2, 4만 사용
    // 10진수가 주어질때 124나라 숫자로 변환하라
    // 입력은 자연수로만 주어지며 5천만 이하이다.
    public static String solution(int n) {
        char[] temp = Integer.toString(n - 1, 3).toCharArray();

        String answer = String.valueOf(temp);
        return answer;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(solution(i));
        }
    }
}

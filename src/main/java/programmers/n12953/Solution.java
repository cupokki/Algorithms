package programmers.n12953;


import java.util.Arrays;
import java.util.List;

public class Solution {
    /*
    두 수의 최소공배수를 구하는 것을 확장하여
    입력 배열의 모든 수의 최소 공배수를 구하는 함수를 작성한다.
     */
    static int solution(int[] arr) {
        int lcm = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int a = lcm;
            int b = arr[i];

            while (b != 0) {
                int temp = b;
                b = a % b;
                a = temp;
            }

            lcm = (lcm * arr[i]) / a;
        }
        return lcm;
    }
    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 6, 8, 14}));
        System.out.println(solution(new int[]{1, 2, 3}));
    }
}

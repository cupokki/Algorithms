package programmers.n42839;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class Solution {
    /*
    한자리 숫자 7개가 문자열로 주어진다.
    문자를 조합하여 만들 수 있는 소수 개수의 최댓값을 구하라
     */
    static int len;
    static char[] result;
    static boolean[] used;
    static String str;
    static Set<Integer> set;
    static int answer;
    public static int solution(String numbers) {
        len = numbers.length();
        result = new char[len];
        Arrays.fill(result, ' ');
        used = new boolean[len];
        str = numbers;
        set = new HashSet<>();
        answer = 0;
        dfs("");
        return answer;
    }
    static void dfs(String current) {
        if (!current.equals("")) {
            int n = Integer.parseInt(current);
            if (!set.contains(n) && isPrime(n)) {
                System.out.println(current);
                answer++;
            }
            set.add(n);
        }

        if (current.length() == len) return;

        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                used[i] = true;
                dfs(current + str.charAt(i));
                used[i] = false;
            }
        }
    }

    static boolean isPrime(int n) {
        if (n == 0) return false;
        if (n == 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution("17"));
//        System.out.println(solution("011"));
//        System.out.println(solution("10101"));
//        System.out.println(solution("110"));
    }
}

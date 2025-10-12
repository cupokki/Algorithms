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
    static int[] result;
    static boolean[] used;
    static String str;
    static Set<Integer> set = new HashSet<>();
    static int answer;
    public static int solution(String numbers) {
        len = numbers.length();
        result = new int[len];
        used = new boolean[len];
        str = numbers;
        answer = 0;
        dfs(0);
        return answer;
    }
    static void dfs(int depth) {
        if (depth == len) {
            int n = 0;
            for (int i = 0; i < len; i++) {
                n += Math.pow(10, i) * result[len - 1 - i];
            }
            if (!set.contains(n) && isPrime(n)) {
                answer++;
            }
            set.add(n);
            return;
        }

        dfs(depth + 1);
        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            result[depth] = str.charAt(i) - '0';
            dfs(depth + 1);
            used[i] = false;
            result[depth] = 0;
        }
    }
    static boolean isPrime(int n) {
        if (n == 0) return false;
        if (n == 1) return false;
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
        System.out.println(solution("011"));
    }
}

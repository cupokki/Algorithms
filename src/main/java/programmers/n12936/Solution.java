package programmers.n12936;

import java.util.Arrays;

public class Solution {
    /*
    번호가 순서대로 매겨진 n명의 사람이 일렬로 줄을 선다.
    줄을 스는 경우의 수를 수열로 나타내고 모든 수열을 사전순으로 정렬할 때,
    k번째 수열을 출력하라.

    깊이가 20인 dfs로 모든 수열을 만들면... 완전그래프
    순열로 풀어야하나
     */
//    static int N;
//    static long K;
//    static long idx = 0;
//    public static int[] solution(int n, long k) {
//        N = n;
//        K = k;
//
//        result = new int[n];
//        visited = new boolean[n + 1];
//        dfs(0);
//        int[] answer = result;
//        Arrays.stream(answer).forEach(i -> System.out.print(i + " "));
//        System.out.println();
//        return answer;
//    }
//    static int[] result;
//    static boolean[] visited;
//    static void dfs(int depth) {
//        if (idx == K) {
//            return;
//        }
//
//        if (depth == N) {
//            idx++;
//            return;
//        }
//
//        for (int i = 0; i < N; i++) {
//            if(!visited[i + 1]) {
//                visited[i + 1] = true;
//                result[depth] = i + 1;
//                dfs(depth + 1);
//                visited[i + 1] = false;
//            }
//            if (idx == K) {
//                return;
//            }
//        }
//    }
    static int[] solution(int n, long k) {
        int len = n;
        int[] answer = new int[len];

        int idx = 0;
        while(idx < len) {
            int fac = fac(n - 1);
            answer[idx] = (int) ((k - 1) / fac);
            System.out.print(answer[idx] + " ");
            k = (k - 1) % fac + 1;
            n--;
            idx++;
        }
        System.out.println();

        Arrays.stream(answer).forEach(i -> System.out.print(i + " "));
        System.out.println();

        return answer;
    }
    static int fac(int n) {
        if (n == 1) {
            return 1;
        }
        return n * fac(n - 1);
    }

    public static void main(String[] args) {
        solution(3, 5);
//        solution(3, 2);
    }
}

package boj.backtracking.n2529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 부등호
 * https://www.acmicpc.net/problem/2529
 * >, < 두 종류의 부등호가 K개로 나열된 문자열 A
 * 부등호 사이에 0~9까지 수를 넣어 각 부등호의 조건을 만족시킨다.
 * 선택된 숫자는 모두 달라야한다.
 * 모든 경우를 출력하라
 */
public class Main {
    static int K;
    static long[] arr;
    static boolean[] visited;
    static char[] cstr;
    static List<String> result = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        cstr = new char[K];
        arr = new long[K + 1];
        visited = new boolean[10];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int k = 0; k < K; k++) {
            cstr[k] = st.nextToken().charAt(0);
        }

        backtrack(0);
        Collections.sort(result);
        System.out.println(result.get(result.size() - 1) + "\n" + result.get(0));
    }
    static void backtrack(int idx) {
        if (idx == K + 1) {
            if (isValid()) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i <= K; i++) {
                    sb.append(arr[i]);
                }
                result.add(sb.toString());

            }
            return;

        }
        for (int i = 0; i <= 9; i++) {
            if (!visited[i]) {
                arr[idx] = i;
                visited[i] = true;
                backtrack(idx + 1);
                visited[i] = false;
            }

        }
    }
    static boolean isValid() {
        for (int i = 1; i <= K; i++){
            if (cstr[i - 1] == '<' && arr[i - 1] >= arr[i])
                return false;
            else if (cstr[i - 1] == '>' && arr[i - 1] <= arr[i])
                return false;
        }
        return true;
    }

}

package boj.backtracking.n6603;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 로또
 * [1,49] 6개의 수를 고른다.
 * 49가지 수 중 k개(k > 6)의 수를 골라 집합S를 만들고 그 수만 가지고 번호를 선택한다.
 * 집합S와 k개가 주어질때, 수를 고르는 모든 방법을 사전순으로 출력하라.
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] S;//Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        while(true) {
            S = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if(S[0] == 0) break;
            int k = S[0];

            solve(new int[k], 0, 0, S, sb);
            sb.append("\n");
        }

        System.out.println(sb);
    }
    static void solve(int[] arr, int size, int i, int[] S, StringBuilder sb) {
        if (i == S[0]) { // 6개를 고른다.
            if (size == 6) {
                for (int n : arr) {
                    if (n != 0) sb.append(n + " ");
                }
                sb.append("\n");
            }
            return;
        }
        arr[i] = S[i + 1];
        solve(arr, size + 1, i + 1, S, sb);
        arr[i] = 0;
        solve(arr, size, i + 1, S, sb);

    }
}

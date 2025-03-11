package boj.backtracking.n2309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 일곱 난쟁이
 * https://www.acmicpc.net/problem/2309
 * 일곱 난쟁이의 키의 합은 100이다.
 * 일곱 난쟁이가 포함된 9명의 키가 주어졌을때, 일곱 난쟁이의 키를 찾아 오름차순으로 출력
 * 동일한 키는 없고, 여러 가지인 경우 한가지만 출력한다.
 * 찾을 수 없는 케이스는 없다.
 *
 * 백트래킹으로 풀었으나, 쉽게 생각하면 두명의 키를 제한 키가 100인지만 확인하면된다...
 */
public class Main {
    static int[] heights = new int[9];
    static int[] result = new int[7];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        for (int i = 0; i < 9; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }

        backtrack(0, 0);

        Arrays.sort(result);
        for (int n : result)
            System.out.println(n);
    }
    static boolean kill = false;
    static void backtrack(int idx, int size) {

        if (size == 7) {
            if (validate()) {
                // 일곱난쟁이를 찾음
                kill = true;
            }
            return;
        }
        if (kill || idx == 9) {
            return;
        }
        result[size] = heights[idx];
        backtrack(idx + 1, size + 1);
        backtrack(idx + 1, size);
    }
    static boolean validate() {
        int sum = 0;
        for (int n : result) {
            sum += n;
        }
        return sum == 100;
    }
}

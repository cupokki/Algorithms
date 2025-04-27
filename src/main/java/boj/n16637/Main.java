package boj.n16637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 괄호 추가하기
 * https://acmicpc.net/problem/16637
 * 수식이 주어질때 괄호를 중첩하지 추가할때 얻을 수 있는 최댓값
 *
 * 기본 우선순위 없음
 * 괄호는 추가하지 않아도 됨
 */
public class Main {
    static int N;
    static List<Integer> arr;
    static List<Character> ops;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        char[] exp = br.readLine().toCharArray();

        arr = new ArrayList<>();
        ops = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (i % 2 == 0)
                arr.add(exp[i] - '0');
            else
                ops.add(exp[i]);
        }

        backtracking(0,0);

    }
    static int max = 0;
    static int[] orders;
    static void backtracking(int idx, int start) {
        if (N / 2 == idx) {
//            int result = 0;
//            for (int order : orders) {
//                int a = arr.get(order);
//                int b = arr.get(order + 1);
//                switch(ops.get(order)) {
//                    case '+':
//                        arr.set(order, a + b);
//                        arr.set(order + 1, a + b);
//                        break;
//                    case '*':
//                        arr.set(order, a * b);
//                        arr.set(order + 1, a * b);
//                        break;
//                    case '-':
//                        arr.set(order, a - b);
//                        arr.set(order + 1, a - b);
//                        break;
//                    default:
//                        break;
//                }
//            }
//            max = Math.max(max, result);
            return;
        }

        for (int i = start; i < N/2; i++) {
            orders[idx] = i;
            backtracking(i + 1, start + 2);
        }
    }
}


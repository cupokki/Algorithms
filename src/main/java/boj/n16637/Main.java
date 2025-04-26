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
 */
public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        char[] exp = br.readLine().toCharArray();

        List<Integer> arr = new ArrayList<>();
        List<Character> ops = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (i % 2 == 0)
                arr.add(exp[i] - '0');
            else
                ops.add(exp[i]);
        }

        int sum = 0;


        for (int i = 0; i < N; i++) {

        }
    }
}

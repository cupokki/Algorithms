package boj.greedy.n1026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1026
 * 보물
 * 크기가 같은 A배열과 B배열
 * S = A[0] × B[0] + ... + A[N-1] × B[N-1]
 * S값이 최소가 되게 A를 재배열하라
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arrA = new int[n];
        int[] arrB = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++ ){
            arrA[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++ ){
            arrB[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arrA);
        Arrays.sort(arrB);

        int s = 0;
        for(int i = 0; i < n; i++){
            s += arrA[i] * arrB[n - 1- i];
        }
        System.out.println(s);
    }
}

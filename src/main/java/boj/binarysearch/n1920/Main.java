package boj.binarysearch.n1920;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1920
 * 수 찾기(이분탐색으로 풀기)
 * N개의 정수가 든 배열
 * X라는 정수가 존재하는지 찾는 프로그램
 * 모든 정수의 범위가 Integer의 모든 값
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int x = Integer.parseInt(st.nextToken());
            System.out.println(binarySearch(arr, x) != -1? 1 : 0); // 존재 시 1
        }
    }

    /**
     * @param arr
     * @param x
     * @return index of element n of arr.
     */
    static int binarySearch(int[] arr, int x) {
        int l = 0;
        int r = arr.length - 1;
        int m;
        while(l <= r){
            m = l + (r - l) / 2;
            if( x == arr[m])
                return m;
            if (x < arr[m]) {
                r = m - 1;
            } else {
                l = m + 1;
            }

        }
        return -1;
    }
}

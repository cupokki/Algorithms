package boj.greedy.n1439;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 뒤집기
 * https://www.acmicpc.net/problem/1439
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] cStr = br.readLine().toCharArray();
        char[] reversed = new char[cStr.length];
        int min = 0;
        for(int i = 0; i < cStr.length; i++){
            reversed[i] = cStr[cStr.length - 1 - i];
        }
        int count = 0;

        //
        for(int i = 0; i < cStr.length / 2; i++){
            if (reversed[i] == cStr[(cStr.length - 1 - i) / 2]) {
                count++;
            }
        }
        min = count;

        count = 0;
        for(int i = 0; i < cStr.length / 2; i++){
            if (reversed[(cStr.length - 1 - i) / 2] == cStr[i]) {
                count++;
            }
        }
        min = Math.min(min, count);
        System.out.println(min);
    }
}

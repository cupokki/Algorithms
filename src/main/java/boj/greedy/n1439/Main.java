package boj.greedy.n1439;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 뒤집기
 * https://www.acmicpc.net/problem/1439
 *
 * 0과 1로만 이루어진 문자열 S
 * 구간을 설정하여 해당 구간을 반전한다.
 * 반전을 수행하여 같은 수로 만드는 최소 횟수를 출력하라.
 *
 * 0으로 만들기, 1로 만들기 최소 횟수가 다를 수 있다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] cstr = br.readLine().toCharArray();

        int min = Math.min(unify(cstr, '1'), unify(cstr, '0'));

        System.out.println(min);
    }

    static int unify(char[] cstr, char c) {
        int count = 0;

        for (int i = 0; i < cstr.length; i++) {
            if(cstr[i] != c){
                while (i < cstr.length && cstr[i] != c) i++;
                count++;
            }
        }

        return count;
    }
}

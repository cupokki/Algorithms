package boj.backtracking.n1759;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 암호 만들기
 * https://www.acmicpc.net/problem/1759
 * 길이가 L인 암호
 *
 * 최소 한개의 모음(a, e, i, o, u)과 두개의 자음이 구성되어있다.
 * 비밀번호는 정렬되어있다.
 *
 * c개의 종류가 있을때, 모든 케이스를 출력한다.
 */
public class Main {
    static int L;
    static int C;
    static char[] charset;
    static char[] pw;
    static List<String> result = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        pw = new char[C];
        charset = br.readLine().replaceAll(" ", "").toCharArray();

        solve(0, 0,0);
        StringBuilder sb = new StringBuilder();

        Collections.sort(result);
        for (String s : result) {
            sb.append(s + "\n");
        }
        System.out.println(sb.toString());

    }
    static void solve (int i, int mo, int ja) {
        if (i == C) {
            if ((mo + ja) == L && mo >= 1 && ja >= 2) {
                List<Character> list = new ArrayList<>();
                for (char c : pw) {
                    if (c != ' ') list.add(c);
                }
                Collections.sort(list);
                StringBuilder sb = new StringBuilder();
                for (char c : list) {
                    sb.append(c);
                }
                result.add(sb.toString());
            }
            return;
        }

        pw[i] = charset[i];
        int m = 0;
        int j = 0;

        if (isMoeum(charset[i]))
            m++;
        else
            j++;

        solve(i + 1, mo + m , ja + j); // 선택
        pw[i] = ' ';
        solve(i + 1, mo, ja ); // 선택하지 않음
    }
    static boolean isMoeum(char c) {
        return "aeiou".indexOf(c) >= 0;
    }
}

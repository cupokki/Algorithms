package boj.backtracking.n15650;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * https://www.acmicpc.net/problem/15650
 *
 * n과 M문제(2)
 * 시간제한: 1초  메모리제한 : 512MB
 * 자연수 n, m이 주어졌을때 중복없이 m개를 고른 수열을 오름차순으로 출력한다.
 * 1 <= M <= n <= 8
 * 중복은 제거한다.
 */
public class Main {
    static int n;
    static int m;
//    static Set<Integer> visited = new HashSet<>();
    static StringBuilder output = new StringBuilder();
    static int[] result;
    static void dfs(int depth, int node) {
        if(depth == m){
            //출력
            for(int i = 0; i < m; i++)
                output.append(String.format("%d ", result[i]));
            output.append("\n");
            return;
        }
        for(int i = node; i <= n; i++){
//            if(!visited.contains(i)){
//                visited.add(i);
                result[depth] = i;
                dfs(depth + 1, i + 1);
//                visited.remove(i);
//            }
        }
    }
    public static void main (String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        result = new int[m];
        dfs(0, 1);

        bw.write(output.toString());
        bw.flush();
        bw.close();
    }
}

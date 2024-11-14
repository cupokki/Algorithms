package boj.backtracking.n15649;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

/*
    자연수 n과 m이 주어졌을때 1부터 n까지 자연수 중 중복없이 m개를 고른 수열을 모두 구하라
    특정 경로를 찾는 문제 dfs가 유리?
    1 <= m <= n <= 8
 */
public class Main {
    static int n;
    static int m;
    static int[] result;
    static boolean[] visited;
    static void dfs(int depth){
        if(depth == m){
            for(int j = 0; j < m; j++)
                System.out.print(result[j] + " ");
            System.out.println();
            return;
        }
        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                visited[i] = true;
                result[depth] = i;
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 1; i <= n; i++){


        }
        result = new int[m + 1];
        visited = new boolean[n + 1];
        dfs(0);

    }
}

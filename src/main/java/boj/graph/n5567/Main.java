package boj.graph.n5567;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/5567
 * 결혼식
 * N명의 동기,
 * 자신의 친구, 친구의 친구를 초대한다.
 * 나는 1번이다.
 */
public class Main {
    static Map<Integer, List<Integer>> graph = new HashMap<>();
    static boolean[] visited;
    static int N;
    static int M;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];

        for(int i = 0; i < M; i++) {
            String[] edge = br.readLine().split( " ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            graph.computeIfAbsent(u, k->new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k->new ArrayList<>()).add(u);
        }

        bfs(1);
        System.out.println(count); // 나를 제외
    }

    static void bfs(int root){
        Queue<int[]> q = new LinkedList<>();
        visited[root] = true;
        q.offer(new int[]{root, 0});
        while(!q.isEmpty()) {
            int[] state = q.poll();
            int u = state[0];
            int depth = state[1];

            for(int v: graph.getOrDefault(u, new ArrayList<>())){
                if(!visited[v] && depth < 2){ // depth 2체크 필요
                    visited[v] = true;
                    count++;
                    q.offer(new int[]{v, depth + 1});
                }
            }
        }

    }
}

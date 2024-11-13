package boj.tree.n11725;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/*
https://www.acmicpc.net/problem/11725
루트가 없는 트리가 주어지고 이때 루트를 1이라고 정했을때, 각 노드의 부모노드를 출력하라.
트리는 사이클이 없는 그래프이다.
n <= 100000,
2번노드부터 부모노드 출력;
*/
public class Main {
    static int[] result;
    static List<List<Integer>> adjList = new ArrayList();
    static Set<Integer> visited = new HashSet();
    static Queue<Integer> queue = new LinkedList<>();
    static void bfs(int root){

        queue.add(root);
        while(!queue.isEmpty()){
            int node = queue.poll();
            for(int next : adjList.get(node)){
                if(!visited.contains(next)){
                    visited.add(next);
                    queue.add(next);
                    result[next] = node;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        result = new int[n + 1];
        Arrays.fill(result, -1);
        //노드추가
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        result[1] = 1;
        bfs(1);

        for(int i = 2; i <= n; i++){
            System.out.println(result[i]);
        }
    }
}

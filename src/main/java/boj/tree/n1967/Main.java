package boj.tree.n1967;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/1967
 * 트리(사이클이 없는 무방향 그래프)에서 어떤 두 노드를 선택해도 둘 사이 결로는 하나이다.
 * 임의의 두 노드를 골라 쭉 당긴다면 일직선이 될 것이다. 이 길이가 가장 긴 경로가 트리의 지름이 된다.
 *
 */
class Edge {
    int node;
    int weight;

    public Edge(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}

public class Main {
    static Map<Integer, List<Edge>> tree = new HashMap<>();
    static Set<Integer> visited = new HashSet<>();
    static int longestLeafNode;
    static int max = 0;
    static int distance = 0;

    static void dfs(int node){
        if(distance > max){
            max = distance;
            longestLeafNode = node;
        }
        var edges = tree.get(node);
//        if(edges == null){ // 간선이 null인 노드가 존재할 수 있나?
//            return;
//        }
        for(Edge next : edges){
            int v = next.node;
            int w = next.weight;
            if(!visited.contains(v)){
                visited.add(v);
                distance += w;
                dfs(v);
                visited.remove(v);
                distance -= w;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        if(n == 1){
            System.out.println(max);
            return;
        }
        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            (tree.computeIfAbsent(u, k -> new ArrayList<>())).add(new Edge(v, w));
            (tree.computeIfAbsent(v, k -> new ArrayList<>())).add(new Edge(u, w)); // 양방향 간선 추가
        }
        visited.add(1);
        dfs(1);
        visited.clear();
        visited.add(longestLeafNode);
        dfs(longestLeafNode);

        System.out.println(max);
    }
}

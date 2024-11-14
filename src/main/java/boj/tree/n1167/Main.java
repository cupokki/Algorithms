package boj.tree.n1167;
/*
트리의 지름
가장 거리가 긴 노드간의 거리는 트리의 지름이다. 첫 줄에 정점 V개수(2이상, 10^5이하, 인덱스는 1부터 v까지)가 주어지고
V개 줄에 걸쳐 간선의 정보가 주어진다.
{대상노드} {연결노드} {가중치} ... -1(라인끝)
메모리 제약 256mb;

5
1 3 2 -1
2 4 4 -1
3 1 2 4 3 -1
4 2 4 3 3 5 6 -1
5 4 6 -1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge>{
    int v;
    int w;
    Edge(int v, int w){
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return this.v - o.v;
    }
}
public class Main {
    static int vertex;
    static int max = Integer.MIN_VALUE;
    static Map<Integer, List<Edge>> graph = new HashMap<>();
    static Set<Integer> visited = new HashSet<>();

    static void dfs(int distance,int node){
        //최고 깊에 도달
        max = Math.max(max, distance);
        for(Edge e : graph.get(node)){
            if(!visited.contains(e.v)){
                visited.add(e.v);
                dfs(distance + e.w, e.v);
                visited.remove(e.v);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        vertex = Integer.parseInt(br.readLine());
        for(int i = 1; i <= vertex; i++){
            st = new StringTokenizer(br.readLine());
            List<Edge> edges = new ArrayList<>();
            int u = Integer.parseInt(st.nextToken());
            while(true){
                int v = Integer.parseInt(st.nextToken());
                if(v == -1){
                    break;
                }

                int w = Integer.parseInt(st.nextToken());
                edges.add(new Edge(v,w));
            }
            graph.put(u, edges);
        }
        dfs(0, 1);
        System.out.println(max);
    }
}

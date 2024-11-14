package boj.tree.n1167;
/*
https://www.acmicpc.net/problem/1167
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

임의의 두점의 거리가 멀려면 두 노드가 말단노드이어야한다.
어떤 정점에서 dfs로 탐색한다면 발견한 노드까지 거리는 최소가 아닐수도 있다.
가지 않은 다른 경로로 더 거리를 측정 할 수 있기 때문이다.

교휸 : 우선 그림으로 한번 그려보고 다시 생각해보자
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
    static int longestleafNode; // 지름이 아니다!!!! 특정 노드에서 가장 거리가 먼 말단 노드이다.
    static int max = Integer.MIN_VALUE;
    static Map<Integer, List<Edge>> graph = new HashMap<>();
    static Set<Integer> visited = new HashSet<>();

    static void dfs(int distance,int node){
        //최고 깊에 도달
//        max = Math.max(max, distance);
//        longestleafNode = node;
        if(distance > max) {
            max = distance;
            longestleafNode = node;
        }
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
        visited.add(1);
        dfs(0, 1);
        visited.clear();
        visited.add(longestleafNode);
        dfs(0, longestleafNode);

        System.out.println(max);
    }
}

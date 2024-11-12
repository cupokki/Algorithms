package boj.graph.n1707;

import java.util.*;

/*
https://www.acmicpc.net/problem/1707
이분 그래프
    그래프의 정점의 집합을 둘로 분할 하여, 각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할 할 수 있을때, 그러한 그래프를 이분 그래프라 칭한다.
    이분 그래프임을 판별하라.
    메모리 제약 256mb
 */
class Node{
    int index;
    int setIndex;
    Node(int index, int setIndex){
        this.index = index;
        this.setIndex = setIndex;
    }
}
public class Main {
    static int vertex;
    static int edge;
    static List<List<Integer>> adjList;
    static int[] visited;
    static Queue<Node> queue;

    // 같은 그룹의 노드는 서로 인접하지 않는다.
    // bfs 한방에 가능하겠는데?

    static boolean solve(){
        visited = new int[vertex + 1];
        Arrays.fill(visited, -1);

        boolean result = bfs(1);
        if(result){
            for(int i = 1; i <= vertex; i++){
                if(visited[i] == -1){
                    return false;
                }
            }
        }
        return result;
    }
    static boolean bfs(int root){
        queue.add(new Node(root, 0));
        visited[root] = 0;
        while(!queue.isEmpty()){
            Node current = queue.poll();
            int node = current.index;
            int setIndex = current.setIndex;
            setIndex = (setIndex + 1) % 2;
            for(int next : adjList.get(node)) {
                if(visited[next] < 0){ // 방문 기록이 없다면
                    visited[next] = setIndex; // 방문기록과 인덱스 추가
                    queue.add(new Node( next , setIndex));
                }else if (visited[next] != setIndex) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        while( k-- > 0) {
            vertex = sc.nextInt();
            edge = sc.nextInt();
            adjList = new ArrayList();
//            visited = new HashSet<>();
            queue = new LinkedList<>();
            //정점 추가.
            for(int i = 0; i <= vertex; i++){ // 번호는 1부터 vertex까지
                adjList.add(new ArrayList<>());
            }
            // 간선입력
            for(int i = 0; i < edge; i++){
                int u = sc.nextInt();
                int v = sc.nextInt(); // u != v

                adjList.get(u).add(v);
                adjList.get(v).add(u); // 양방향 간선 추가
            }
            System.out.println(solve() ? "YES" : "NO");
        }

    }
}
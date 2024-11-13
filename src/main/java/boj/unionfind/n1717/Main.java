package boj.unionfind.n1717;

import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> adjList = new ArrayList<>();
    static void union(int element1,int element2){
        if(find(element1, element2)){
            return;
        }
        adjList.get(element1).add(element2);
        adjList.get(element2).add(element1);
    }
    static boolean find(int element1, int element2){
        if(element1 == element2) return true;

        // 그래프를 탐색해서 해당 노드를 찾는다면? 같은 집합에 속한다.
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        visited.add(element1);
        queue.add(element1);
        while(!queue.isEmpty()){
            int node = queue.poll();
            for(int next : adjList.get(node)){
                if(!visited.contains(next)){
                    visited.add(next);
                    queue.add(next);
                    if(next == element2){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 집합의 개수 n + 1개
        int m = Integer.parseInt(st.nextToken()); // 입력으로 주어지는 연산의 개수

        //집합과 각 집합의 원소 초기화
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
            adjList.get(i).add(i);
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int operation = st.nextToken().charAt(0);
            int arg1 = Integer.parseInt(st.nextToken());
            int arg2 = Integer.parseInt(st.nextToken());
            if(operation == '0'){
                //union
                union(arg1, arg2);
            }else{
                //find
                System.out.println(find(arg1, arg2) ? "YES" : "NO");
            }
        }
    }
}

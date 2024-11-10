package boj.graph.n16928;

import java.util.*;

/*
뱀과 사다리 게임
주사위를 조작해 내가 원하는 수가 나오게 만들 수 있다면 최소 몇번만에 도착점에 도착?
노드간 이동 경우
    1. 주사위를 굴려 나온수만큼 이동할 수 있다.
    2. 이동한 칸에 뱀또는 사다리면 해당 위치로 이동한다.

root 1번칸 100번칸에 도달해야한다.
 */
public class Main {
    static final int size = 10;
    static int[] board = new int[size *size];
    static int root = 0;
    static int target = size * size - 1;
    static List<List<Integer>> adjList = new ArrayList<>();
    static boolean[] visited = new boolean[size * size];
    static Queue<Integer> queue = new LinkedList<>();
    static int ladder;
    static int snake;

    static int bfs(){
        int[] result = new int[size * size];
        visited[root] = true;
        queue.add(root);
        while(!queue.isEmpty()){
            int node = queue.poll();

            for(int newNode : adjList.get(node)){
                if(!visited[newNode]){
                    visited[newNode] = true;
                    queue.add(newNode);
                    result[newNode] = result[node] + 1;
                    if(newNode == target){
                        return result[newNode];
                    }
                }
            }
        }
        return -1;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ladder = sc.nextInt();
        snake = sc.nextInt();

        for(int i = 0; i < ladder; i++){
            board[sc.nextInt() - 1] = sc.nextInt() - 1;
        }
        for(int i = 0; i < snake; i++) {
            board[sc.nextInt() - 1] = sc.nextInt() - 1;
        }

        //노드추가
        for(int i = 0; i < size * size; i++){
            adjList.add(new ArrayList<>());
        }

        //간선추가
        for(int u = 0; u < size * size; u++){
            for(int dice = 1; dice <= 6; dice++){
                int v = u + dice;
                if(v >= size * size) // v가 보드를 벗어나면 간선을 추가하지 않는다.
                    continue;
                if(board[v] > 0 ) // 보드에 기록된 이동 위치로 v를 변경한다.
                    v = board[v];
                adjList.get(u).add(v);
            }
        }

        int min = bfs();

        System.out.println(min);
    }
}

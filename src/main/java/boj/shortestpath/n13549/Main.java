package boj.shortestpath.n13549;

import java.util.*;

/**
 *  https://www.acmicpc.net/problem/13549
 *  숨바꼭질3
 *  수빈이는 현재 점 N(0 <= N <= 100,000), 동생은 점 K(0 <= K <= 100,000)이 있다.
 *  수빈이는 1초간 걷거나(1칸), 순간이동한다.0초 만에 2x 위치로 이동한다.
 *  수빈이가 동생을 찾을 수 있는 가장 빠른 시간은
 */

/**
 *      - 일반 다익스트르라로 해결한다.
 *      - 노드의 범위가 커지면 해결할 수 없을 수도 있다.
 *      - 01 BFS를 구현하자
 *      - dp도 가능?
 */
class State {
    int node;
    int dist;

    public State(int node, int dist) {
        this.node = node;
        this.dist = dist;
    }
}

//public class Main {
//    static final int INF = Integer.MAX_VALUE;
//    static final int MAX_POSITION = 100001;
//    static boolean[][] graph;
//    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//        int K = sc.nextInt();
//        if(N >= K){
//            System.out.println(N - K);
//        }else{
//            System.out.println(solve(N, K));
//        }
//    }
//    static int solve(int root, int target) {
//        int[] distance = new int[MAX_POSITION];
//        Arrays.fill(distance, INF);
////        PriorityQueue<Path> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.dist, b.dist));
//        Queue<Path> pq = new LinkedList<>();
//        pq.add(new Path(root, 0));
//        distance[root] = 0;
//
//        while(!pq.isEmpty()) {
//            Path p = pq.poll();
//            int u = p.node;
//            int curDist = p.dist;
//            int v;
//
////            //pruning
////            if(curDist > distance[u])
////                continue;
//
//            // branchs
//            v = u * 2;
//            if(v < MAX_POSITION && distance[v] > curDist + 0) { //https://www.acmicpc.net/board/view/143652
//                distance[v] = curDist + 0;
//                pq.offer(new Path(v, distance[v]));
//            }
//            v = u - 1;
//            if(v >= 0 && distance[v] > curDist + 1) {
//                distance[v] = curDist + 1;
//                pq.offer(new Path(v, distance[v]));
//            }
//            v = u + 1;
//            if(v < MAX_POSITION && distance[v] > curDist + 1) {
//                distance[v] = curDist + 1;
//                pq.offer(new Path(v, distance[v]));
//            }
//
//        }
//
//        return distance[target];
//    }
//}
//
public class Main {
    static final int MAX = 100000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int result = N >= K? N - K : solve(N, K) ;
        System.out.println(result);
    }
    static int solve(int root, int target) {
        int[] time = new int[MAX + 1];
        Arrays.fill(time, Integer.MAX_VALUE);
        Deque<State> dq = new LinkedList<>();
        dq.offer(new State(root, 0));
        time[root] = 0;
        while(!dq.isEmpty()) {
            State s = dq.pollFirst();

            if(time[s.node] < s.dist)
                continue;

            if(s.node * 2 <= MAX && time[s.node * 2] > s.dist + 0) {
                time[s.node * 2] = s.dist;
                dq.addFirst(new State(s.node * 2, s.dist));
            }
            if(s.node + 1 <= MAX && time[s.node + 1] > s.dist + 1) {
                time[s.node + 1] = s.dist + 1;
                dq.addLast(new State(s.node + 1, s.dist + 1));
            }
             if(s.node - 1 >= 0 && time[s.node - 1] > s.dist + 1) {
                time[s.node - 1] = s.dist + 1;
                dq.addLast(new State(s.node - 1, s.dist + 1));
            }
        }
        return time[target];
    }
}
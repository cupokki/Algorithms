package programmers.n86971;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution {
    /*
    n개(2이상, 100이하)의 송전탑이 전선을 통해 트리형태로 존재
    망 내에서 전선을 하나를 끊어 네트워크를 두개로 분할하고자한다.

    분할한 전력망의 노드 수의 차이의 최소값을 구한다.
    그런 경우가 없다면 -1을 반환한다.

    간선리스트에서 하나를 제외한다. -> n - 2개의 간선
    간선리스트
    만약 bfs의 결과값이 n - 2이라면 실패
     */
    static int N;
    static boolean[][] tree;
    public static int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        tree = new boolean[n + 1][n + 1];
        N = n;
        for (int i = 0; i < wires.length; i++) {
            tree[wires[i][0]][wires[i][1]] = true;
            tree[wires[i][1]][wires[i][0]] = true;
        }

        for (int i = 0; i < wires.length; i++) {
            tree[wires[i][0]][wires[i][1]] = false;
            tree[wires[i][1]][wires[i][0]] = false;
            int a = bfs(); // a편 개수
            int b = n - a; // b편 개수
            answer = Math.min(Math.abs(b - a), answer);
            tree[wires[i][0]][wires[i][1]] = true;
            tree[wires[i][1]][wires[i][0]] = true;
        }
        return answer != Integer.MAX_VALUE ? answer : -1;
    }

    static int bfs() {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(1);
        visited.add(1);


        while(!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 1; i <= N; i++) {
                if (!visited.contains(i) && tree[cur][i]) {
                    visited.add(i);
                    q.offer(i);
                }
            }
        }
        return visited.size();
    }

    public static void main(String[] args) {
        int[][] wires1 = {
                {1, 3},
                {2, 3},
                {3, 4},
                {4, 5},
                {4, 6},
                {4, 7},
                {7, 8},
                {7, 9}
        };
        System.out.println(solution(9, wires1));

        int[][] wires2 = {
                {1, 2},
                {2, 3},
                {3, 4}
        };
        System.out.println(solution(4, wires2));

        int[][] wires3 = {
                {1, 2},
                {2, 7},
                {3, 7},
                {3, 4},
                {4, 5},
                {6, 7}
        };
        System.out.println(solution(7, wires3));
    }
}

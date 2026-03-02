package programmers.n49189;

import java.util.*;

public class Solution {
    /*
    n개의 노드 그래프
    n까지 자연수 번호가 적혀있다.
    1번 노드에서 가장 먼 노드를 구하려 한다. (가중치x)

    노드 2만개 이하 2개 이상
    간선은 양뱡향이며 5만개

    */
    public static int solution(int n, int[][] edge) {
        int max = 0;
        int answer = 0;
        List<Integer> list = new ArrayList<>();
        Map<Integer, List<Integer>> adjacentList = new HashMap<>();

        for (int[] e : edge) {
            adjacentList.computeIfAbsent(e[0], (k) -> new ArrayList<>()).add(e[1]);
            adjacentList.computeIfAbsent(e[1], (k) -> new ArrayList<>()).add(e[0]);
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        q.offer(new int[]{1, 0});
        while (!q.isEmpty()) {
            int[] state = q.poll();
            int cur = state[0];
            int dist = state[1];
            if (max < dist) {
                max = dist;
                list.clear();
                list.add(cur);
            } else if (max == dist) {
                list.add(cur);
            }
            for (int next : adjacentList.get(cur)) {
                if (!visited[next]) {
                    q.offer(new int[]{next, dist + 1});
                    visited[next] = true;
                }
            }
        }

        answer = list.size();
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(6, new int[][]{
                {3, 6},
                {4, 3},
                {3, 2},
                {1, 3},
                {1, 2},
                {2, 4},
                {5, 2}
        }));
    }
}

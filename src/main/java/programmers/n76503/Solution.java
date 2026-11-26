package programmers.n76503;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    /*
    말단 노드부터 0으로 만들어나간다.

    Goal
    - 모든 정점 가중치를 0으로 만드는 최소 횟수
    - 불가능하다면 -1 출력
    - 이미 완성된 상태라면 0

    Given information
    - 임의의 연결된 두 점을 골라 한쪽은 +1, 다른 쪽은 -1
    - 배열 a는 가중치 배열
        - a의 길이는 [2, 300000]
        - a[i] = [-1000000, 1000000]
    - 배열 edge는 간선정보
        - edge의 길이는 a.length - 1
        - 항상 트리의 형태로 주어진다.
    */
    public long solution(int[] a, int[][] edges) {
        long answer = -1;

        List<Integer>[] adjMat = new ArrayList[a.length];
        for (int i = 0; i < a.length; i++) {
            adjMat[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adjMat[edge[0]].add(edge[edge[1]]);
            adjMat[edge[1]].add(edge[edge[0]]);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < a.length; i++) {
            if (adjMat[i].size() == 0) {
                q.offer(i);
            }
        }

        int cnt = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            int v = adjMat[u].get(0);

            if (a[v] >= a[u]){
//                a[v] - a[u];
            } else {

            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // 9
        System.out.println(sol.solution(new int[]{-5, 0, 2, 1, 2}, new int[][]{
                {0, 1}, {3, 4}, {2, 3}, {0, 3}
        }));

        // -1
        System.out.println(sol.solution(new int[]{0, 1, 0}, new int[][]{
                {0, 1}, {1, 2}
        }));
    }
}
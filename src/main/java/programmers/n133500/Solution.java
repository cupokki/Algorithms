package programmers.n133500;

import java.util.*;

public class Solution {
    /*
    - 번호가 매겨진 n개의 등대 (n은 10만 이하의 자연수)
    - 등대사이에 n-1개의 뱃길이 존재
    - 뱃길의 한쪽끝의 등대는 반드시 불이 밝혀져 있도록한다.
    - 최소 등대의 수를 구하라.

    말단 노드가 문제에 사용되지않는다? 노드 축소?
    나와 연결된 반대쪽 노드는 반드시 불이 밝혀진다. 반대쪽 노드는 다시 말단 노드가 된다.
    */
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;

        List<Integer>[] nodes = new List[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < lighthouse.length; i++) {
            int u = lighthouse[i][0] - 1;
            int v = lighthouse[i][1] - 1;

            nodes[u].add(v); // 문제조건상 중복된 간선이 주어질 수 없다.
            nodes[v].add(u);
        }

        boolean[] covered = new boolean[n];
        for (int u = 0; u < n; u++) {

            int uDegree = nodes[u].size();

            if (covered[u] || uDegree == 1) continue; // 커버된곳과 말단은 건너뛴다.

            for (int v : nodes[u]) {
                if (covered[v]) uDegree --;
            }

            if (uDegree == 1) { // 말단노드이다.
                int v = nodes[u].iterator().next();
                if (!covered[v]) { // 부모노드 커버되지않았다면
                    covered[v] = true;
                    for (int children : nodes[v]) {
                        covered[children] = true;
                    }
                    answer++;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
//        System.out.println(sol.solution(6, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}})); // 3
//        System.out.println(sol.solution(7, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}})); // 3
        System.out.println(sol.solution(8, new int[][]{{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}})); // 2
        System.out.println(sol.solution(10, new int[][]{{4, 1}, {5, 1}, {5, 6}, {7, 6}, {1, 2}, {1, 3}, {6, 8}, {2, 9}, {9, 10}})); // 3

    }
}
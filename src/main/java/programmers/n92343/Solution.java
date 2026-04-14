package programmers.n92343;

import java.util.*;

public class Solution {
    /*
    이진트리형태로 양과 늑대가 각 노드에 있다.
    루트에서 출발하여 양을 모은다.
    노드 방문시, 노드에 있던 양과 늑대가 따라온다.
    모은 늑대와 양의 수에서 늑대가 양의 수와 같거나 많아지면 모든 양을 잡아먹는다.
    양을 잡아먹히지 않도록하여 최대한 많은 양을 모아 다시 루트로 돌아온다.
    
    최대 몇마리의 양을 모을 수 있나.

    무조건 말단까지 가능 경우를 생각했는데 그런게 아니다.

    그래프에서 서브트리를 만들어 포함된 양의 수와 늑대의 차가 가장 적으면서, 양이 가장 많은 경우를 찾아야한다.
    단순 dfs
    
    */
    static class Node {
        public Node(int parent) {
            this.parent = parent;
            this.children = new ArrayList<>();
        }

        int parent = -1;
        List<Integer> children;

    }
    public static int solution(int[] info, int[][] edges) {
        int answer = 0;

        int n = info.length;


        boolean[] visited = new boolean[n];
        Map<Integer, Node> tree = new HashMap();
        for (int[] e: edges) {
            var p = tree.computeIfAbsent(e[0], k -> new Node(-1));
            p.children.add(e[1]);
            var c = tree.computeIfAbsent(e[1], k -> new Node(e[1]));
            c.parent = (e[0]);
        }


        for (int i = 1 ; i < n; i++) { // 루트도 간선이 하나일수도 있으므로 1부터 시작
            if (tree.get(i) != null) {
                var node = tree.get(i);
                int cur = i;
                while (node.parent != -1 && node.children.size() == 1) {
                    visited[cur] = true;
                    cur = node.parent;
                    node = tree.get(cur);
                }
            }
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 1, 0});
        visited[0] = true;

        while (!q.isEmpty()) {
            int[] state = q.poll();
            int u = state[0];
            int sCnt = state[1];
            int wCnt = state[2];

            //TODO: 트리인데 모든 노드를 검사한다고?
            for (int v : tree.get(u).children) {
                if (info[v] == 0) sCnt++;
                if (info[v] == 1) wCnt++;
                if (!visited[v] && sCnt > wCnt) {
                    q.offer(new int[]{v, sCnt, wCnt});
                    visited[v] = true;
                    answer = Math.max(sCnt, answer);
                }
            }
        }


        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[] {0,0,1,1,1,0,1,0,1,0,1,1}, new int[][] {
                {0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}
        }));

        System.out.println(solution(new int[] {0,1,0,1,1,0,1,0,0,1,0}, new int[][] {
                {0, 1}, {1, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9,10}
        }));
    }
}
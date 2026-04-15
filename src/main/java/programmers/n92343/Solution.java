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
            this.children = new HashSet<>();
        }

        int parent = -1;
        Set<Integer> children;

    }

    static Map<Integer,Node> tree;
    static boolean[][] graph;
    static int n;
    static int[] Info;
    public static int solution(int[] info, int[][] edges) {
        int answer = 0;
        n = info.length;
        Info = info;
        boolean[] visited = new boolean[n];
//        tree = new HashMap();
//        for (int[] e : edges) {
//            var p = tree.computeIfAbsent(e[0], k -> new Node(-1));
//            p.children.add(e[1]);
//            var c = tree.computeIfAbsent(e[1], k -> new Node(e[1]));
//            c.parent = (e[0]);
//        }

        for (int[] e : edges) {
            graph[e[0]][e[1]] = graph[e[1]][e[0]] = true;
        }
//
//        for (int i = 1; i < n; i++) { // 루트도 간선이 하나일수도 있으므로 1부터 시작
//            var node = tree.get(i);
//            int cur = i;
//            while (node.children.size() == 0 && info[cur] == 1) {
//                visited[cur] = true;
//                tree.get(node.parent).children.remove(cur);
//                cur = node.parent;
//                node = tree.get(cur);
//            }
//        }

        // bfs 하려니 양쪽을 둘러보면 문제 규칙과 맞지 않는다.
        // dfs이다.
        // 종료조건은 무엇인가? 모든 노드를 다 들려야하나?
        // 프루닝은? 프루닝 대상인걸 어떻게 알지?
        // 현 가지를 더 진행할지말지는 다른 가지를 들렸다 와야 알 수 있는것 아닌가?
        // 그러면 현 가지를 보류하는 알고리즘이 있어야하는거 아닌가?
        max = 0;
        bfs(0, 1, 0, 1 << 0 | 1 << 1);
        return answer;
    }

    static int max;
    static void bfs(int curNode, int sheep, int wolves, int next) {
        // 0은 루트
//        if (wolves >= sheep) return;
//        max = Math.max(max, sheep);
//        for (int nextNode = 1; nextNode < n; nextNode++) {
//            if (graph[curNode][nextNode]) {
//                bfs(nextNode, sheep + (Info[nextNode] == 1?1:0), wolves + (Info[nextNode] == 0?1:0), next);
//            }
//        }

        for (int i = 0; i < n; i++) {
            if ((next & (1 << i)) == 0) continue;
            int nextNode = i;
            int nextSheep = sheep + (Info[nextNode] == 0?1:0);
            int nextWolves = wolves + (Info[nextNode] == 1?1:0);

            if (wolves < sheep) {
                max = Math.max(max, nextSheep);
                int nextMask = next & ~(1 << curNode);
                for (int to = 0; to < n; to++) {
                    if (graph[curNode][to]) nextMask |= 1 << to;
                }
                bfs(nextNode, nextSheep, nextWolves, nextMask);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1}, new int[][]{
                {0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}
        }));

        System.out.println(solution(new int[]{0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0}, new int[][]{
                {0, 1}, {1, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}
        }));
    }
}
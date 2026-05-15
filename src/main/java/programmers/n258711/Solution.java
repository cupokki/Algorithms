package programmers.n258711;

import java.util.*;

public class Solution {
    /*

    도넛, 막대, 8자 형태의 그래프들에서 추가된 정점하나가 가리키도록 연결된 그래프 형태
    그래프는 단방향그래프.

    추가된 정점의 번호, 도넛, 막대, 8자 그래프의 수를 구하여 순서대로 배열에 담아 반환한다.

    노드 및 간선은 각각 100만개 이하.

    자식이 2개 이상인 노드에 대해 bfs돌려서 자신으로 돌아오는지 확인한다.
    돌아오지 않으면 추가된 정점
    */

    static Map<Integer, List<Integer>> nodes;

    public static int[] solution(int[][] edges) {
        int[] answer = new int[4];
        nodes = new HashMap<>();
        for (int[] edge : edges) {
            nodes.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            if (edge[0] == edge[1]) {
                answer[1]++; // size 1인 도넛
            }
        }


        boolean[] founded = new boolean[1_000_001];
        // bfs
        for (int i : nodes.keySet()) {
            List<Integer> children = nodes.get(i);
            if (children.size() > 2) answer[0] = i; // 정점 확정
            if (children.size() == 2) {
                if (isCreatedNode(i)) {
                    answer[0] = i;
                    break;
                }
            }
        }

        for (int i : nodes.keySet()) {
            if (i == answer[0]) continue;
            if (nodes.get(i).size() != 2) continue;
            // 판별
            // 경로를 다돌고 최대로 많이 방문한 노드의 방문수 cnt
            // cnt == 1 => 도넛
            // cnt == 2 => 8자
            Queue<Integer> q = new LinkedList<>();
            q.offer(i);
            founded[i] = true;
            int cnt = 0;
            while (!q.isEmpty()) {
                int cur = q.poll();
                if (nodes.get(cur) == null) continue;
                for (int next : nodes.get(cur)) {
                    if (next == i) {
                        cnt++;
                    }
                    if (!founded[next]) {
                        q.offer(next);
                        founded[next] = true;
                    }
                }
            }
            if (cnt == 1) {
                answer[1]++; // 도넛
            } else if (cnt == 2) {
                answer[3]++; // 8자
            }
        }

        answer[2] = nodes.get(answer[0]).size() - (answer[1] + answer[3]);
        return answer;
    }

    static boolean isCreatedNode(int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[1_000_000];
        q.offer(start);
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (nodes.get(cur) == null) continue;
            for (int next : nodes.get(cur)) {
                if (next == start) return false;
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        Arrays.stream(solution(new int[][]{{2, 3}, {4, 3}, {1, 1}, {2, 1}}))
//                .forEach(i -> System.out.print(i + " "));
//        System.out.println();
        Arrays.stream(solution(new int[][]{{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}}))
                .forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
}

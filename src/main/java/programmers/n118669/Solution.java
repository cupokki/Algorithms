package programmers.n118669;

import java.util.*;

public class Solution {
    /*
    n개의 지점에 출입구, 쉼터, 산봉우리가 있다.
    모든 지점은 양방향 등산로 존재 
    등산코스를 지점의 번호나열로 나타낸다.
    쉼터와 산봉우리를 지날때 휴식을 할 수 있다.
    intensity: 휴식없이 이동해야하는 가장 긴 코스
    
    출입구에서 출발하여, 하나의 산봉우리를 거쳐 원래 출입구로 돌아오는 코스를 정한다.
    출입구는 처음과 끝, 산봉우리는 한번만 코스에 포함된다.
    
    intensity가 최소가 되게하는 경로의 '산봉우리 번호'와 'intensity의 최솟값'을 반환한다. 답은 항상 존재한다.
    (intensity가 최소인 경로가 다수라면, 최소인 산봉우리 번호를 출력한다.)
    => 코스에 포함된 간선의 가중치의 최댓값이 가장 적은 경로
    
    
    최대 20만개의 경로
    5만개의 노드
    
    gates.length + summits.length = n
    
    
    플로이드와셜? 모든 경로의 최단경로를 찾는것인데 의미가 있나?
    
    최단 경로를 찾는게 목표가 아님
    이동 경로가 max를 넘지 않아야함. 
    
    모든 서밋을 가지않아도 된다.

    시간 개선을 위해서 다익스트라가 필요
    */
    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        List<int[]>[] adjList = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) adjList[i] = new ArrayList<>();

        for (int[] p : paths) {
            int u = p[0];
            int v = p[1];
            int w = p[2];
            adjList[u].add(new int[]{v, w});
            adjList[v].add(new int[]{u, w});
        }

        Set<Integer> gateSet = new HashSet<>();
        Set<Integer> summitSet = new HashSet<>();

        for (int gate : gates) gateSet.add(gate);
        for (int summit : summits) summitSet.add(summit);

        // 노드 i까지 최소 Intensity
        int[] minIntensity = new int[n + 1];
        Arrays.fill(minIntensity, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing((int[] a)-> a[1]));
        gateSet.forEach(gate -> {
            pq.offer(new int[]{gate, 0});
        });

        while (!pq.isEmpty()) {
            int[] state = pq.poll();
            int u = state[0];
            int intensity = state[1];

            // 현 노드까지 더 낮은 강도의 경로가 존재
            if (intensity > minIntensity[u]) continue;
            // 산봉우리 도달
            if (summitSet.contains(u)) continue;

            for (int[] edge : adjList[u]) {
                int v = edge[0];
                int w = edge[1];
                if (gateSet.contains(v)) continue; // 다른 게이트
                int nextIntensity = Math.max(intensity, w);
                if (nextIntensity < minIntensity[v]) {
                    minIntensity[v] = nextIntensity;
                    pq.offer(new int[]{v, nextIntensity});
                }
            }
        }

        int[] answer = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};

        Arrays.sort(summits);
        for (int summit: summits) {
            if (minIntensity[summit] < answer[1]) {
                answer[0] = summit;
                answer[1] = minIntensity[summit];
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(6, new int[][]{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}}, new int[]{1, 3}, new int[]{5})));
        System.out.println(Arrays.toString(solution(7, new int[][]{{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}}, new int[]{1}, new int[]{2, 3, 4})));
        System.out.println(Arrays.toString(solution(7, new int[][]{{1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1}}, new int[]{3, 7}, new int[]{1, 5})));
        System.out.println(Arrays.toString(solution(5, new int[][]{{1, 3, 10}, {1, 4, 20}, {2, 3, 4}, {2, 4, 6}, {3, 5, 20}, {4, 5, 6}}, new int[]{1, 2}, new int[]{5})));
    }
}
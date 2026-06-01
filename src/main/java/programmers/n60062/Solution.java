package programmers.n60062;

import java.util.*;

class Solution {
    /*
    원형 외벽을 점검한다. 둘레는 n
    weak: 취약지점이 주어진다.
    dist: 시계방향 또는 반시계로 dist[i]만큼 이동하는 일꾼
    모든 취약지점을 고려하는 필요한 최소의 친구 수를 출력한다.
    실패시 -1;


    */
    static int min;
    public static int solution(int n, int[] weak, int[] dist) {
        int answer = -1;
        min = 9999999;

        int[] convertedWeak = new int[weak.length * 2];
        for (int i = 0; i < weak.length; i++) {
            convertedWeak[i] = weak[i];
            convertedWeak[i + weak.length] = weak[i] + n;
        }

        int[] descDist = Arrays.stream(dist)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(i -> i)
                .toArray();

        backtrack(new boolean[weak.length], n, convertedWeak, descDist, 0, 0);
        return min  == 9999999 ? -1 : min;
    }

    static void backtrack(boolean[] visited, int n, int[] weak, int[] dist, int depth, int sum) {
        if (depth == dist.length) {
            for (int i = 0 ; i < weak.length / 2; i++) {
                if (!visited[i]) return;
            }
            min = Math.min(sum, min);
            return;
        }

        if (min <= sum) {
            return;
        }

        backtrack(visited, n, weak, dist, depth + 1, sum); // skip

        for (int i = 0; i < weak.length / 2; i++)  {
            if (visited[i]) continue;

            int j = 0;
            List<Integer> list = new ArrayList<>();
            while(i + j < weak.length && weak[i + j] - weak[i] <= dist[depth]){
                if (visited[(i + j) % (weak.length / 2)]) { // 이미 방문한 친구 롤백방지
                    j++;
                    continue;
                }
                visited[(i + j) % (weak.length / 2)] = true;
                list.add(i + j);
                j++;
            }
            backtrack(visited, n, weak, dist, depth + 1, sum + 1);
            for (int idx : list)
                visited[idx % (weak.length / 2)] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(12, new int[]{1, 5, 6, 10}, new int[]{1, 2, 3, 4}));
        System.out.println(solution(12, new int[]{1, 3, 4, 9, 10}, new int[]{3, 5, 7}));

    }
}

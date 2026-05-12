package programmers.n60062;

public class Solution {
    /*
    원형의 공간 둘레 n
    외벽에 취약점 존재
    1시간마다 이동거리가 제각각인 인부를 보내 벽을 점검
    정북방향부터 시계방향으로 거리를 나타냄
    인부는 시계 혹은 반시계로 외벽을 따라 이동
    취약지점을 점검하기 위해 보내야하는 친구
    의 최소 수를 구하라.
    모두 투입해도 불가능 하다면 -1

    인부가 출발할지점을 고를 수 있다.

    n은 2이상 200이하 자연수

     */
    public static int solution(int n, int[] weak, int[] dist) {
        result = new int[dist.length];
        dfs(n, weak, dist, 0, 0);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    static int[] result;
    static int min;
    static void dfs(int n, int[] weak, int[] dist, int depth, int start) {
        if (depth == dist.length) {
            int wIdx = 0;
            for (int i = 0; i < dist.length; i++) {
                while (weak[wIdx] < wIdx + result[i]) { // 범위가 인덱스가 잘못됨
                    wIdx++;
                }
                wIdx++;
                if (wIdx == weak.length) {
                    min = Math.min(min, i);
                    break;
                }
            }

            return;
        }

        for (int i = start ; i < dist.length; i++) {
            result[depth] = dist[i];
            dfs(n, weak, dist, depth + 1, depth + 1);
        }
    }

    public static void main(String[] args) {

        System.out.println(solution(12, new int[]{1, 5, 6, 10}, new int[]{1, 2, 3, 4}));
        System.out.println(solution(12, new int[]{1, 3, 4, 9, 10}, new int[]{3, 5, 7}));

    }
}

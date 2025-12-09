package programmers.n81302;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    /*
    5x5 면접장에서 거리두기
    빈테이블: o, 사람: p, 파티션: x
    맨해튼거리: => 직각(상하좌우)으로만 이동한 거리
    맨해튼거리 2이하로 앉으면 안된다.
    응시자가 사이에 파티션으로 막혀 있을경우 허용, 맨해튼거리제한 허용

    대기실들의 구조가 배열로 주어진다.
    각 대기실의 거리두기상태를 체크해 1(거리둠)또는 0으로 상태 반환
     */

    static char[][] room;
    static final int SIZE = 5;
    public static int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int i = 0; i < places.length; i++) {
            // 현 대기실 초기화
            room = new char[SIZE][SIZE];
            for (int j = 0; j < places.length; j++) {
                room[j] = places[i][j].toCharArray();
            }

            // 대기실 검사
            answer[i] = checkQuarantine() ? 1 : 0;
        }

        return answer;
    }

    static boolean checkQuarantine() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c< SIZE; c++) {
                if (room[r][c] == 'P' && !bfs(r,c)){
                    return false;
                }
            }
        }
        return true;
    }
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean bfs(int sr, int sc) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sr, sc, 0}); // prevR, preC, dist
        boolean[][] visited = new boolean[SIZE][SIZE];
        visited[sr][sc] = true;

        while(!q.isEmpty()) {
            int[] state = q.poll();
            int dist = state[2];

            if (dist == 2) continue;

            for (int d = 0; d < 4; d++) {
                int nr = state[0] + dr[d];
                int nc = state[1] + dc[d];
                if (nr < 0 || nc < 0 || nr >= SIZE || nc >= SIZE) {
                    continue;
                }
                char next = room[nr][nc];
                if (!visited[nr][nc] && next != 'X') { // 파티션이아니고 방문하지않았다면
                    if (next == 'P') {
                        return false;
                    } else {
                        q.offer(new int[]{nr, nc, dist + 1});
                        visited[nr][nc] = true;
                    }
                }
            }
        }

        return true;
    }
    public static void main(String[] args) {
        String[][] input = {
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };
        Arrays.stream(solution(input)).forEach(s -> System.out.println(s + " "));
    }
}

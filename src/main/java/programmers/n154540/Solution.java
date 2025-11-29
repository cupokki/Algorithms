package programmers.n154540;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    /*
    지도에 x나 1~9사이 자연수
    x는 바다, 자연수는 n일 분의 식량, 연결된 자연수는 하나의 섬을 의미함
    각 섬의 식량을 모두 합하여, 각 섬의 식량 수를 오름차순 정렬하여 반환
    무인도가 없다면 -1;
     */
    public static int[] solution(String[] maps) {
        int[] answer = {-1};

        R = maps.length;
        C = maps[0].length();
        foodMap = new int[R][C]; // 0은 바다와 방문한 지점을 의미한다.
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (maps[r].charAt(c) != 'X')
                    foodMap[r][c] = maps[r].charAt(c) - '0';
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (foodMap[r][c] != 0) {
                    list.add(bfs(r, c));
                }
            }
        }

        if (!list.isEmpty()) {
            answer = list.stream().sorted().mapToInt(Integer::intValue).toArray();
        }

        for (int i : answer) {
            System.out.print(i + " ");
        }
        System.out.println();

        return answer;
    }
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int[][] foodMap;
    static int R, C;
    static int bfs(int sr, int sc) {
        int food = foodMap[sr][sc];
        foodMap[sr][sc] = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sr, sc});
        while(!q.isEmpty()) {
            int[] state = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = state[0] + dr[d];
                int nc = state[1] + dc[d];
                if (nr >= 0 && nc >= 0 && nr < R && nc < C) {
                    if (foodMap[nr][nc] != 0) {
                        food += foodMap[nr][nc];
                        foodMap[nr][nc] = 0;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
        }
        return food;
    }

    public static void main(String[] args) {
        solution(new String[]{"X591X","X1X5X","X231X", "1XXX1"});
        solution(new String[]{"XXX","XXX","XXX"});
    }
}

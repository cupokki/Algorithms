package programmers.n86052;

import java.util.*;

public class Solution {
    /*
    격자에 S L R 중 하나가 쓰여있다.
    격자에 빛을 쏠때
        - 빛이 s에 도달하면, 직진
        - 빛이 l에 도달하면, 좌회전
        - 빛이 r에 도달하면, 우회전
        - 끝을 넘어가면 반대편 끝으로 돌아온다.

    격자 내의 모든 사이클의 수를 구한다.

    이미 사용한 간선이 사용되면 종료
    */

    static int m, n;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static List<Integer> result;
    static boolean[][][] visitedEdge;
    public static int[] solution(String[] grid) {

        m = grid.length;
        n = grid[0].length();
        result = new ArrayList<>();
        visitedEdge = new boolean[m][n][4];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int d = 0; d < 4; d++) {
                    move(grid, i, j, d);
                }
            }
        }
        int[] answer = result.stream().mapToInt(Integer::intValue).sorted().toArray();
        return answer;
    }
    static void move(String[] grid, int r, int c, int dir) {
        int cnt = 0;

        while (!visitedEdge[r][c][dir]){
            visitedEdge[r][c][dir] = true;
            cnt++;

            // 이동 방향설정
            if (grid[r].charAt(c) == 'L')
                dir = (4 + dir + 3) % 4;
            else if (grid[r].charAt(c) == 'R')
                dir = (4 + dir + 1) % 4;

            // 다음 포인터 이동
            r = (m + r + dr[dir]) % m;
            c = (n + c + dc[dir]) % n;
        }
        if (cnt != 0) result.add(cnt);
    }


    public static void main(String[] args) {
//        Arrays.stream(solution(new String[]{"SL", "LR"})).forEach(i -> System.out.print(i + " "));
//        System.out.println();
//        Arrays.stream(solution(new String[]{"S"})).forEach(i -> System.out.print(i + " "));
//        System.out.println();
        Arrays.stream(solution(new String[]{"R", "R"})).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }

}
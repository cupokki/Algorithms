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

    public static int[] solution(String[] grid) {
        int[] answer = {};
        List<Integer> result = new ArrayList<>();

        int m, n;
        m = grid.length;
        n = grid[0].length();
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        boolean[][] visitedEdge = new boolean[m * n][m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int d = 0; d < 4; d++) {

                    int dir = d; // 초기 출발 방향
                    int nr = (m + i + dr[d]) % m;
                    int nc = (n + j + dc[d]) % n;
                    int cnt = 1;

                    int currentNodeIdx = i * m + j;
                    int nextNodeIdx = nr * m + nc;
                    while (!visitedEdge[currentNodeIdx][nextNodeIdx]) {
                        if (nr == i && nc == j) { // 사이클 발생
                            result.add(cnt);
                            break;
                        }
                        char c = grid[nr].charAt(nc);

                        if (c == 'L') {
                            dir = (4 + dir - 1) % 4;
                        } else if (c == 'R') {
                            dir = (4 + dir + 2) % 4;
                        }

                        cnt++;
                        currentNodeIdx = nr * m + nc;
                        nr = (m + i + dr[dir]) % m;
                        nc = (n + j + dc[dir]) % n;
                        nextNodeIdx = nr * m + nc;
                        visitedEdge[currentNodeIdx][nextNodeIdx] = true;
                    }
                }
            }
        }
        return answer;
    }


    public static void main(String[] args) {
        Arrays.stream(solution(new String[]{"SL", "LR"})).forEach(i -> System.out.println(i + " "));
        System.out.println();
        Arrays.stream(solution(new String[]{"S"})).forEach(i -> System.out.println(i + " "));
        System.out.println();
        Arrays.stream(solution(new String[]{"R", "R"})).forEach(i -> System.out.println(i + " "));
        System.out.println();
    }

}
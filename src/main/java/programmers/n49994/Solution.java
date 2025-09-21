package programmers.n49994;

import java.util.Arrays;

public class Solution {
    // dirs 는 500이하 자연수, 즉 각 방향으로 500까지 이동가능
    // 2500개의 좌표쌍
    // 2500^2개의 간선
    public static int solution(String dirs) {
        boolean[][] visited = new boolean[11 * 11][11 * 11];

        int r = 5;
        int c = 5;

        int answer = 0;

        for (char d : dirs.toCharArray()) {
            int nr = r;
            int nc = c;
            switch (d) {
                case 'U':
                    nr -= 1;
                    break;
                case 'D':
                    nr += 1;
                    break;
                case 'R':
                    nc += 1;
                case 'L':
                    nc -= 1;
                    break;
                default:
                    break;
            }
            if (nr < 0 || nc < 0 || nr > 10 || nc > 10) {
                continue;
            }
            if (!visited[r * 11 + c][nr * 11 + nc]) {
                visited[r * 11 + c][nr * 11 + nc] = true;
                visited[nr * 11 + nc][r * 11 + c] = true;
                answer ++;
            }
            r = nr;
            c = nc;
        }

        return answer;
    }


    public static void main(String[] args) {
        System.out.println(solution("ULURRDLLU"));
        System.out.println(solution("LULLLLLLU"));
    }
}

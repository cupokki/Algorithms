package programmers.n87377;

import java.util.*;

public class Solution {
    /*
    Ax + By + C = 0인 직선 n개
    직선의 교점 중 정수 좌표에 별을 그린다.

    직선은 최대 1천개
    계수는 -10만에서 10만 사이 정수
    정답은 1000 * 1000 안에 그려짐

    일반적 해법)
    두 직선을 골라 교점을 찾는다. 교점이 정수라면 별을 찍는다.
    *무수히 많은 교점이 생기는 직선 쌍은 없다. => 교차점이 하나 존재
    평행하거나 일치하는 경우는 있음.

    C(1000, 2) = 1000 (1000 - 1) / 2



    어떻게 실수가 주어질떄, 이게 정수인지 판별 할 수 있을까?
    */
    public static String[] solution(int[][] line) {

        boolean[][] board = new boolean[1000][1000];

        int n = line.length;
        long sx = Long.MAX_VALUE, sy = Long.MAX_VALUE;
        long ex = Long.MIN_VALUE, ey = Long.MIN_VALUE;
        List<long[]> coords = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long a = line[i][0], b = line[i][1], e = line[i][2];
                long c = line[j][0], d = line[j][1], f = line[j][2];

                long denominator = a * d - b * c;
                if (denominator == 0) continue;


                if ((b * f - e * d) % denominator != 0) continue;
                if ((e * c - a * f) % denominator != 0) continue;

                long x = (b * f - e * d) / denominator;
                long y = (e * c - a * f) / denominator;

                coords.add(new long[]{x, y});

                sx = Math.min(sx, x);
                sy = Math.min(sy, y);
                ex = Math.max(ex, x);
                ey = Math.max(ey, y);
            }
        }

        char[][] result = new char[(int) (ey - sy + 1)][(int) (ex - sx + 1)];

        for (int i = 0; i < result.length; i++) {
            Arrays.fill(result[i], '.');
        }

        for (long[] coord : coords) {
            int nx = (int) (coord[0] - sx);
            int ny = (int) (ey - coord[1]); // y좌표는 뒤집어서 매핑
            result[ny][nx] = '*';
        }
        String[] answer = new String[result.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = String.valueOf(result);
        }
        return answer;
    }

    public static void main(String[] args) {
        solution(new int[][]{{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}});
        solution(new int[][]{{0, 1, -1}, {1, 0, -1}, {1, 0, 1}});
        solution(new int[][]{{1, -1, 0}, {2, -1, 0}});
        solution(new int[][]{{1, -1, 0}, {2, -1, 0}, {4, -1, 0}});
    }
}
package programmers.n468379;

import java.util.*;

public class Solution {
    /*
    격자 사막구역에 선인장구역을 조성한다.
    선인장 구역은 격자 축에 맞춘 연속된 wh크기의 부분이며 회전 불가
    비구름이 순서대로 격자칸에 비를 뿌린다. 비가 구역에 떨어질때, 선인장이 처음으로 비를 맞는 순간으로 기록
    선인장이 최대한 비를 늦게 맞는 구역을 정한다.
    구역이 여러개면 최대한 위이고, 왼쪽을 선택한다.
    
    격자한 변의 최대 크기는 500000이다.
    drops의 길이는 m *n 이하.
    drops를 모두 고려하면, 시간초과가 분명 발생한다.
    
    시뮬레이션과 그리디?
    공간이 갈라진다면? 그건 어케 처리할건데?
    매 drop 마다 가능한 위치를 유지시키면되는거 아닌가?
    
    그리드에 점수를 세기고, m,n크기의 윈도우를 돌려가며 선택하는건 500000^4인가?
    그럼 누적합?

    범위안에 최소의 크기를 어떻게 인지할까...
    */

    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[][] grid = new int[m][n];
        int idx = 1;
        for (int[] pos: drops) {
            grid[pos[0]][pos[1]] = idx++; // 점수기록
        }

        // 가장 낮은 점수인 영역을 찾아 탐색을 종료한다.
        int[] answer = new int[2];
        int max = -1;
        for (int i = 0; i < m - h + 1; i++) {
            for (int j = 0; j < n - w + 1; j++) {
                // if (grid[i][j] < max) continue;
                int temp = getMin(grid, h, w, i, j);
                if (temp == Integer.MAX_VALUE) return new int[]{i, j};
                if (temp > max)  {
                    max = temp;
                    answer[0] = i; answer[1] = j;
                }
            }
        }
        return answer;
    }

    // 총합을 구하는게 아니라. 구간에 포함된 가장 작은 수를 대표로 꼽는다.
    public int getMin(int[][] grid, int h, int w, int r, int c) {
        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (grid[r + i][c + j] == 0) {
                    cnt++;
                    continue;
                }
                // System.out.println(grid[r + i][c + j]);
                min = Math.min(min, grid[r + i][c + j]);
            }
        }
        if (cnt == h * w) return Integer.MAX_VALUE;
        // System.out.println(r + "," + c + ": " + min);
        return min;
    }

     public static void main(String[] args) {
         Solution sol = new Solution();
//         System.out.println(Arrays.toString(sol.solution(4, 5, 2, 2, new int[][]{{0, 0}, {3, 1}, {1, 3}, {2, 4}, {1, 1}, {2, 2}, {2, 3}, {0, 4})));
         System.out.println(Arrays.toString(sol.solution(3, 3, 1, 1, new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 0}})));
// System.out.println(Arrays.toString(sol.solution(4, 6, 3, 4, new int[][]{{1, 2}})));
// System.out.println(Arrays.toString(sol.solution(4, 6, 1, 2, new int[][]{{0, 1}, {0, 3}, {0, 5}, {1, 1}, {1, 3}, {1, 5}, {2, 1}, {2, 3}, {2, 5}, {3, 1}, {3, 3}, {3, 5}})));
// System.out.println(Arrays.toString(sol.solution(2, 2, 2, 2, new int[][]{{0, 0}, {0, 1}, {1, 1}, {1, 0}})));
// System.out.println(Arrays.toString(sol.solution(4, 4, 3, 1, new int[][]{{2, 0}, {1, 3}, {3, 2}, {0, 1}})));
     }
}
package programmers.n68645;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    // n은 1000 이하 자연수
    // n = 4일때 첫 변은 4 ... 3... 2... 1.. 이런식으로 감아들어가고
    // 방향을 n 감소 할때마다 dr, dc의 인덱스 넘겨주면 되려나
    static int[] dr = {1, 0, -1};
    static int[] dc = {0, 1, -1};
    public static int[] solution(int n) {
        int[][] arr = new int[n][];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[i + 1]; // 각 행의 길이를 i+1로 설정, 자바는 된다.
        }

        int r = 0;
        int c = 0;
        int dir = 0;
        int num = 1;
        for (int l = n; l > 0; l--) {
            for (int i = 0; i < l; i++) {
                arr[r][c] = num++;
                r += dr[dir];
                c += dc[dir];
            }
            r -= dr[dir];
            c -= dc[dir];
            dir = (dir + 1) % 3;
            r += dr[dir];
            c += dc[dir];
        }


        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (arr[i][j] != 0) {
                    temp.add(arr[i][j]);
                }
            }
        }

        int[] answer = temp.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }

    public static void main(String[] args) {
        solution(4);
        solution(5);
        solution(6);
    }
}
    
package programmers.n68645;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    // n은 1000 이하 자연수
    public static int[] solution(int n) {
        int[][] arr = new int[1001][1001];
        for (int i = 1; i <= n;i ++) {
        }

        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr[i][j] != 0) {
                    temp.add(arr[i][j]);
                }
            }
        }

        return temp.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        solution(4);
    }
}

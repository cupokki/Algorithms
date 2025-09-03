package programmers.n87390;

public class Solution {
    public static int[] solution(int n, long left, long right) {
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            int idx = i + 1;
            for (int j = 0; j < n; j++) {
                if (j > idx - 1) idx++;
                arr[i][j] = idx;
            }
        }
        int si = (int)left / n;
        int sj = (int)left % n;
        int ei = (int)right / n;
        int ej = (int)right % n;
        int range = (int)(right - left) + 1;
        int[] answer = new int[range];
        int idx = 0;
        for (int j = sj; j < n; j++) {
            answer[idx++] = arr[si][j];
        }
        for (int i = si + 1; i < ei; i++) {
            for (int j = 0; j < n; j++) {
                answer[idx++] = arr[i][j];
            }
        }
        for (int j = 0; j <= ej; j++) {
            answer[idx++] = arr[ei][j];
        }

//        System.out.print("[");
//        for (int num : answer) {
//            System.out.print(num + ", ");
//        }
//        System.out.println("]");
        return answer;
    }
    public static void main(String[] args) {
        solution(3, 2, 5);
        solution(4, 7, 14);
    }
}

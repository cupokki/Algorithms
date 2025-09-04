package programmers.n87390;

public class Solution {
    public static int[] solution(int n, long left, long right) {

        int si = (int)left / n;
        int sj = (int)left % n;
        int ei = (int)right / n;
        int ej = (int)right % n;

        int[] arr = new int[(ei - si + 1) * n];
        for (int i = si; i <= ei; i++) {
            int idx = i + 1;
            for (int j = 0; j < n; j++) {
                if (j > idx) idx++;
                arr[i * n + j] = idx;
            }

        }

        int range = (int)(right - left) + 1;
        int[] answer = new int[range];
        int idx = 0;
        while(arr[idx] != 0) {
            idx++;
        }
        for (int i = 0 ; i < range; i++) {
            answer[i] = arr[idx + i];
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

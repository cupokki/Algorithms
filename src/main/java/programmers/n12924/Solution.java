package programmers.n12924;

public class Solution {
    static int solution(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = i; j <= n; j++) {
                sum += j;
                if (sum == n) {
                    count++;
                    break;
                } else if (sum > n) {
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(solution(15) == 4);
    }
}

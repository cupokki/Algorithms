package programmers.n181187;

public class Solution {
    public static long solution(int r1, int r2) {
        long quarter = 0;

        for (long x = 1; x <= r2; x++) {
            long y1 = 0;
            if (x < r1) {
                y1 = (long) Math.ceil(Math.sqrt(((long)r1 * r1) - (x * x)));
            }

            long y2 = (long) Math.floor(Math.sqrt(((long)r2 * r2) - (x * x)));

            quarter += y2 - y1 + 1;
        }

        long answer = quarter * 4;
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(2, 3));
        System.out.println(solution(1, 2));
    }
}




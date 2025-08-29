package programmers.n12985;

public class Solution {
    static int solution(int n, int a, int b) {


        int result = 1;
        while (true) {
            if (a % 2 == 0) a /= 2;
            else a = (a + 1) / 2;

            if (b % 2 == 0) b /= 2;
            else b = (b + 1) / 2;

            if (a == b) {
                break;
            }
            result++;
        }
        // 1 2 3 (4) 5 6 (7) 8
        // 1 (2) 3 (4)
        // 1/ 2



        return result;
    }

    public static void main(String[] args) {


        System.out.println(solution(8, 4, 7));
    }
}

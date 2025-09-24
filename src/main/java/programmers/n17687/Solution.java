package programmers.n17687;

public class Solution {
    // n : 진법 (최대 16진법)
    // t : 미리구할 숫자의 갯수
    // m : 참여인원 수
    // p : 플레이어 순서
    static char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static String solution(int n, int t, int m, int p) {
        String answer = "";
        int current = 0;
        int idx = 0;

        while (answer.length() != t) {
            StringBuilder sb = new StringBuilder();
            int temp = current;
            while(temp >= n) {
                sb.append(numbers[current % n]);
                temp /= n;
            }
            sb.append(numbers[temp]);
            String str = sb.reverse().toString();
//            String str = Integer.toString(current, n);
            for (int i = 0; i < str.length(); i++) {
                if (answer.length() == t)
                    break;
                if (idx % m == p - 1) {
                    answer += str.charAt(i);
                }
                idx++;
            }
            current++;
        }
        return answer.toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(solution(2, 4, 2, 1));
        System.out.println(solution(16, 16, 2, 1));
        System.out.println(solution(16, 16, 2, 2));
    }
}

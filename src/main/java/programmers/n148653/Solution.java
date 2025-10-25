package programmers.n148653;

public class Solution {
    // +-10^c(c는 0이상 정수)의 층계로만 이동가능하다.
    // 현재 층이 주어질 때, 최소한의 이동으로 0층으로 이동한다.
    // 최소한의 이동 횟수를 출력하라.
    // 주어진 층수는 1억 이하의 자연수
    /*
    DP?
     */
    public static int solution(int storey) {
        int answer = 0;
        while (storey != 0) {
            int current = storey % 10;
            if (current < 5) {
                answer += current;
                storey /= 10;
            } else {
                answer += 10 - current;
                storey /= 10;
                storey += 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(16));
        System.out.println(solution(2554));
    }
}

package programmers.n42897;

public class Solution {
    /*
    도둑은 n개의 집을 턴다.
    집은 원형을 배치되어있으며, 서로 인접한 두 집을 털면 경보가 울린다.
    가장 많이 돈을 출력하라.
    */
    public int solution(int[] money) {
        int answer = 0;

        int n = money.length;
        int[] first = new int[n]; // 첫번째 집 포함
        int[] second = new int[n + 1]; // 두번째 집 포함

        first[1] = money[0];
        first[2] = money[0];

        second[2] = money[1];

        for (int i = 2; i < n; i++) {
            first[i] = Math.max(first[i - 2] + money[i - 1], first[i - 1]);
        }

        for (int i = 2; i <= n; i++) {
            second[i] = Math.max(second[i - 2] + money[i - 1], second[i - 1]);
        }

        // answer = Math.max(
        //     Math.max(first[n - 1], first[n - 2]),
        //     Math.max(second[n], second[n - 1])
        // );
        answer = Math.max(first[n - 1], second[n]);
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[]{1, 2, 3, 1}));
    }
}
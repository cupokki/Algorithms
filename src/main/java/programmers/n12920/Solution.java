package programmers.n12920;

public class Solution {
    /*
        코어수는 [2,10000]
        코어당 작업 처리속도 1만이하
        최대 일의 수는 5만개 이하

        2개이상의 코어가 가능할때는, 앞쪽 코어부터 작업을 수행.
        마지막 작업을 처리하는 코어의 번호를 출력하라.
        */
    public int solution(int n, int[] cores) {
        int answer = 0;

        int len = cores.length;

        if (len >= n) {
            return n;
        }

        int[] current = new int[len];

        for (int i = 0; i < len; i++) {
            current[i] = cores[i];
            n--;
        }

        while (n > 0) {
            for (int i = 0; i < len; i++) {
                if (current[i] > 0) current[i]--;
            }

            for (int i = 0; i < len; i++) {
                if (current[i] == 0) {
                    current[i] = cores[i];
                    n--;
                    if (n == 0) {
                        answer = i + 1;
                        break;
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(6, new int[]{1, 2, 3}));
    }
}
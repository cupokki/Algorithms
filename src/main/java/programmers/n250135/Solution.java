package programmers.n250135;

public class Solution {
    /*
    아날로그 시계가 초침이 시침, 분침과 겹칠때 마다 알람이 울린다. (동시에 겹치면 한번으로 카운트)
    특정 시간동안 알람이 울린 횟수를 출력하라.

    초침이 한바퀴 도는 동안 분침은 1/60을 움직인다.
        -> 임의의 시간에서 최대 61초는 경과해야 한번 더 겹친다.
        -> 시침또한 그렇다.
    */
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {

        int cnt = 0;

        int lh = 0, lm = 0, ls = 0; // last collusion

        while (h1 != h2 && m1 != m2 && s1 != s2) {
//            if () {
//                cnt++;
//            }

            s1++;
            if (s1 == 60) {
                s1 = 0;
                m1++;
            }
            if (m1 == 60) {
                m1 = 0;
                h1++;
            }
            if (h1 == 24) {
                h1 = 0;
            }
        }

        if (cnt == 0) return -1;
        return cnt;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(0,5	,30,0,7,0)); // 2
    }

}

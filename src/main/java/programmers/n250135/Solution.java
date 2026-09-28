package programmers.n250135;

public class Solution {
    /*
    아날로그 시계가 초침이 시침, 분침과 겹칠때 마다 알람이 울린다. (동시에 겹치면 한번으로 카운트)
    특정 시간동안 알람이 울린 횟수를 출력하라.

    초침이 한바퀴 도는 동안 분침은 1/60을 움직인다.
        -> 임의의 시간에서 최대 61초는 경과해야 한번 더 겹친다.
        -> 시침또한 그렇다.

    초침 : 1/60 바퀴/sec
    분침 : 1/3600 바퀴/sec
    시침 : 1/43200 바퀴/sec

    */
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {

        int startSec = h1 * 60 * 60 + m1 * 6 + s1;
        int endSec = h2 * 60 * 60 + m2 * 6 + s2;

        int cnt = count(endSec) - count(startSec); //

        if (cnt == 0) return -1;

        return cnt;
    }

    // 0부터 t초까지 알람 횟수
    int count(int sec) {
        return 0;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(0,5	,30,0,7,0)); // 2
    }

}

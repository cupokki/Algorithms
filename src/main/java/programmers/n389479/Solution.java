package programmers.n389479;

public class Solution {
    /*
    m명 늘때마다 서버 한대씩 추가한다.

    증설한 서버는 k시간 동안 운영하고 그이후 반납합
    시간대별 이용자 수가 주어지면, 하루동안 몇번의 서버 증설이 이루어지는지 구하라.
     */
    public static int solution(int[] players, int m, int k) {
        int answer = 0;

        int server;
        for (int i = 0; i < players.length; i++) {
//            if(players[i] < server) {
//                   answer++;
//            }

        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(
                solution(new int[]{0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5},
                3, 4)
        );

        System.out.println(
                solution(new int[]{0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0, 0},
                        5, 1)
        );

        System.out.println(
                solution(new int[]{0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                        1, 1)
        );
    }
}

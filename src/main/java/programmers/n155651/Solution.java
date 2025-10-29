package programmers.n155651;

public class Solution {
    /*
    최소한의 객실로 대실예약을 받는다.
    입실시간과 퇴실시간이 주어질 때, 필요로한 최소의 방의 수를 구하라.
    단 청소시간은 10분임
     */
    public static int solution(String[][] book_time) {
        int len = book_time.length;
        int[][] sheet = new int[len][2];
        for (int i = 0; i < len; i++) {
            String[] s = book_time[i][0].split(":");
            sheet[i][0] = Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
            s = book_time[i][1].split(":");
            sheet[i][1] = Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
            if (s[0].equals("23") && Integer.parseInt(s[1]) <= 50) {
                sheet[i][1] += 10;
            }
        }
        int answer = 1;
        for (int i = 0; i < len; i++) {

        }

        return answer;
    }

    public static void main(String[] args) {
        String[][] i1 = {
                {"15:00", "17:00"},
                {"16:40", "18:20"},
                {"14:20", "15:20"},
                {"14:10", "19:20"},
                {"18:20", "21:20"}
        };
        System.out.println(solution(i1));

        String[][] i2 = {
                {"09:10", "10:10"},
                {"10:20", "12:20"}
        };
        System.out.println(solution(i2));

        String[][] i3 = {
                {"10:20", "12:30"},
                {"10:20", "12:30"},
                {"10:20", "12:30"}
        };
        System.out.println(solution(i3));
    }
}

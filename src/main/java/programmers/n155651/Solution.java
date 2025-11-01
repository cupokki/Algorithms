package programmers.n155651;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
//            if (s[0].equals("23") && Integer.parseInt(s[1]) <= 50) {
                sheet[i][1] += 10;
//            }
        }

        Arrays.sort(sheet, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        Map<Integer, Integer> map = new HashMap<>(); // room num - cleaned up time
        map.put(0, sheet[0][1]);
        int answer = 1;
        for (int i = 1; i < len; i++) {
            boolean done = false;
            for (var e : map.entrySet()) {
                if (e.getValue() <= sheet[i][0]) { // 현 시작시간이 대상종료보다 이후
                    map.put(e.getKey(), sheet[i][1]);
                    done = true;
                    break;
                }
            }
            if (!done)
                map.put(map.size(), sheet[i][1]);
        }

        answer = map.size();
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

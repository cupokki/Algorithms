package programmers.n72414;

import java.time.LocalTime;

public class Solution {
    /*
    로그는 최대 30만개
    100시간은 360,000초
     */
    public static String solution(String pt, String adv, String[] logs) {
        String answer = "";
        int playTime = toInt(pt);
        int advTime = toInt(adv);

        int[] time = new int[playTime + 1];

        for (String log : logs) {
            String[] t = log.split("-");
            int s = toInt(t[0]);
            int e = toInt(t[1]);

            time[s] += 1;
            time[e] -= 1;
        }

        for (int i = 1; i < time.length; i++) {
            time[i] += time[i - 1];
        }

        int startAt = 0;
        long sum = 0;
        for (int i = 0; i < advTime; i++) {
            sum += time[i];
        }
        long max = sum;

        for (int i = advTime; i < playTime; i++) {
            sum = sum - time[i - advTime] + time[i];
            if (sum > max) {
                startAt = i - advTime + 1;
                max = sum;
            }
        }

        int h = startAt / 3600;
        int m = (startAt % 3600) / 60;
        int s = startAt % 60;

        answer = String.format("%02d:%02d:%02d",h, m, s);
        return answer;
    }
    static int toInt(String t) {
        String[] arg = t.split(":");
        return  Integer.parseInt(arg[0]) * 60 * 60 +
                Integer.parseInt(arg[1]) * 60 +
                Integer.parseInt(arg[2]);
    }

    public static void main(String[] args) {
//        System.out.println(solution("02:03:55", "00:14:15", new String[]{
//                "01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"
//        }));
//        System.out.println(solution("99:59:59", "25:00:00", new String[]{
//                "69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"
//        }));
        System.out.println(solution("50:00:00", "50:00:00", new String[]{
                "15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"
        }));
    }
}

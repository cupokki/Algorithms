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
            time[e + 1] -= 1;
        }

        for (int i = 1; i < time.length; i++) {
            time[i] += time[i - 1];
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i <= advTime; i++) {
            sum += time[i];
        }
        for (int i = 1; i <= playTime - advTime; i++) {
            sum -= time[i - 1];
            sum += time[i + advTime];
            if (sum > max) {
                min = i;
                max = sum;
            }
        }

        int h = min / 3600;
        int m = (min % 3600) / 60;
        int s = min % 60;

        answer = h + ":" + m + ":" + s;
        return answer;
    }
    static int toInt(String t) {
        String[] arg = t.split(":");
        return  Integer.parseInt(arg[0]) * 60 * 60 +
                Integer.parseInt(arg[1]) * 60 +
                Integer.parseInt(arg[2]);
    }

    public static void main(String[] args) {
        System.out.println(solution("02:03:55", "00:14:15", new String[]{
                "01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"
        }));
    }
}

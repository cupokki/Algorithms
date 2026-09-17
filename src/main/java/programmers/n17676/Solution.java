package programmers.n17676;

import java.util.*;
import java.time.*;

public class Solution {
    /*
    기준시작
    기준끝

    현재로그시작
    현재로그끝


    현재로그의 끝이 구간안(end 이전)에 포함되어야한다.
        ->

    현재로그의 끝이 기준시작을 지났다면, i++
    */
    public int solution(String[] lines) {
        int answer = 0;

        int n = lines.length;

        for (int i = 0; i < n; i++) {

            int tps = 0;

            String[] tokens = lines[i].split(" ");

            // TPS 측정 구간
            int start = toMillisecond(tokens[1]);
            int end = start + 999;

            for (int j = 0 ; j < n; j++) {
                tokens = lines[j].split(" ");
                int responseTime = toMillisecond(tokens[1]);
                int requestTime = responseTime - toMillisecond(tokens[2]) + 1;

                if (start <= responseTime && requestTime <= end) {
                    tps++;
                }
            }

            answer = Math.max(tps, answer);
        }

        return answer;
    }

    int toMillisecond(String time) {
        if (time.charAt(time.length() - 1) == 's') {
            time = time.substring(0, time.length() - 1);
            if (time.length() == 1) return Integer.parseInt(time) * 1000;
            String[] tokens = time.split("\\.");
            return Integer.parseInt(tokens[0]) * 1000
                    + Integer.parseInt(tokens[1]) * (int) Math.pow(10, 3 - tokens[1].length());
        }

        String[] tokens = time.split("[:.]");
        int hour = Integer.parseInt(tokens[0]) * 60 * 60 * 1000;
        int minute = Integer.parseInt(tokens[1]) *  60 * 1000;
        int second = Integer.parseInt(tokens[2]) * 1000;
        int millisecond = Integer.parseInt(tokens[3]) * (int) Math.pow(10, 3 - tokens[3].length());
        return hour + minute + second + millisecond;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new String[] {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"})); // 1
        System.out.println(sol.solution(new String[] {"2016-09-15 01:00:04.002 2.0s","2016-09-15 01:00:07.000 2s"})); // 2
        System.out.println(sol.solution(new String[] {"2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"})); // 7
    }



}
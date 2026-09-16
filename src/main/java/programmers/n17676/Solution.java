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
            LocalTime end = LocalTime.parse(tokens[1]);
            LocalTime start = end.minusSeconds(1).plusNanos(1_000_000L);

            for (int j = i; j < n; j++) {
                tokens = lines[j].split(" ");
                LocalTime responseTime = LocalTime.parse(tokens[1]);
                double t = Double.valueOf(tokens[2].substring(0, tokens[2].length() - 1)); // duration
                LocalTime requestTime = responseTime.minusNanos((long)((t * 1_000_000_000L) - 1_000_000L));

                if (responseTime.isAfter(start) || requestTime.isBefore(end)) {
                    tps++;
                }

                if (responseTime.isBefore(start)) {
                    i++;
                }
            }

            answer = Math.max(tps, answer);
        }


        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
//        System.out.println(sol.solution(new String[] {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"})); // 1
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
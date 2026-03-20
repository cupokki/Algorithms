package programmers.n17678;

import java.time.LocalTime;
import java.util.*;
public class Solution {
    /*
    카카오 무료셔틀을 타는 크루
    - 9시부터 n회(10이하) t분(60이하) 간격으로 운행하여 최대 m명(45 이하)의 승객이 탈 수 있다.
    - 셔틀 도착시 도착순 대기열에 도착한 순간에 크루까지 포함하여 태우고 출발
    - 가장 늦은 도착시각을 구한다.
    - 단 같은 시각에 도착하더라도 제일 뒤에 선다.
    - 23:59까지만 다룬다.

    1. 버스에 자리가 남는 경우 -> 버스가 떠나는시간 - 1
    2. 버스에 자리가 없는 경우 -> **마지막 경우 - 1
        - 마지막 시간 대기자가 k명이면 시간k - 1
    */
    public static String solution(int n, int t, int m, String[] timetable) {
        String answer = "";

        Arrays.sort(timetable);

        int crewCnt = timetable.length;

        int i = 0; // i번쨰 버스
        int crewIdx = 0;
        LocalTime now = LocalTime.parse("09:00");
        LocalTime leaveAt = now.plusMinutes(t); // 첫차의 떠나는 시간
        LocalTime crewArrivedAt = LocalTime.parse(timetable[crewIdx]); // 대기열의 첫 번째 크루

        // n - 1 번 버스까지 처리
        while (i < n - 1) {
            int passengerCnt = 0;
            // 크루가 i번쨰 버스에 탑승
            while (crewIdx < crewCnt && crewArrivedAt.isBefore(leaveAt) && passengerCnt < m) {
                crewArrivedAt = LocalTime.parse(timetable[crewIdx++]);
                passengerCnt++;
            }
            now = now.plusMinutes(t);
            leaveAt = now.plusMinutes(t);
            i++;
        }

        // 마지막 버스
        int passengerCnt = 0;
        // 마지막 최소 한자리가 남을때 까지 탑승
        while (crewIdx < crewCnt - 1 && crewArrivedAt.isBefore(leaveAt) && passengerCnt < m - 1) {
            crewArrivedAt = LocalTime.parse(timetable[crewIdx++]);
            passengerCnt++;
        }

        // 마지막 탑승자의 대기 시간이 현버스 출발시간 전이면 이전 버스를 타야한다.
        answer = leaveAt.minusMinutes(1).toString();

        return answer;
    }

    public static void main(String[] args) {
//        System.out.println(solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"}));
//        System.out.println(solution(2, 10, 2, new String[]{"09:10", "09:09", "08:00"}));
//        System.out.println(solution(2, 1, 2, new String[]{"09:00", "09:00", "09:00", "09:00"}));
        System.out.println(solution(1, 1, 5, new String[]{"00:01", "00:01", "00:01", "00:01", "00:01"}));
        System.out.println(solution(1, 1, 1, new String[]{"23:59"}));
        System.out.println(solution(10, 60, 45, new String[]{"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"}));
    }
}
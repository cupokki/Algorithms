package programmers.n92341;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    // fess: 기본시간, 기본요금, 단위시간, 단위요금
    // 출차 내역없으면 23시 59분에 출차된 것으로 처리.
    public static int[] solution(int[] fees, String[] records) {
        Map<String, LocalTime> map = new HashMap<>();
        Map<String, Integer> timeMap = new HashMap<>();
        DateTimeFormatter form = DateTimeFormatter.ofPattern("HH:mm");
        for (String row : records) {
            String[] col = row.split(" ");
            if (col[2].equals("IN")) {
                LocalTime t = LocalTime.parse(col[0], form);
                map.put(col[1], t);
            } else {
                int minute = (int) Duration.between(map.get(col[1]), LocalTime.parse(col[0], form)).toMinutes();
                map.remove(col[1]);
                timeMap.merge(col[1], minute, Integer::sum);
            }
        }
        if (!map.isEmpty()) {
            for (var k : map.keySet()) {
                int minute = (int) Duration.between(map.get(k), LocalTime.parse("23:59", form)).toMinutes();
                timeMap.merge(k, minute, Integer::sum);
            }
        }
//        Map<String, Integer> feeMap = new HashMap<>();
//        timeMap.entrySet().forEach(e -> {
//            int total = fees[1];
//            if (e.getValue() <= fees[0]) {
//                feeMap.put(e.getKey(), total);
////
//            }
//            total += (int) Math.ceil(((e.getValue() - fees[0] * 1.0)) / fees[2]) * fees[3];
//            feeMap.put(e.getKey(), total);
//        });
//
//
//        var result= feeMap.keySet().stream().sorted().mapToInt(
//                k ->feeMap.get(k))
//                .toArray();
//        return result;

        return timeMap.entrySet().stream()
                .sorted((a, b) -> a.getKey().compareTo(b.getKey()))
                .mapToInt(e -> {
                    int total = fees[1];
                    if (e.getValue() <= fees[0]) {
                        return total;
                    }
                    total += (int) Math.ceil(((e.getValue() - fees[0] * 1.0)) / fees[2]) * fees[3];
                    return total;
                }).toArray();
    }


    public static void main(String[] args) {
        solution(
                new int[] {180, 5000, 10, 600},
                new String[] {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"}
                );

        solution(
                new int[] {120, 0, 60, 591},
                new String[] {"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"}
                );


        solution(
                new int[] {1, 461, 1, 10},
                new String[] {"00:00 1234 IN"}
        );
    }
}

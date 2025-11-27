package programmers.n17683;

import java.util.PriorityQueue;

class Solution {
    /*
    문자열 m : 네오가 기억하는 멜로디
    문자열 배열 musicinfos: 시작시간, 종료시간, 곡명, 멜로디가 ","로 구분된 문자열 배열

    멜로디의 음은 1분에 1개씩 재생된다.

    - 조건을 만족하는 음악이 여러개라면 가장 이름이 긴 제목을 반환
    - 재생시간이 같다면 먼저 입력된 음악 우선
    - 없다면 "(None)" 출력

    곡명이 긴 순으로 정렬하고, 재생시간이 빠른 순으로 정렬한다.
    */
    public static String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        PriorityQueue<String[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0].equals(b[0])) {
                return a[1].compareTo(b[1]);
            }
            return Integer.parseInt(a[0]) - Integer.parseInt(b[0]);
        });
        for(String s : musicinfos) {
            String[] token = s.split(",");
            int min = getMin(token[0], token[1]);
            String runningMin = String.valueOf(min);
            String title = token[2];
            String convertedMelody = "";
            char[] temp = token[3].toCharArray();
            for (int i = 0; i < min; i++) {
                if (i + 1 < temp.length && temp[i + 1] == '#') {
                    convertedMelody += temp[i % temp.length] + temp[(i + 1) % temp.length];
                    i++;
                } else {
                    convertedMelody += temp[i % temp.length];
                }
            }

            pq.offer(new String[]{runningMin, title, convertedMelody});
        }

        while (!pq.isEmpty()) {
            String[] str = pq.poll();
            if (str[2].contains(m)){
                answer = str[1];
                break;
            }
        }

        return answer;
    }
    static int getMin(String s, String e) {
        String[] start = s.split(":");
        String[] end = e.split(":");
        int startH = Integer.parseInt(start[0]);
        int endH = Integer.parseInt(end[0]);
        int startM = Integer.parseInt(start[1]);
        int endM = Integer.parseInt(end[1]);
        return (endH - startH) * 60 + endM- startM;
    }

    public static void main(String[] args) {
//        System.out.println(solution("ABCDEFG", new String[]{"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
//        System.out.println(solution("CC#BCC#BCC#BCC#B", new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}));
        System.out.println(solution("ABC", new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
    }
}
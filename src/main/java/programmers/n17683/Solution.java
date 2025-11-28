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

    **C와 C# 같은 경우를 구분하지 못한다.
    */
    public static String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        PriorityQueue<String[]> pq = new PriorityQueue<>((a, b) -> {
            return Integer.parseInt(b[0]) - Integer.parseInt(a[0]); // 긴 순
        });
        for(String s : musicinfos) {
            String[] token = s.split(",");
            int duration = getMin(token[0], token[1]);
            String runningMin = String.valueOf(duration);
            String title = token[2];
            String convertedMelody = convertString(token[3], duration);
            pq.offer(new String[]{runningMin, title, convertedMelody});
        }

        int sharp = (int) m.chars().filter(c-> c == '#').count();
        String target = convertString(m, m.length() - sharp);

        while (!pq.isEmpty()) {
            String[] str = pq.poll();
            if (str[2].contains(target)){
                answer = str[1];
                break;
            }
        }

        return answer;
    }
    static String convertString(String str, int n) {
        String result = "";
        char[] temp = str.toCharArray();
        int sIdx = 0;
        int idx = 0;
        while (idx != n) {
            if (temp[(sIdx + 1) % temp.length] == '#') {
                result += temp[sIdx % temp.length] + "#";
                sIdx++;
            } else {
                result += temp[sIdx % temp.length] + " ";
            }
            sIdx++;
            idx++;
        }
        return result;
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
        System.out.println(solution("CC#BCC#BCC#BCC#B", new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}));
//        System.out.println(solution("ABC", new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
    }
}
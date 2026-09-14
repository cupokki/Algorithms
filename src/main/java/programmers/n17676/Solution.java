package programmers.n17676;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Solution {
    /*
    서버증설, 작년 9월 15일 로그를 분석해 최대 TPS 계산한다.
    최대 TPS: 응답 여부와 관계없이 임의 시간부터 1초간 처리하는 요청의 최대 개수
        *TPS 산정시 닫힌구간으로 카운팅한다.
    
    - 로그 문자열 마다 응답완료시간s, 처리시간t가 공백으로 구분되어 있다.
    - t: 3이하의 실수
    - 로그 문자열 lines: s기준 오름차순 정렬되어있다. 최대 2000개의 로그가 있다.
    
    => 날짜정보는 필요없다.
    
    */
    public int solution(String[] lines) {
        int answer = 0;

        for (int i = 0; i < lines.length; i++) {
            String[] tokens = lines[i].split(" ");
//            LocalTime s = LocalTime.from(tokens[1]);
            Double t = Double.valueOf(tokens[2].substring(0, -1));

        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new String[]{"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"})); // 1
        System.out.println(sol.solution(new String[]{"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"})); // 2
        System.out.println(sol.solution(new String[]{"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s","2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"})); // 7
    }
}
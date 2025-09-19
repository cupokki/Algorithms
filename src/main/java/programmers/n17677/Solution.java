package programmers.n17677;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    //자카드 유사도 J(A, B) =  A intersect B / A union B
    // A or B 가 0이면 J(A, B) = 1
    // 중복허용집합인 "다중집합"으로 확장, '로 표시
    // A' intersect B' = 일치하는 문자 중 최소인 쪽의 개수
    // A' intersect B' = 일치하는 문자 중 최대인 쪽의 개수
    // 문자를 두 글자씩 잘라서 집합화 하여, 두 문장을 비교한다.
    // 유사도는 실수이므로 65536을 곱하고 정수부만 출력한다.
    public static int solution(String str1, String str2) {
        double similarity = 0.0;

        Map<String, Integer> a = new HashMap<>();
        Map<String, Integer> b = new HashMap<>();

        for (int i = 2; i <= str1.length(); i++) {
            String element = str1.substring(i - 2, i);
            element = element.toUpperCase();
            if (element.matches("^[A-Z]+$")) {
                a.merge(element, 1, Integer::sum);
            }
        }
        for (int i = 2; i <= str2.length(); i++) {
            String element = str2.substring(i - 2, i);
            element = element.toUpperCase();
            if (element.matches("^[A-Z]+$")) {
                b.merge(element, 1, Integer::sum);
            }
        }

        int intersectCount = 0;
        int unionCount = 0;

        var large = a.size() > b.size() ? a : b;
        var small = a.size() <= b.size() ? a : b;

        // calculate intersectCount
        for (String s : small.keySet()) {
            if (large.containsKey(s)) {
                intersectCount += small.get(s);
            }
        }

        // calculate unionCount
        for (String s : large.keySet()) {
            unionCount += large.get(s);
        }

        for (String s : small.keySet()) {
            if (!large.containsKey(s)) {
                unionCount += small.get(s);
            }
        }

        if (unionCount == 0) {
            similarity = 1.0;
        } else {
            similarity = (double)intersectCount / (double) unionCount;
        }
        return (int) Math.floor(similarity * 65536);
    }

    public static void main(String[] args) {
        System.out.println(solution("FRANCE", "french"));
        System.out.println(solution("handshake", "shake hands"));
        System.out.println(solution("aa1+aa2", "AAAA12"));
        System.out.println(solution("E=M*C^2", "e=m*c^2"));
    }
}

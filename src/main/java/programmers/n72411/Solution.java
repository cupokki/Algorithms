package programmers.n72411;

public class Solution {
    /*
    orders의 각 문자열에서 course[i]개의 문자를 선택하여 같은 문자열을 2개 이상으로 구성한다.
    이 규칙의 모든 조합을 출력한다.(오름차순)
     */
    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        return answer;
    }

    public static void main(String[] args) {
        String[] orders1 = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course1 = {2, 3, 4};
        String[] orders2 = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course2 = {2, 3, 5};
        String[] orders3 = {"XYZ", "XWY", "WXA"};
        int[] course3 = {2, 3, 4};

        System.out.println(solution(orders1, course1));
        System.out.println(solution(orders2, course2));
        System.out.println(solution(orders3, course3));
    }
}

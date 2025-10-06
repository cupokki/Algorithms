package programmers.n17686;

import java.util.Arrays;

public class Solution {
    /*
    문자열을 문자순으로 정렬하지 않고 숫자부를 순서대로 정렬하고자한다.
    문자열은 HEAD, NUMBER, TAIL 3파트로 나뉜다.
    HEAD: 한글자 이상의 문자열, 대소구분x
    NUMBER: 한글자 이상, 5글자 이하의 연속된 숫자로 이루어진 문자열. 정수로 변환후 비교
    TAIL: 나머지 뒷 부분의 뭇자열.
     */
    public static String[] solution(String[] files) {
        return Arrays.stream(files).sorted((a, b) -> {
            String[] tempA = parseTitle(a);
            String[] tempB = parseTitle(b);

            if (!tempA[0].equals(tempB[0])) {
                return tempA[0].compareTo(tempB[0]);
            }

            return Integer.parseInt(tempA[1]) - Integer.parseInt(tempB[1]);

        }).toArray(String[]::new);
    }
    static String[] parseTitle(String name) {

        String[] temp = new String[2];

        temp[0] = name.split("[0-9]")[0].toUpperCase();
//        temp[1] = name.replaceAll(".*?([0-9]+).*","$1");
        temp[1] = name.replaceAll(".*?(\\d+).*","$1"); // 전체를 $1으로 치환

        return temp;
    }

    public static void main(String[] args) {
//        var a = solution(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"});
        var a = solution(new String[]{"img01.png", "img1.png"});
//        var b = solution(new String[]{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"});
//        var b = solution(new String[]{"ss01", "ss1"});
        System.out.println();
    }
}









































































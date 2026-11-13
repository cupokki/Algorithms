package programmers.n389481;

import java.util.Arrays;

public class Solution {
    /*
    goal
        - 주문서에서 특정 주문을 지웠을때, n번째 주문을 찾아낸다.

    given information
        - 주문은 알파벳 소문자 11자
        - 글자수가 적은 순
        - 글자수가 같다면 사전순
        - n은 10^15 이하의 자연수
        - bans의 길이는 300000 이하.
        - bans에 중복된 주문은 없음

    n번째 문자열는 바로 알 수 있다.
    bans[i]의 문자열도 몇번째인지 유추가능하다.
    bans

    이분 탐색?
     */
    public String solution(long n, String[] bans) {
        String answer = "";

        Arrays.sort(bans);

        long[] nums = new long[bans.length];

        long targetIdx = 0;
        for (int i = 0; i < bans.length; i++) {
            int digit = bans[i].length() - 1;
            for (char c : bans[i].toCharArray()) {
                nums[i] += (c - 'a' + 1) * (long) Math.pow(26, digit);
                digit--;
            }
            if (nums[i] > n) {
                targetIdx = n - i; // 인덱스 오류
            }
        }
        StringBuilder sb = new StringBuilder();

        while (targetIdx > 26 && targetIdx % 26 != 0) {
            sb.append((char) ('a' + (targetIdx / 26)));
            targetIdx /= 26;
        }
        sb.append((char)('a' + targetIdx));

        return answer;
    }

    public static void main(String[] args) {
        
        Solution sol = new Solution();
        
        // ah
        System.out.println(sol.solution(30, new String[]{"d", "e", "bb", "aa", "ae"}));
        
        // jxk
        System.out.println(sol.solution(7388, new String[]{"gqk", "kdn", "jxj", "jxi", "fug", "jxg", "ewq", "len", "bhc"}));
    }
}

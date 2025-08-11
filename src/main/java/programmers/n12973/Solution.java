package programmers.n12973;

public class Solution {
    static int solution(String s) {
        StringBuilder sb = new StringBuilder(s);
        int idx = 1;
        while(idx < sb.length()) {
            if(sb.charAt(idx - 1) == sb.charAt(idx)) {
                sb.deleteCharAt(idx - 1);
                sb.deleteCharAt(idx - 1);
                idx = 1;
            }
            else {
                idx++;
            }
        }

        return sb.isEmpty() ? 1 : 0;
    }
    public static void main(String[] args) {
        System.out.println(solution("baabaa"));
        System.out.println(solution("cdcd"));
    }
}

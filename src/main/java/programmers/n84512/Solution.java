package programmers.n84512;

public class Solution {

    static boolean done = false;
    static int answer = 0;
    static int count = 0;
    static char[] cSet = {'A', 'E', 'I', 'O', 'U'};


    public static int solution(String word) {
//        answer = 0;
//        count = 0;
//        done = false;
        backtracking("", word);
        return answer;
    }

    public static void backtracking(String current, String word) {
        if (done) return;

        // 단어 하나 생성했을 때 count 증가
        if (!current.isEmpty()) count++;

        if (current.equals(word)) {
            answer = count;
            done = true;
            return;
        }

        if (current.length() == 5) return;

        for (int i = 0; i < cSet.length; i++) {
            backtracking(current + cSet[i], word);
        }
    }



    public static void main(String[] args) {
        System.out.println(solution("AAAAE"));
        System.out.println(solution("AAAE"));
        System.out.println(solution("I"));
        System.out.println(solution("EIO"));
    }
}

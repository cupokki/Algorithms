package programmers.n1831;

public class Solution {
    /*
    1단계에서 음높이 3배 (*)
    2,3단계에서 음높이 각 1씩 증가 (+)
    3단고음 1회 -> *++

    3단고음후 연달아 삼단 고음 -> *++(*++)
    2단고음후 3단고음 -? *+(*++)+

    음이 1에서 3단고음 음으로 정할때, 몇번의 3단 고음 문자열이 몇개 가능하가
    힙력은 5이하 Integer_max_value
     */
    public int solution(int n) {
        int answer = 0;



        return answer;
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(15)); // 1
        System.out.println(sol.solution(24)); // 0, **++++**++, *+**+++++
        System.out.println(sol.solution(41)); // 2
        System.out.println(sol.solution(2147483647)); // 1735
    }
}

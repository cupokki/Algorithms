package programmers.n1835;

import java.util.*;

public class Solution {
    /*
    프렌즈 이니셜: {A, C, F, J, M, N, R, T}
    백트래킹? dfs?

    조건의 수는 n개 (100 이하)
    */
    static char[] members = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'}; // 8명
    static int cnt;
    static String[] conditions;
    public static int solution(int n, String[] data) {
        cnt = 0;
        conditions = data;
        memberPos = new int[26];
        dfs(0, 0);
        return cnt;
    }
    static int[] memberPos;
    static void dfs(int depth, int visitedMask) {
        if (depth == members.length) {
            if (vaildate()) cnt++;
            return;
        }

        for (int i = 0; i < members.length; i++) {
            if ((visitedMask & (1 << i)) == 0) {
                memberPos[members[depth] - 'A'] = i;
                dfs(depth + 1, visitedMask | (1 << i));
            }
        }
    }
    static boolean vaildate() {
        for (String cond : conditions) {
            int a = memberPos[cond.charAt(0) - 'A'];
            int b = memberPos[cond.charAt(2) - 'A'];
            int dist = Math.abs(a - b) - 1;
            int expect = cond.charAt(4) - '0';
            switch (cond.charAt(3)) {
                case '=':
                    if (dist != expect) return false;
                    break;
                case '<':
                    if (dist >= expect) return false;
                    break;
                case '>':
                    if (dist <= expect) return false;
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution(2, new String[]{"N~F=0", "R~T>2"}));
        System.out.println(solution(2, new String[]{"M~C<2", "C~M>1"}));
    }
}
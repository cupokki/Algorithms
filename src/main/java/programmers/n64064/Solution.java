package programmers.n64064;

import java.util.*;

public class Solution {
    /*
    사용자 중 비정상 응모 = 불량사용자 찾기 = 제재 아이디
    개인정보 마스킹 "*"
    아이디당 최소하나의 *
    마스킹한게 겹칠 수 있다.

    응모자와 마스킹된 재재아이디가 주어질때,
    가능한 경우의 수를 구하라.

    아이디 개수 8이하 자연수
    아이디 길이 최대 8이하 자연수
     */
    static String[] users;
    static String[] bans;
    static Set<Integer> set;
    static boolean[][] isMatch;
    public static int solution(String[] user_id, String[] banned_id) {
        users = user_id;
        bans = banned_id;
        set = new HashSet<>();
        isMatch = new boolean[bans.length][users.length];
        for (int i = 0; i < bans.length; i++) {
            for (int j = 0; j < users.length; j++) {
                isMatch[i][j] = validate(bans[i], users[j]);
            }
        }
        dfs(0, 0);
        int answer = set.size();
        return answer;
    }

    static void dfs(int depth, int bitmask) {
        if (depth == bans.length) {
            set.add(bitmask);
            return;
        }

        for (int i = 0; i < users.length; i++) {
            if ((bitmask & (1 << i)) == 0 && isMatch[depth][i]) {
                dfs(depth + 1, bitmask | (1 << i));
            }
        }
    }

    static boolean validate(String a, String b) {
        int len = a.length();
        if (len != b.length()) return false;
        for (int i = 0 ; i < len; i++) {
            if (a.charAt(i) == '*') continue;
            if (a.charAt(i) != b.charAt(i)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "abc1**"}));
        System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"*rodo", "*rodo", "******"}));
        System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "*rodo", "******", "******"}));
    }
}

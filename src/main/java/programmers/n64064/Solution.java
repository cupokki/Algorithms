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
    static int bannedSize;
    static int userSize;
    static String[] users;
    static String[] bans;
    static int result;
    static Set<String> set;
    public static int solution(String[] user_id, String[] banned_id) {
        result = 0;
        users = user_id;
        bans = banned_id;
        bannedSize = banned_id.length;
        userSize = user_id.length;
        result = 0;
        set = new HashSet<>();
        dfs(0, userSize, new boolean[userSize]);
        return result;
    }

    static void dfs(int depth, int userSize, boolean[] visited) {
        if (depth == bannedSize) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < userSize; i++) {
                if (visited[i]) sb.append(users[i]);
            }
            String str = sb.toString();
//            System.out.println(str);
            if (!set.contains(str)) {
                result++;
                set.add(str);
            }
            return;
        }

        for (int i = 0; i < userSize; i++) {
            if (!visited[i] && validate(bans[depth], users[i])) {
                visited[i] = true;
                dfs(depth + 1, userSize, visited);
                visited[i] = false;
            }
        }
    }

    static boolean validate(String a, String b) {
        int len = a.length();
        if (len != b.length()) {
            return false;
        }
        for (int i = 0 ; i < len; i++) {
            if (a.charAt(i) == '*') {
                continue;
            }
            if (a.charAt(i) != b.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "abc1**"}));
        System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"*rodo", "*rodo", "******"}));
        System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "*rodo", "******", "******"}));
    }
}

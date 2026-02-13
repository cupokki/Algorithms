package programmers.n43163;

import java.util.*;

public class Solution {
    /*
    한번에 한개의 알파벳만 바꿀 수 있다.
    words 안에 있는 단어로만 변환 할 수 있다
     */
    record State(String str, int dist) {}
    public static int solution(String begin, String target, String[] words) {

        int n = words.length;
        int len = begin.length();
//        boolean[] visited = new boolean[n];
        Queue<State> q = new LinkedList<>();
        q.offer(new State(begin, 0));

        int answer = Integer.MAX_VALUE;
        Set<String> set = new HashSet<>();
        set.addAll(List.of(words));
        if (!set.contains(target)) {
            return 0;
        }
        while (!q.isEmpty()) {
            State state = q.poll();

            if (state.dist >= answer) {
                continue;
            }

            for (int i = 0; i < n; i++) {
//                if (visited[i]) continue;
                if (!set.contains(words[i])) continue;
                // 비교하는게 문제인데. 또 반복문으로 해도 성능이 괜찮까?
                if (validate(len, words[i], state.str)) {
//                    visited[i] = true;
                    set.remove(words[i]);
                    if (words[i].equals(target)) {
                        answer = Math.min(answer, state.dist + 1);
                    } else {
                        q.offer(new State(words[i], state.dist + 1));
                    }

                }

            }
        }

        return answer;
    }
    static boolean validate(int len, String a, String b) {
        int cnt = 0;
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != b.charAt(i)) cnt++;
        }
        return cnt == 1;
    }

    public static void main(String[] args) {
        System.out.println(solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        System.out.println(solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log"}));


    }
}

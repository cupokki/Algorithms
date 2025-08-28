package programmers.n12981;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    static int[] solution(int n, String[] words) {
        int[] result = new int[]{0, 0};
        Set<String> set = new HashSet<>();
        int turn = 0;
        char c = words[0].charAt(0);
        for(String w : words) {
            if (set.contains(w) || c != w.charAt(0)) {
                result[0] = turn % n + 1 ; // player num
                result[1] = turn / n + 1; // player's turn
                break;
            }
            set.add(w);
            c = w.charAt(w.length() - 1);
            turn++;
        }
        System.out.println(result[0] + " " + result[1]);
        return result;
    }

    public static void main(String[] args) {
        solution(3, new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"});
        solution(5, new String[]{"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"});
        solution(2, new String[]{"hello", "one", "even", "never", "now", "world", "draw"});
    }
}

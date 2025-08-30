package programmers.n131701;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    static int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        int n = elements.length;


        return set.size();
    }



    public static void main(String[] args) {
        System.out.println(solution(new int[]{7, 9, 1, 1, 4}));
    }
}

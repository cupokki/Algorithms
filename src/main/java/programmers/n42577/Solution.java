package programmers.n42577;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static boolean solution(String[] phone_book) {
        Arrays.sort(phone_book, (a, b) -> a.length() - b.length());
        Set<String> set = new HashSet<>();
        for(String num : phone_book) {

            for (int i = 1; i < num.length(); i++ ) {
                if(set.contains(num.substring(0, i))){
                    return false;
                }
            }
            set.add(num);
        }
        return true;
    }

    public static void main(String[] args) {
        String[] str1 = {"119", "97674223", "1195524421"};
        System.out.println(solution(str1));
    }
}

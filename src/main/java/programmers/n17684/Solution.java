package programmers.n17684;

import java.util.*;

public class Solution {
    public static int[] solution(String msg) {
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap();

        int index = 1;
        for (char c = 'A'; c <= 'Z'; c++) {
            map.put(c+"", index);
            index++;
        }
        int len = msg.length();
//        msg += " ";
        for (int i = 0; i < len;) {
            String prev = msg.charAt(i) + "";
            int j = i + 1;
            String current = msg.substring(i, j);
            while (j <= len && map.containsKey(current)) {
                prev = current;
                j++;
                if (j > len) break; // 끝에 도달했으면 substring 안 하고 빠져나감
                current = msg.substring(i, j);
            }
            result.add(map.get(prev));
            map.put(current, index++);
            i += prev.length();
        }


        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
//        Arrays.stream(solution("KAKAO")).forEach(i -> {
//            System.out.print(i + " ");
//        });
//        System.out.println();
        Arrays.stream(solution("TOBEORNOTTOBEORTOBEORNOT")).forEach(i -> {
            System.out.print(i + " ");
        });
        System.out.println();
//        Arrays.stream(solution("ABABABABABABABAB")).forEach(i -> {
//            System.out.print(i + " ");
//        });
//        System.out.println();
    }
}

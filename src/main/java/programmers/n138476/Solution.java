package programmers.n138476;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    /*
    k개의 귤을 한박스에 담는다.
    !!!!!주어지는 귤의 수는 k개와 다를 수 있다.
    k개의 귤의 크기를 최소 종류로 하여 담는 최솟값을 구하여라
    // 정렬해서 개수 세고 가장 작은거 빼면 되는거 아님?
    // 근데 텐저린의 최대 무게가 1천만에서 1까지
    // 정렬해서 빼내고 다시 그리디 돌리면 될것같은데?
    귤의 수가 천만개이고 최악의 경우 그리디 시에 O(천만^2);인데?
    이것도 투포인터 가능할듯?
    5 4 3 2 1
    2 1 2 2 1
     */
    static int solution(int k, int[] tangerine) {
        Map<Integer, Integer> tgr = new HashMap<>();

        for (int t : tangerine) {
            tgr.merge(t, 1, Integer::sum); // 더 깔끔하게!
        }

        List<Map.Entry<Integer, Integer>> sizeList = tgr.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .collect(Collectors.toList());
//                .toList();

        int result = 0;
        for (Map.Entry<Integer, Integer> entry : sizeList) {
            if (k <= 0) break;
            k -= entry.getValue();
            result++;
        }

        return result;
    }
    public static void main(String[] args) {
        System.out.println(solution(6, new int[]{1, 3, 2, 5, 4, 5, 2, 3}));
        System.out.println(solution(4, new int[]{1, 3, 2, 5, 4, 5, 2, 3}));
        System.out.println(solution(2, new int[]{1, 1, 1, 1, 2, 2, 2, 3}));
    }
}

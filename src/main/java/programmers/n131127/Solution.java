package programmers.n131127;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /*
    돈지불하여 10일동안 회원권
    회원 대상 매일 한가지 할인
    할인품목은 하루에 하나 구입가능
    원하는 제품이 10일 연속으로 할인하는날 회원가입할것임
    want: 구매희망
    number: 구매희망 개수
    discount: 할인품목 순서 나타냄
     */
    public static int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> cart = new HashMap<>();
        int result = 0;
        int len = want.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += number[i];
        }


        for (int i = 0; i <= discount.length - sum; i++) {
            // 초기화
            for (int j = 0; j < len; j++) {
                cart.put(want[j], number[j]);
            }

            // 체크
            for (int k = i; k < i + sum; k++) {
                if (!cart.containsKey(discount[k])) break;
                cart.merge(discount[k], -1, Integer::sum);
                int num = cart.get(discount[k]);
                if (num == 0 || num == -1) cart.remove(discount[k]);
            }
            if(cart.isEmpty()) result++;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(
                new String[]{"banana", "apple", "rice", "pork", "pot"},
                new int[]{3, 2, 2, 2, 1},
                new String[]{"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"}
        ));
        System.out.println(solution(
                new String[]{"apple"},
                new int[]{10},
                new String[]{"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"}
        ));
    }
}

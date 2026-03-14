package programmers.n77486;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    /*
    다단계 조직도
    판매원이 판매하면 이익이 트리를 타고 분배된다.
    민호는 center로써 누가 얼마나 이익을 가져갔는지 알아본다.

    판매원은 판매액의 10%를 추천인에게 배분하고 나머지는 본인이 가진다.
    또한 피추천자의 판매액 배분 또한 본인의 이익으로 산정되어 10%를 추천인에게 분배하고, 나머지를 본인이 가진다.

    원단위 절사


     */
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = enroll.length;
        Map<String, String> tree = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String parent = referral[i];
            if (parent.equals("-")) parent = "center";
            tree.put(enroll[i], parent);
        }


        int m = seller.length;
        Map<String, Integer> incomes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            incomes.put(enroll[i], 0);
        }
        for (int i = 0; i < m; i++) { // 10만 이하
            String current = seller[i];
            String parent = tree.get(seller[i]);
            int remain = 100 * amount[i];
            while(!current.equals("center") && remain != 0) { //최대 10000의 깊이
                int nextIncome = remain / 10;
                int currentIncome = remain - nextIncome;
                incomes.merge(current, currentIncome, Integer::sum);
                remain = nextIncome;

                current = parent;
                parent = tree.get(current);
            }
        }
        int[] answer = new int[n]; // 셀러당 판매량
        for (int i = 0; i < n; i++) {
            answer[i] = incomes.get(enroll[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        Arrays.stream(solution(
                new String[] {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[] {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[] {"young", "john", "tod", "emily", "mary"},
                new int[] {12, 4, 2, 5, 10}
        )).forEach(i -> System.out.print(i + " "));
        System.out.println();

        Arrays.stream(solution(
                new String[] {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[] {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[] {"sam", "emily", "jaimie", "edward"},
                new int[] {2, 3, 5, 4}
        )).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
}

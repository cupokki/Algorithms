package programmers.n42885;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    // 보트를 타고 무인도 탈출
    // 최대 2명씩, 무게제한 있음
    // 보트 이용 최소의 수를 구하여라
    // 완전탐색으론 시간 초과 예상 불가
    // 백트래킹역시 한 레이어에서
    static int solution(int[] people, int limit) {
        List<Integer> sorted = Arrays.stream(people)
                .boxed()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());

        int result = 0;
        while(!sorted.isEmpty()) {
            int remain = limit;
            List<Integer> chosen = new ArrayList<>();
            for (int w : sorted) {
                if (chosen.size() == 2) {
                    break;
                }
                if (remain >= w) {
                    remain -=w;
                    chosen.add(w);
                }
            }
            for(Integer w : chosen) {
                sorted.remove(w);
            }
            result++;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{70, 50, 80, 50}, 100));
        System.out.println(solution(new int[]{70, 80, 50}, 100));
    }

}

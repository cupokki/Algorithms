package programmers.n42486;

import java.util.*;

public class Solution {
    // 순서대로 앞에것이 배포되어야 배포가능
    public static int[] solution(int[] progress, int[] speeds) {
        List<Integer> result = new ArrayList<>();
        int len = progress.length;
        int idx = 0;
        while (idx != len) {
            for (int i = idx; i < len; i++) {
                progress[i] += speeds[i];
            }
            int cnt = 0;
            for (int i = idx; i < len; i++) {
                if (progress[i] >= 100 ) {
                    cnt++;
                }else {
                    break;
                }

            }
            if (cnt != 0) {
                result.add(cnt);
                idx += cnt;
            }
        }

        return result.stream().mapToInt(i->i).toArray();
    }

    public static void main(String[] args) {
//        solution(
//                new int[]{93, 30, 55},
//                new int[]{1, 30, 5}
//                );

        solution(
                new int[]{95, 90, 99, 99, 80, 99},
                new int[]{1, 1, 1, 1, 1, 1}
        );
    }
}


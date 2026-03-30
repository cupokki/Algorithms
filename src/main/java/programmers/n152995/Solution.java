package programmers.n152995;

import java.util.*;

public class Solution {
    /*
    근태와 동료평가 테이블
    a사원이 b사원 보다 두 지표가 모두 낮은 경우 a 사원은 인센x
    그렇지아니한 사원들을 모아 점수 단순합계 석차를 매겨 인센 차등지급
    동석차가 존재하며, 동석차만큼 다음 석차는 건너 뜀

    테이블의 0번은 완호이며, 완호의 석차를 출력한다.
    인센을 못받는다면 -1을 출력한다.

    인원의 수는 최대 10만명
    */


    public static int solution(int[][] scores) {
        int answer = -1;

        int n = scores.length;

        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            boolean check = true;

            for (int j = i + 1; j < n; j++) {
                if (scores[i][0] < scores[j][0] && scores[i][1] < scores[j][1]) {
                    check = false;
                    break;
                }
            }
            if (check) list.add(new int[]{i, scores[i][0] + scores[i][1]});
        }

        list.sort(Comparator.comparing((int[] a) -> a[1]).reversed());

        for (int i = 0; i < list.size(); i++) {
            answer++;
            if (list.get(0)[0] == 0) {
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{2, 2}, {1, 4}, {3, 2}, {3, 2}, {2, 1}}));
    }


}

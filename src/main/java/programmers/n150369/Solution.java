package programmers.n150369;

import java.util.Arrays;

public class Solution {
    /*
    일렬로 나열된 n개의 집. 택배는 모두 같은 크기의 상자에 담는다.
    배달 끝나면 빈 택배상자 수거함
    cap: 트럭이 상자를 실을 수 있는 수.
    트럭은 택배를 실어 창고에서 각집으로 배달 및 빈상자 수거.
    각 집마다 배달할 상자와 수거할 상자 수를 알때,
    트럭 하나로 모든 배달과 수거를 마치고 물류창고로 돌아오는 최소 이동거리를 구한다.
    각 집에 배달 및 수거할때 원하는 개수만큼 할 수 있다.

    탐욕법?
    출발할때 가장 많이 담고, 멀리 있는 것 부터 배달하고,
    돌아오는 길에 담을 수 있는대로 담는다면?

    배달과 수거시에 0/0이 된다면 limit 검사

    출발할때 최대한 많이 들고가면 예외가 있다.

    딱 남은 배달량을 0으로 만들 수 있는 양만 들고간다?
     */
    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = -1;
        int len = n - 1;
        int limit = len;

        while(limit >= 0) {
            answer += 2 * limit;
            int idx = limit - 1;

            int truck = 0;

            for (int i = limit - 1; i >= 0; i--) {
                // 배달
                if (truck + deliveries[i] <= cap) {
                    truck += deliveries[i];
                    deliveries[i] = 0;
                }

                // 수거

                break;
            }

            // 위치가 limit 위치의 이고 배달 및 수거가 완료된 주소 발생 then limit--
            while (limit >= 0 && deliveries[limit] == 0 && pickups[limit] == 0)
               limit--;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(
                4,
                5,
                new int[]{1, 0, 3, 1, 2},
                new int[]{0, 3, 0, 4, 0}
        ));

        System.out.println(solution(
                2,
                7,
                new int[]{1, 0, 2, 0, 1, 0, 2},
                new int[]{0, 2, 0, 1, 0, 2, 0}
        ));
    }
}

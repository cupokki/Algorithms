package programmers.n42583;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /*
    트럭이 다리를 지나는데 다리 길이 시간만큼 소요된다.
    다리의 하중이 주어지고 진입할 트럭의 무게가 순서대로 주어질 때
    트럭이 이동하는 최소시간을 구하라
     */
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> bridge = new LinkedList<>();

        int tCnt = truck_weights.length;
        int tNum = 0;
        int passedCnt = 0;
        int curWeight = 0;
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(-1);
        }
        while (passedCnt < tCnt) {
            // 탈출
            int temp = bridge.poll();
            if (temp != -1) {
                curWeight -= temp;
                passedCnt++;
            }
            // 진입
            if (tNum < tCnt && curWeight + truck_weights[tNum] <= weight) {
                bridge.offer(truck_weights[tNum]);
                curWeight += truck_weights[tNum];
                tNum++;
            } else {
                bridge.offer(-1);
            }
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
//        System.out.println(solution(2, 10, new int[]{7, 4, 5, 6}));
//        System.out.println(solution(100, 100, new int[]{10}));
        System.out.println(solution(100, 100, new int[]{10,10,10,10,10,10,10,10,10,10}));
    }
}

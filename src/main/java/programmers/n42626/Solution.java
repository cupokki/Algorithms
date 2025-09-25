package programmers.n42626;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Solution {
    // k는 희망 스코빌지수
    // 섞은 음식의 스코빌 지수 =
    // 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
    public static int solution(int[] scoville, int K) {

        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Arrays.stream(scoville).boxed().collect(Collectors.toList()));
        while (pq.peek() < K) { // 최솟값이 k보다 크다면 모든 요소가 k보다 큼
            if (pq.size() < 2) { // null 익셉션 주의
                return -1;
            }
            int a = pq.poll();
            int b = pq.poll();
            if (a == 0 && b == 0) {
                return -1;
            }
            int c = a + b * 2;
            pq.offer(c);
            answer += 1;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 2, 3, 9, 10, 12}, 7));
    }
}

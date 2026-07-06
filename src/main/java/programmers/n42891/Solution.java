package programmers.n42891;

import java.util.*;

public class Solution {
    /*
    회전판 위 N개의 음식
    각 음식 섭취시 일정 시간 소요
    1번 부터 먹기 시작
    음식 번호 오름차순으로 음식을 자신앞으로 가져온다. (회전판 돌리는 속도는 무시)
    마지막 법호의 음식을 섭취한 후 회전판에 의해 다시 1번 음식이 무지앞으로
    1초 동안 섭취후 다음음식(남아있는 음식중 가장 가까운)
    먹방 시작 K초 후 어떤 음식을 먹어야하는지 구하라.
    없다면 -1을 반환
    
    k는 2*10^13이하의 자연수 => 이분탐색?
    */
    public int solution(int[] food_times, long k) {

        long total = Arrays.stream(food_times).sum();
        if (total <= k)  return -1;

        // sort by times;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[0]));

        int n = food_times.length;
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{food_times[i], i + 1});
        }

        long t = 0; // 소모한 시간
        long cnt = n;

        // 뺄 수 있을 때 까지 k에서 가장 작은 시간 빼기
        while (!pq.isEmpty()) {
            long cost = (pq.peek()[0] - t) * cnt;
            if (k >= cost) {
                k -= cost;
                t = pq.poll()[0];
                cnt--;
            } else {
                break;
            }
        }

        List<int[]> result = new ArrayList<>();
        while (!pq.isEmpty()) result.add(pq.poll());
        result.sort(Comparator.comparingInt(a -> a[1]));

        return result.get((int)(k % cnt))[1];
    }

     public static void main(String[] args) {
         Solution sol = new Solution();
         System.out.println(sol.solution(new int[]{3, 1, 2}, 5));  //1
     }
}
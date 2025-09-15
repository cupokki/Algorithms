package programmers.n42587;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

public class Solution {
    public static int solution(int[] priorities, int location) {
        //실행 대기 큐에 대기중인것 가져온다.
        // 큐에 우선순위가 더 높은게 있다면  방금 꺼낸걸 다시 큐에 넣는다.
        // 만약 그런 프로세스가 없다면 그냥 실행
        // 실행하면 작업은 삭제됨

//        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        // 우선순위큐로하면 같은 우선순위일때 순서가 룰에 위배된다.
        // 시뮬레이션으로가자

        Queue<int[]> queue = new LinkedList<>();

        int step = 0;

        int[] sorted = IntStream.range(0, priorities.length).map(i->priorities[i]).sorted().toArray();
        for (int i = 0; i < priorities.length; i++) {
            queue.offer(new int[]{i, priorities[i]});
        }

        int maxIdx = priorities.length - 1;
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            if (current[1] < sorted[maxIdx]) {
                queue.offer(current);
                continue;
            }
            step++;
            maxIdx--;
            if (current[0] == location) {
                break;
            }
        }

        return step;
    }

    public static void main(String[] args) {
//        System.out.println(solution(new int[]{2, 1, 3, 2}, 2));
        System.out.println(solution(new int[]{1, 1, 9, 1, 1, 1}, 0));
    }
}

package programmers.n118667;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
    /*
    한번에 프로세스에서 한큐에서 pop하고 다른 큐에 삽입한다.
    프로세스 수행으로 각 큐의 원소 합이 같아지는 최소의 수
    불가능하다면 -1을 반환한다.

    두 큐를 연결한다면 슬라이딩 윈도우 적용이 가능하다?

    -> ->
     */
    public static int solution(int[] queue1, int[] queue2) {
        int aLen = queue1.length;
        int bLen = queue2.length;
        int len = aLen + bLen;
        long aSum = Arrays.stream(queue1).asLongStream().sum();
        long bSum = Arrays.stream(queue2).asLongStream().sum();
        long totalSum = aSum + bSum;

        if (totalSum % 2 != 0)
            return -1;

        int[] arr = new int[len];
        System.arraycopy(queue1, 0, arr, 0, aLen);
        System.arraycopy(queue2, 0, arr, aLen, bLen);

        long temp = aSum;
        int s = 0;
        int e = aLen - 1;
        int cnt = 0;
        while (cnt <= len * 2) {
            if (temp * 2 == totalSum) {
                return cnt;
            }
            if (temp * 2 < totalSum) {
                e = (e + 1) % len;
                temp += arr[e];
            }
            else{
                temp -= arr[s];
                s = (s + 1) % len;
            }
            cnt++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(solution(
                new int[]{3, 2, 7, 2},
                new int[]{4, 6, 5, 1}
        ));

        System.out.println(solution(
                new int[]{1, 2, 1, 2},
                new int[]{1, 10, 1, 2}
        ));

        System.out.println(solution(
                new int[]{1, 1},
                new int[]{1, 5}
        ));
    }

}

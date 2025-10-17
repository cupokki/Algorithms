package programmers.n118667;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
    /*
    한번에 프로세스에서 한큐에서 pop하고 다른 큐에 삽입한다.
    프로세스 수행으로 각 큐의 원소 합이 같아지는 최소의 수
    불가능하다면 -1을 반환한다.

    두 큐를 연결한다면 슬라이딩 윈도우 적용이 가능하다?
     */
    public static int solution(int[] queue1, int[] queue2) {
        int aLen = queue1.length;
        int bLen = queue2.length;
        int len = aLen + bLen;
        int aSum = IntStream.range(0, queue1.length).map(i->queue1[i]).sum();
        int bSum = IntStream.range(0, queue2.length).map(i->queue2[i]).sum();



        int totalSum = aSum + bSum;
        int[] arr = new int[aLen + bLen];

        for (int i = 0; i < aLen; i++) {
            arr[i] = queue1[i];
        }
        for (int i = 0; i < bLen; i++) {
            arr[i + aLen] = queue2[i];
        }

        int temp = aSum;
        for(int i = 0; i < len; i++) {
            if (totalSum == temp * 2) {
                return i;
            }
            temp -= arr[i]; // 길이가 가변적이라 이런식으론 안된다.
            temp += arr[(i + aLen) % len];
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

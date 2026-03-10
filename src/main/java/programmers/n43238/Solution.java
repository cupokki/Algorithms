package programmers.n43238;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    /*
    n명의 입국심사 대기줄
    심사관마다 소모시간 다름, 심사대당 동시에 한명만 가능
    모두 빈 심사대로 시작하여, 줄의 앞사람은 빈 심사대로.
    하지만 더 빨리 끝나는 심사대가 있다면, 기다렸다가 그곳으로 가서 심사 받을 수 있다.

    모두 심사받는 최소의 시간을 구하라.

    심사관은 10만명 이하의 자연수
    소요시간은 10억분 이하
    n은 10억 아하의 자연수

    10만 케이스를 비교한다?;

    먼저 끝나는 시간을 비교하는게 아니라 끝나는 시간을 비교한다.


     */
    public static long solution(int n, int[] times) {
        long answer = 0;

        long l = 1, r = 1_000_000_000L * 1_000_000_000L;
        while (l <= r) {
            long m = (l + r) / 2;
            if (check(times, m, n)) {
                answer = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return answer;
    }
    static boolean check(int[] times, long t, long n) {
        long sum = 0;
        for (int i = 0; i < times.length; i++) {
            sum += t / times[i];
            if (sum >= n) return true;
        }
        return sum >= n;
    }

    public static void main(String[] args) {
        System.out.println(solution(6, new int[]{7, 10})); // 28
    }
}

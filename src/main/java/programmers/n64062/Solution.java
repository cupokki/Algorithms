package programmers.n64062;

import java.util.Arrays;

public class Solution {
    /*
    징검다리를 건넌다
    최대 몇명 건널 수 있는가.
    각 돌은 내구도가 있다. stones
    다음 돌로 이동할때, 가장 가까운 돌로 이동하며 k이하의 거리만 이동 가능하다.

    내구도는 2억 이하의 자연수.

    단순 구현으로 생각하면

    각 요소를 최소 값만큼 제한다. -> 최솟값을 찾아야한다... 시간복잡도 상승

     */
    static int N, K;
    static int[] Stones;
    public static int solution(int[] stones, int k) {
        int answer = 0;
        int max = Arrays.stream(stones).max().getAsInt();
        N = stones.length;
        K = k;
        Stones = stones;
        answer = binarySearch(0, max);
        return answer;
    }

    static int binarySearch(int l, int r) {
        if (r < l) return r;

        int m = (r + l) / 2;

        if (check(m)) {
            return binarySearch(m + 1, r);
        } else {
            return binarySearch(l, m - 1);
        }
    }

    static boolean check(int m) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (Stones[i] < m) {
                cnt++;
            } else {
                cnt = 0;
            }

            if (cnt == K) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
    }
}

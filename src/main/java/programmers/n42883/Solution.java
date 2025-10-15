package programmers.n42883;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class Solution {
    /*
    주어진 숫자에서 k개의 문자를 제거하여 만들 수 있는 최대 수
    k는 number의 자릿수 미만 (최대 100만 자리 이하)
    1. 분할정복가능한가.
    부분문자열로 바꾼다고 해보자. 각 부분에서 수를 제거한다하더라도 다시 문자열을 합쳤을때 최댓값을 보장 할 수 없다.
     */
    static int K;
    static int max;
    public static String solution(String number, int k) {
        K = k;
        max = -1;
        bfs(Integer.parseInt(number));


        return max + "";
    }

    static void bfs(int n) {
        Queue<int[]> q = new LinkedList<>(); // 이전값을 전달한다. 지운 수를 파싱한걸 전달하니 없어지지도 않을것임
        q.offer(new int[]{n, 0});
        while (!q.isEmpty()) {
            int[] state = q.poll();
            String str = state[0]+"";

            if (state[0] < max) {
                continue;
            }
            if (state[1] == K) {
                max = state[1];
                continue;
            }
            int i = 0;
            while(state[0] / Math.pow(10, i) != 0) {
                int a = (int)(state[0] / Math.pow(10, i + 1));
                int b = (int)(state[0] / Math.pow(10, i + 1));
                int next = a + b;
                q.offer(new int[]{next, state[1] + 1});
                i++;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solution("1924", 2));
        System.out.println(solution("1231234", 3));
        System.out.println(solution("4177242841", 4));
    }
}

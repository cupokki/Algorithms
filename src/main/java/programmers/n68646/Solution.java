package programmers.n68646;

import java.util.*;

public class Solution {
    /*
    서로 다른 수가 적힌 일렬의 n개 풍선
    1개의 풍선만 남길때 까지 아래를 반복한다.
    1. 임의의 인접한 두 풍선을 골라, 둘 중 하나를 터트린다.
    2. 터진 풍선으로 생긴 빈공간이 없도록 풍선을 중앙으로 밀착
    
    **인접한 두 풍선중 번호가 더 작은 풍선을 터트리는 행위는 최대 1번만 가능
    
    최후에 남을 수 있는 풍선들의 수를 출력한다.
    배열의 길이는 백만이하의 자연수
    풍선의 번호는 서로 다른 -10억에서 10억사이의 정수

    i번쨰 풍선이 남을 수 있는지 하나씩 확인한다? => 여러 경우의 수중에 한번만 남으면 된다.
    정렬후에 i번쨰 풍선을 빼고, 가장 작은 수의 풍선과 i번쨰 풍선을 비교하면 되겠는데?

    이러면 불가능한 경우가 없지않나
    아 인접한 두 풍선을 골라야하니깐

    i번째 풍선을 고를때, i로 인해 나누어지는 부분 수열[0, i - 1], [i + 1, n]을 확인한다.
    각각을 left, right라고 할때, 각각의 구간에서 큰것들을 지워가며 얻어지는 최솟값이 발생한다.
    각각의 min(left), min(right), a[i]를 비교해서 a[i]가 가장크다면, a[i]는 불가능

    n은 100만이므로, 구간별 최솟값을 미리 구해야한다.

    */
    public static int solution(int[] a) {
        int answer = 0;

        int n = a.length;

        // 길이가 2이하로는 반드시 가능
        if (n <= 2) {
            return n;
        }

        int[] leftMins = new int[n - 1]; // 0부터 n방향으로 길이가 i인 부분수열의 최솟값
        int[] rightMins = new int[n - 1]; // n부터 0방향으로 길이가 i인 부분수열 최솟값

        leftMins[0] = a[0];
        rightMins[0] = a[n - 1];

        for (int i = 1; i < n - 1; i++) {
            leftMins[i] = Math.min(a[i], leftMins[i - 1]);
            rightMins[i] = Math.min(a[n - 1 - i], rightMins[i - 1]);
        }

        answer = 2; // 양끝의 풍선은 반드시 가능하다.

        for (int i = 1; i < n - 1; i++) {
            if (a[i] > leftMins[i] && a[i] > rightMins[n - 1 - i]) continue;
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[] {9, -1, -5}));
        System.out.println(solution(new int[] {-16, 27, 65, -2, 58, -92, -71, -68, -61, -33}));
    }
}
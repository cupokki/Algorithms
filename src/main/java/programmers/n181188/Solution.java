package programmers.n181188;

import java.util.*;

public class Solution {
    /*
    요격시스템
    미사일을 최소로 사용해 모든 미사일을 요격하려한다.
    A, B 두나라는 2차원 평면.
    A가 발사한 미사일을 x축에 평행한 직선모양이고 개구간 정수상(s,e)= s,e의 위치에서는 요격불가
    B나라는 y축에 평행하게 수직발사하여, x축에 겹치는 모든 미사일을 요격한다.
    요격미사일은 실수좌표에서도 발사가능,
    폭격미사일 좌표범위가 주어질때, 모든 폭격미사일을 요격하기 위해 필요한 최소 요격미사일 수
    요격미사일은 50만개 이하 자연수
    x공간의 범위는 1억이하
     */
    public static int solution(int[][] targets) {
        int answer = 0;

        Arrays.sort(targets, Comparator
                .comparingInt((int[] a) -> a[1]) // end를 지나면 해당 미사일은 요격 불가하다. end 기준 정렬
                .thenComparingInt(a -> a[0])
        );

        int end = 0;
        for (int[] t : targets) {
            if (t[0] >= end) {
                answer++;
                end = t[1];
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][] {
                {4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}
        }));
    }
}

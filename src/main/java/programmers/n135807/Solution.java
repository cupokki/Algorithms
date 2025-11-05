package programmers.n135807;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /*
    두명이 숫자카드를 나눠 받는다. 이후 두 조건중 하나를 만족하는 가장 큰 양의정수 a를 구한다.
    1. A가 가진 숫자를 모두 나눌 수 있고 B가 가진 모든 수와 누눌수 없는 a
    2. 혹은 1의 반대

    두 카드 집합의 길이 각 500_000 이하 자연수
    카드에 적힌 수는 1억 이하의 자연수, 두집합간 중복인 카드 존재\
     */
    public static int solution(int[] arrayA, int[] arrayB) {
        // A의 공통된 약수 중 가장 크며, arrayB의 최대 수보다 작으며,
        // i(1 <= 두 배열의 최댓값) 일때 A[i]% a == 0이고 B[i]% a!= 0임 또는 그 역
        // i의 최댓값은 1억이므로 완전탐색은 어렵다?
        // 최댓값 m, 길이가 l일때 O(m*l); 의 시간복잡도

        // A의 GCD의 약수를 순회(A의 공통된 약수)하며, B의 모든 집합을 나눌수 없는 수를 찾는다.

        int len = arrayA.length;

        int[] gcdA = gcdArray(arrayA);
        int[] gcdB = gcdArray(arrayB);

        int answer = 0;
        boolean done = true;
        for (int i = gcdA.length - 1; i >= 0; i--) {
            for (int j = 0; j < len; j++) {
                if (gcdA[i] < answer) {
                    done = false;
                    break;
                }
                if (arrayB[j] % gcdA[i] == 0) { // 나눠지면,
                    done = false; // 이 뒤의 약수는 볼 필요 엾음
                    break;
                }
            }
            if (!done) {
                break;
            }
            answer = Math.max(answer, gcdA[i]);
        }
        done = true;
        for (int i = gcdB.length - 1; i >= 0; i--) {
            for (int j = 0; j < len; j++) {
                if (gcdB[i] < answer) {
                    done = false;
                    break;
                }
                if (arrayB[j] % gcdB[i] == 0) {
                    done = false;
                    break;
                }
            }
            if (!done) {
                break;
            }
            answer = Math.max(answer, gcdB[i]);
        }

        return answer;
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    static int[] gcdArray(int[] arr) {
        int n = arr[0];
        for (int i = 1; i < arr.length; i++) {
            n = gcd(n, arr[i]);
        }
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                divisors.add(i);
                if (i != n / i) {
                    divisors.add(n / i);
                }
            }
        }

        return divisors.stream()
                .sorted()
                .mapToInt(Integer::intValue)
                .toArray();
    }


    public static void main(String[] args) {
        System.out.println(solution(new int[]{10, 17}, new int[]{5, 20}));
        System.out.println(solution(new int[]{10, 20}, new int[]{5, 17}));
        System.out.println(solution(new int[]{14,35, 119}, new int[]{18, 30, 102}));
    }

}

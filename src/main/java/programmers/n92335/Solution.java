package programmers.n92335;

public class Solution {
    /*
    n: 양의 정수 n
    k: k진수로 변환
    변환된 수 안에 아래 조건에 맞는 Prime number가 몇개인지 알아본다.
    0P0: 소수양쪽에 0
    P0: 소수 오른쪽에 0, 왼쪽엔 아무것도 없음
    0P: 소수 왼쪽 0, 오른쪽 아무것도 없음
    P: 소수 그자체 (각 자릿수에 0을 포함하지않음) ex 101은 P가 될 수 없음

     */
    public static int solution(int n, int k) {
        int answer = 0;
        String bin = Integer.toString(n, k);
        String[] numbers = bin.split("[0]+");
        for (int i = 0; i < numbers.length; i++) {
//            int num = Integer.parseInt(numbers[i], k); // 문제에 나옴. K진법으로 하지말라고
            int num = Integer.parseInt(numbers[i]);
            if (isPrime(num)) {
                answer++;
            }
        }
        return answer;
    }

    public static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        if (n == 2)
            return true;
        if (n % 2 == 0)
            return false;
//        for (int i = 3; i <= Math.sqrt(n); i += 2) {
        for (int i = 3; i < n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution(437674, 3));
        System.out.println(solution(110011, 10));
    }
}

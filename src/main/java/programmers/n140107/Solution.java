package programmers.n140107;

public class Solution {
    /*
    좌표평면에 점을 찍는다.
    두 양의 정수 k,d
    원점으로 부터 x축 방향으로 a*k(a = 0, 1, 2, ...)
    원점으로 부터 y축 방향으로 b*k(b = 0, 1, 2, ...)
    원점과 거리가 d를 넘는 위치에는 점을 찍지 않는다.

    => 반지름이 0인 반원꼴 영역안에 점이 분포한다.

    실수 비교에서.. 좀 그렇네;
    찍히는 점의 개수를 출력하라
     */
//    public static long solution(int k, int d) {
//        long answer = 0;
//        for (int x = 0; x <= d; x += k) {
//            long y = (long)Math.sqrt((long)d * d - (long)x * x);
//            answer +=  y / k + 1;
//        }
//        return answer;
//    }
    public static long solution(int k, int d) {
        long answer = 0;
        long y = d/k * k;
        for (int x = 0; x <= d; x += k) {
            while ((long)x * x + y * y > (long)d * d) {
                y -= k;
            }
            answer +=  y / k + 1;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(2, 4));
        System.out.println(solution(1, 5));
    }
}

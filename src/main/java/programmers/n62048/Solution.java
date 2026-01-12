package programmers.n62048;

import java.util.*;

public class Solution {
    /*
    W x H 직사각형 격자종이. 대각선으로 잘려있다.
    대각선부위를 제거할때, 사용할 수 있는 단위 정사각형의 개수(1by1)

    직접 방정식으로 풀면 시간은 문제가 되지않는다.
    다만 기울기가 무한소수라면 부동소수점 오차때문에 결과가 오염된다.
    => 실수 기울기를 직접 쓰면 안된다.

    오차가 적용되지 않는 범위만큼 쪼개어 계산한다.
    반드시 대각선으로 나누어 지는 영역은 대칭이다.
    f(n,m) = 2 * f(n/2,m/2)와 같은가?
    맞다. 하지만 n % 2 == 0 %% m % 2 == 0이어야 한다.
    모두 홀수 일 경우
    f(n, m) = 2 * f(n/2 + 1, m/2 + 1) - 1; // -1 두 변이 홀수라면 한 점이 겹친다.
    이를 응용하면 될 듯 하다.

    아니다 f(3, 4)는 이런 식으로 자르면 다음 f()가 대칭이 안된다.
    */
//    public static long solution(int w, int h) {
//        long answer = w * h;
//        double slope = (double) h / w;
//
//        for (int x = 1; x <= w; x++) {
//            double a = Math.floor(slope * (x - 1));
//            double b = Math.ceil(slope * x);
//            answer -= b - a;
//        }
//        return answer;
//    }

    public static long solution(int w, int h) {
        if (w == h) {
            return w; // 정사각형이라면 대각선으로 점유되는 단위정사각형의 개수는 변의 길이와 같다.
        }

        return w * h - func(w, h);
    }
    static long func (int w, int h) {
        if (w == 1 || h == 1) {
            return w * h;
        }

        int nw = w % 2 == 0 ? w / 2 : w / 2 + 1;
        int nh = h % 2 == 0 ? h / 2 : h / 2 + 1;

        if (nw % 2 != 0 && nh % 2 != 0) { // 둘다 홀수
            return 2 * func(nw, nh) - 1;
        } else {
            return 2 * func(nw, nh);
        }
    }


    public static void main(String[] args) {
//        System.out.println(solution(3, 5));
//        System.out.println(solution(2, 3));
        System.out.println(solution(3, 4));
//        System.out.println(solution(2, 100000000));
        System.out.println(solution(8, 12));
    }
}
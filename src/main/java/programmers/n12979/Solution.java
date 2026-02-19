package programmers.n12979;

public class Solution {
    /*
    N 개의 아파트 일렬
    옥상의 기지국을 4g -> 5g 공사(전달 범위 좁아짐, 범위반경 w칸)
    기존 기지국이 주어지고, 모든 아파트에 전파를 전달하기 위해 추가해야할 최소 기지국 수를 구하라.
    n은 2억이하 자연수
    stations의 크기는 1만이하 자연수
    stations는 오름차순 정렬이며, N보다 같거나 작은 자연수 원소
    W 1만이하 자연수
     */
    public static int solution(int n, int[] stations, int w) {
        int answer = 0;

        int diameter = w * 2 + 1;

        int prevRight = 0;
        for (int s : stations) {
            int curLeft = s - w;
            int dist = curLeft - prevRight - 1;
            if (dist > 0) {
                answer += (dist + diameter - 1) / diameter;
            }
            prevRight = s + w;
        }

        if (prevRight < n) {
            int dist = n - prevRight;
            answer += (dist + diameter - 1) / diameter;
        }
        return answer;
    }

    public static void main(String[] args) {
//        System.out.println(solution(11, new int[]{4, 11}, 1));
        System.out.println(solution(16, new int[]{9}, 2));
    }
}

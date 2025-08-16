package programmers.n42885;

public class Solution {
    // 보트를 타고 무인도 탈출
    // 최대 2명씩, 무게제한 있음
    // 보트 이용 최소의 수를 구하여라
    static int[] p;
    static int l;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;
    static int solution(int[] people, int limit) {
        p = people;
        l = limit;
        visited = new boolean[people.length];
        backtracking(0, 0);
        return min;
    }
    static void backtracking(int rescued, int cnt) {

        if(rescued == p.length) {
            min = Math.min(min, cnt);
        }

        for (int i = 0; i < p.length; i++) {
            if (!visited[i]) {
               visited[i] = true;
               backtracking(0, 0);
               visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{70, 50, 80, 50}, 100));
        System.out.println(solution(new int[]{70, 80, 50}, 100));
    }

}

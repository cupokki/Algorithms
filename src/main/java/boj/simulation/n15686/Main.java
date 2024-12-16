package boj.simulation.n15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/15686
 * 치킨 배달
 * N*N 도시, 인덱스는 1부터
 * 각 칸은 빈칸/치킨집(2)/집(1)
 * 치킨 거리 : 집에서 치킨집까지 최단 경로
 * 도시 치킨거리 : 도시 치킨 거리의 합
 * 거리 = |r_1 - r_2| + |c_1 - c_2|
 * => 인접한것을 다루는 문제가 아님
 * M은 최대 선택가능한 치킨집의 개수.
 * 집의 개수는 2 * N을 넘지않고, 최소 하나는 존재한다.
 */
public class Main {
    static int N, M;
    static int[][] city;
    static List<int[]> houses = new ArrayList<>();
    static List<int[]> chickens = new ArrayList<>();
    static Set<int[]> chosen = new HashSet<>();
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // [2,50]
        M = Integer.parseInt(st.nextToken()); // [1,13]

        city = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if (city[i][j] == 1) // 집이면
                    houses.add(new int[]{i, j});
                if (city[i][j] == 2) // 치킨집이면
                    chickens.add(new int[]{i, j});
            }
        }

        solve(0, 0);
        System.out.println(min);
    }
    static void solve(int start, int size) {
        if (size == M) {
            int cityChickenDistance = 0;
            for(int[] h : houses){
                int minChickenDistance = Integer.MAX_VALUE;
                for(int[] c : chosen){
                    // 집들이 한 c만 고르게 하면 안된다.
                    minChickenDistance = Math.min(Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]), minChickenDistance);
                }
                cityChickenDistance += minChickenDistance;
            }
            min = Math.min(cityChickenDistance, min);
            return;
        }

        // 중복을 허용하지않고 고름, 사실 choosen은 set을 사용해서 중복이 있어도 됨
        // ㄴ 아님 각 분기의 크기를 줄여야함
        for (int i = start; i < chickens.size(); i++) { // 조합 백트래킹
            chosen.add(chickens.get(i));
            solve(i + 1, size + 1);
            chosen.remove(chickens.get(i));
        }
    }
}

//static void dfs(int depth, int cityChickenDistance) {
//    if (cityChickenDistance > min)
//        return;
//
//    if (depth == houses.size()) { // 문제가 M을 13으로 설정한걸로 봐서 depth는 M임
//        // 최소 치킨거리 계산
//        min = Math.min(min, cityChickenDistance);
//        return;
//    }
//
//    // 집선택
//    int[] h = houses.get(depth);
//    if (chosen.size() == M) {
//        for (int[] c : chosen) {
//            int chickenDistance = Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]);
//            dfs(depth + 1, cityChickenDistance + chickenDistance);
//        }
//        return;
//    }
//    for (int ch = 0; ch < chickens.size(); ch++) { // chichen.size()의 최댓값은 (n*2 - 2) 13은 M(선택된 치킨집의 최소)
//        // 치킨집을 m개 선택했다면 무조건 선택한 치킨집 안
//        int[] c = chickens.get(ch);
//        int chickenDistance = Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]);
//        chosen.add(c);
//        dfs(depth + 1, cityChickenDistance + chickenDistance);
//        chosen.remove(c);
//
//    }
//}
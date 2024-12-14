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
 * <p>
 * 최소 도시치킨거리를 구하라
 * <p>
 * bfs -> M개 이하로 치킨집이 남을 수 있다.
 * -> dfs로 풀어야할 듯?
 * <p>
 * 집의 개수는 2 * N을 넘지않고, 최소 하나는 존재한다.
 */
public class Main {
    static int N, M;
    static int[][] city;
    static List<int[]> houses = new ArrayList<>();
    static List<int[]> chickens = new ArrayList<>();
    static Set<int[]> choosen = new HashSet<>();
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

        dfs(0, 0, 0);
        System.out.println(min);
    }

    static void dfs(int depth, int m, int cityChickenDistance) {
        if (depth == houses.size()) {
            // 최소 치킨거리 계산
            min = Math.min(min, cityChickenDistance);
            return;
        }

        int[] h = houses.get(depth);

        if (choosen.size() == M) {
            for (int[] c : choosen) {
                int chickenDistance = Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]);
                // 집은 가게는 반드시 선택해야함
                dfs(depth + 1, m + 1, cityChickenDistance + chickenDistance);

            }
            return;
        }

        //(2 * N) ^ 13???
        for (int ch = 0; ch < chickens.size(); ch++) { //
            // 치킨집을 m개 선택했다면 무조건 선택한 치킨집 안
            int[] c = chickens.get(ch);
            int chickenDistance = Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]);
            // 집은 가게는 반드시 선택해야함
            choosen.add(c);
            dfs(depth + 1, m + 1, cityChickenDistance + chickenDistance);
            choosen.remove(c);

        }
    }
}
//    static void dfs(int depth, int m, int cityChickenDistance) {
//        if (depth == chickens.size()) {
//            // 최소 치킨거리 계산
//            if (m == M)
//                min = Math.min(min, cityChickenDistance);
//            return;
//        }
//
//        int[] c = chickens.get(depth);
//        for (int hi = 0; hi < houses.size(); hi++) {
//            int[] h = houses.get(hi);
//            int chickenDistance = Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]);
//
//            dfs(depth + 1, m + 1, cityChickenDistance + chickenDistance);
//
//            dfs(depth + 1, m, cityChickenDistance);
//
//        }
//    }
//}

// depth 마다 집을 고른다. -> 집은 반드시 치킨집을 고른다. <--------------------- O((2 * N) ^ 13)

// depth 마다 치킨집을 고른다. -> 모든 치킨집은 집을 고르지 않는다.<---------------------- O((13)^3) // 고르거나/고르지않거나/폐업하거나?
// chickens.size() 에서 M개를 선택하는 경우에서 모든 치킨집이 1회 이상 선택 되어야한다.
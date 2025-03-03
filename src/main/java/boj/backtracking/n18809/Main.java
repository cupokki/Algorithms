package boj.backtracking.n18809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Gaaaaaaaaaarden
 * https://www.acmicpc.net/problem/18809
 * NM 크기 정원에 땅, 호수가 있다.
 * 빨강 초록 배양액을 땅애 뿌려 꽃을 피운다.
 * 배양액은 흰 칸에만 뿌릴 수 있다.(뿌릴수없는 칸은 황토)
 * 배양액은 매 초 인접 땅(상하좌우)로 퍼진다. 전파는 색구분없다.
 * 두 배양액이 동시에 만나는 땅애 꽃이 피고 인접한 땅으로 번지지않는다.
 * 배양액은 모두 사용해야한다.또 같은 곳에 뿌릴 수 없다.
 *
 * 땅은 항상 [R+G,10]
 *
 * result : 피울수 있는 꽃의 최대
 */
public class Main {
    static int N; // [2,50];
    static int M; // [2,50];
    static int G; // [1,5];
    static int R; // [1,5]
    static int[][] garden; // -1:호수, 0땅, 1초, 2빨, 3꽃
    static int flowerCnt = 0;
    static List<int[]> lands = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        garden = new int[N][M];

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                switch (Integer.parseInt(st.nextToken())) {
                    case 0:
                        garden[i][j] = -1; // 호수
                        break;
                    case 1:
                        lands.add(new int[]{i, j}); // 배양액을 뿌릴 수 있는 땅
                }
                // 0: 호수, 1: 배양액 가능, 2: 배양액 불가
            }
        }
        arr = new int[lands.size()];
        // 뿌리기,
        solve(0, 0, 0);

        System.out.println(flowerCnt);

    }
    static int[][] red;
    static int[][] green;


    static int[] arr;
    // O(10^3)
    static void solve(int i, int r, int g) {
        if (i == lands.size()) {
            // r,g 번갈아가며 bfs?
            //deepcopy // ->
//            List.;

            spread(); // ->  2500
            //rebase -> 2500

            return;
        }

        int[] pos = lands.get(i);
        int row = pos[0];
        int col = pos[0];
        garden[row][col] = 1;
        solve(i + 1, r + 1, g); // 파란색을 뿌림
        garden[row][col] = 2;
        solve(i + 1, r , g + 1); // 빨간색 뿌림
        garden[row][col] = 0;
        solve(i + 1, r, g); // 선택하지않음

    }
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {-1, 1, 0, 0};
    // bfs
    // O() * O() // 깊은복사
    static void spread() {
        Queue<int[]> rq = new LinkedList<>();
        Queue<int[]> gq = new LinkedList<>();
        int[][] rvisited = new int[N][M];
        int[][] gvisited = new int[N][M];

        while(!rq.isEmpty() && !gq.isEmpty()) {
            int[] p = rq.poll();
            int rr = p[0];
            int rc = p[1];
            for (int d = 0; d < 4; d++) {
                int rnr = rr + dr[d];
                int rnc = rc + dc[d];

                if (rnr >= 0 && rnr < N && rnc >= 0 && rnc < M && rvisited[rnr][rnc] == 0) {
//                    rvisited[rnr][rnc] =
                }
            }
        }
    }
}

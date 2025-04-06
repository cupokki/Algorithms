package boj.graph.n17135;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 캐슬 디펜스
 * https://www.acmicpc.net/problem/17135
 * 다펜스게임..
 * NM크기 격자
 * 각 칸엔 최대 하나의 적
 * N + 1 행에 성
 * 성 위에 궁수 배치 한칸에 최대 한명
 * 궁수는 D이하 거리의 가까운 적을 공격(영역에 여러적이면 가장 왼쪽)
 * 적은 턴당 한칸씩 아래로
 * 적이 판에 없으면 게임 종료
 *
 * 첫째 줄에 궁수의 공격으로 제거할 수 있는 적의 최대 수를 출력한다.
 */
public class Main {
    static int[][] origin;
    static int max = 0;
    static int N, M, D;
    static int enemy = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        origin = new int[N + 1][M];
        archer = new boolean[M];



        for (int i = 0 ; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < M; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());

            }
        }

        backtracking(0, 0);

        System.out.println(max);
    }

    static boolean[] archer;
    static void backtracking(int start, int size) {
        if (size == 3) {
            List<int[]> list = new ArrayList<>();
            for (int i = 0 ; i < N; i++) {
                for (int j = 0 ; j < M; j++) {
                    if (origin[i][j] == 1)
                        list.add(new int[]{i, j, 1});
                }
            }

            max = Math.max(run(list), max);
        }

        for (int i = start; i < M; i++) {
            archer[i] = true;
            backtracking(i + 1, size + 1);
            archer[i] = false;
        }

    }
    static void target(int ar, int ac, List<int[]> list) {
        list.sort((a, b) -> {
            int distA = Math.abs(ar - a[0]) + Math.abs(ac - a[1]);
            int distB = Math.abs(ar - b[0]) + Math.abs(ac - b[1]);

            if (distA != distB) {
                return distA - distB;  // 거리 우선
            } else {
                return a[1] - b[1];     // 열 우선
            }
        });
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int[] state = list.get(i);
            if (Math.abs(ar - state[0]) + Math.abs(ac - state[1]) <= D) {
                state[2] = 0;
                return;
            }
        }
    }
    static int run(List<int[]> list) {
        int killCount = 0;
        while (!list.isEmpty()) {

            // 일제 타겟팅
            for (int a = 0; a < M; a++) {
                if (archer[a]) target(N, a, list);
            }

            // 킬 수
            killCount += (int) list.stream().filter(e -> e[2] == 0).count();

            // 일괄 삭제
            list.removeIf(state -> state[2] == 0);

            // 이동
            list.forEach(state -> state[0]++);

            // 성에 도달한 적 삭제
            list.removeIf(state -> state[0] == N);
        }
        return killCount;
    }
}

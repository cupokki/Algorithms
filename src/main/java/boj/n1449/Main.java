package boj.n1449;

import java.io.*;
import java.util.*;

/**
 * 파이프는 가장 왼쪽에서 정수만큼 떨어진 거리만 물이 샌다.
 * 길이가 L인 테이프 무한개 테이프로 물을 막는다.
 * 물을 막을때, 좌우 0.5만큼 간격을 줘야 물이 다시 안 샌다. -> 지점당 길이 1씩 필요, 테이프는 자를 수 없다.
 * 필요한 테이프의 최소 개수를 구하라.
 * dp? 백트래킹? bfs?
 */
public class Main {
    static int N;
    static int L;
    static int[] pos;
    static int[] taped;
    static int min = Integer.MAX_VALUE;
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 물이 세는 곳
        L = Integer.parseInt(st.nextToken()); // 테이프 길이

        pos = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(pos);
        int pipeSize = pos[N - 1];
        taped = new int[pipeSize + 1 + L + 1];
        Arrays.fill(taped, 1);

        for (int i : pos) {
            taped[i] = 0;
        }

        backtrack(0, 0, 0);

        System.out.println(min);

    }
    static void backtrack(int depth, int start, int cnt) {

        if (cnt >= min) {
            return;
        }
        //TODO : 이게 문제다
        if (depth == N) {
            if (check()){
                min = Math.min(min, cnt);
            }
            // 모두 보수했다면
            // min cnt compare

            return;
        }
        for (int i = start; i < N; i++) {
            if (taped[pos[i]] == 0) {
                tape(pos[i], L);
                backtrack(depth + 1, start + 1, cnt + 1);
                untape(pos[i], L);
                backtrack(depth + 1, start + 1, cnt);
            }
        }
    }
    static boolean check() {
        for (int p : pos) {
            if (taped[p] == 0)
                return false;
        }
        return true;
    }
    static void tape(int pos, int length) {
        for (int i = 0; i < length; i++) {
            taped[pos + i] += 1;
        }
    }
    static void untape(int pos, int length) {
        for (int i = 0; i < length; i++) {
            taped[pos + i] -= 1;
        }
    }
}
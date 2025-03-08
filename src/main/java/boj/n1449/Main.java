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
    static boolean[] taped;

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 물이 세는 곳
        L = Integer.parseInt(st.nextToken()); // 테이프 길이

        pos = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(pos);
        taped = new boolean[pos[N - 1] + L];
        for (int p : pos) {
            taped[p] = true;
        }
        int cnt = 0;
        for (int i = 1; i < taped.length; i++) {
            if (!taped[i])
                continue;
            cnt++;
            for (int j = 0; j < L; j++) {
                taped[i + j] = false;
            }
        }

        System.out.println(cnt);

    }
}
package boj.simulation.n14891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * https://www.acmicpc.net/problem/14891
 * 톱니바퀴
 * 8개의 톱니를 가진 톱니바퀴, 톱니는 N극 또는 S극
 * 톱니바퀴에는 번호, 왼쪽부터 1번, 4개
 * 톱니바퀴를 총 k번 톱니 한칸 단위의 회전(시계,반시계 모두)시키려고 한다.
 * 회전시킬 톱니와 방향을 결정해서 인접한 톱니바퀴의 톱니가 같은 극끼리 맞닿으면(3번째 톱니) 회전
 * 초기상태와, 회전시킬 톱니바퀴와 방향을 입력대로 수행하고 각 톱니바퀴 12시방향을 아래대로 채점
 * 톱니는 12시부터 시계방향으로 넘버링
 * 1번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 1점
 * 2번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 2점
 * 3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점
 * 4번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점
 */
public class Main {
    static final int GEAR_COUNT = 4;
    static final int TOOTH_COUNT = 8;
    static char[][] gears = new char[GEAR_COUNT][TOOTH_COUNT];
    static boolean[] visited = new boolean[GEAR_COUNT];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i = 0; i < GEAR_COUNT; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < TOOTH_COUNT; j++) {
                gears[i][j] = line[j]=='1' ? 'S' : 'N'; // 1은 S극
            }
        }
        int K = Integer.parseInt(br.readLine()); // rotate count K(1 ≤ K ≤ 100)
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken()) - 1; // gear number
            int d = Integer.parseInt(st.nextToken()); // direction
            rotate(g, d);
            Arrays.fill(visited, false);
        }
        System.out.println(calculateScore());
    }

    static void rotate(int g, int d) {
        visited[g] = true;
        if (g - 1 >= 0 && !visited[g - 1] && gears[g][6] != gears[g - 1][2])
            rotate(g - 1, d * -1);
        if (g + 1 < GEAR_COUNT && !visited[g + 1] && gears[g][2] != gears[g + 1][6])
            rotate(g + 1, d * -1);

        if (d == 1) {
            char temp = gears[g][TOOTH_COUNT - 1];
            for (int i = TOOTH_COUNT - 2; i >= 0; i--)
                gears[g][i + 1] = gears[g][i];
            gears[g][0] = temp;
        }else {
            char temp = gears[g][0];
            for (int i = 1; i < TOOTH_COUNT ; i++)
                gears[g][i - 1] = gears[g][i];
            gears[g][TOOTH_COUNT - 1] = temp;
        }



    }
    static int calculateScore(){
        return IntStream.range(0, GEAR_COUNT)
                .map(i -> gears[i][0] == 'S' ? (int) Math.pow(2,i) : 0)
                .sum();
    }
}

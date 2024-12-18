package boj.simulation.n13335;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/13335
 * 트럭
 * 다리와 n개의 트력의 무게 배열이 주어진다.
 * 다리의 길이는 w(동시에 올라갈 수 있는 트럭), 최대하중은 L
 * 단위시간에 단위길이 만큼 이동한다.
 * 모든 트럭이 다리를 건너는 최단시간을 구하라.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] trucks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Queue<Integer> bridge = new LinkedList<>();
        int time = 0; // 경과 시간
        int count = 0; // 다리 위 트럭 수
        int i = 0; // 트럭 인덱스
        while (i < n) {
            if(bridge.size() == w && bridge.poll() != 0)
                count--;
            if(count + 1 <= w && getSum(bridge) + trucks[i] <= L) {
                bridge.offer(trucks[i]);
                count++;
                i++;
            }else
                bridge.offer(0);
            time++;
        }
//        while (bridge.size() < w) bridge.offer(0);
//        while (!bridge.isEmpty()) {
//            bridge.poll();
//            time++;
//        }
//        time += w - bridge.size();
//        time += bridge.size();
        time += w; // 최단 시간만 고려한다면
        // 다르게 생각하면 길이가 2인 모든 부분수열의 합이 10 이하가 되도록 0을 끼워넣는 문제.
        System.out.println(time);
    }
    static int getSum(Queue<Integer> q) {
        return q.stream().mapToInt(i->i).sum();
    }
}

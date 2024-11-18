package boj.graph.n1926;

import java.io.*;
import java.util.*;

/**
 *https://www.acmicpc.net/problem/1926
 * 그림
 * 1로 연결된것은 그림이다. 그림의 넓이 중 가장 넓은 것을 출력, (대각은 불허한다.)
 */

public class Main {
    static int n;
    static int m;
//    static ArrayList<Integer>[] graph;
    static boolean[] canvas;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int bfs(int root){
        int area = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        canvas[root] = false;
        area++;

        while(!queue.isEmpty()){
            int u = queue.poll();
            for(int d = 0; d < 4; d++){
                int row = u / m + dx[d];
                int col = u % m + dy[d];

                if(row < 0 || col < 0 || row >= n || col >= m)
                    continue;
                int v = toIndex(row, col);
                if(canvas[v]){
                    canvas[v] = false;
                    area++;
                    queue.add(v);
                }
            }
        }

        return area;
    }
    static int toIndex(int row, int col) {
        return row * m + col;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 세로 가로
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        canvas = new boolean[n * m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                if(st.nextToken().charAt(0) == '1'){
                    canvas[i * m + j] = true;
                }
            }
        }

        int count = 0;
        int maxArea = 0;

        for(int i = 0; i < n * m; i++ ){
            if(canvas[i]){
                count++;
                maxArea = Math.max(maxArea, bfs(i));
            }
        }

        bw.write(count+"\n");
        bw.write(maxArea+"\n");
        bw.flush();
        bw.close();
        br.close();

    }
}

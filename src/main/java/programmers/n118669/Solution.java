package programmers.n118669;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /*
    n개의 지점에 출입구, 쉼터, 산봉우리가 있다.
    모든 지점은 양방향 등산로 존재
    등산코스를 지점의 번호나열로 나타낸다.
    쉼터와 산봉우리를 지날때 휴식을 할 수 있다.
    intensity: 휴식없이 이동해야하는 가장 긴 코스

    출입구에서 출발하여, 하나의 산봉우리를 거쳐 원래 출입구로 돌아오는 코스를 정한다.
    출입구는 처음과 끝, 산봉우리는 한번만 코스에 포함된다.

    intensity가 최소가 되게하는 경로의 '산봉우리 번호'와 'intensity의 최솟값'을 반환한다.
    (intensity가 최소인 경로가 다수라면, 최소인 산봉우리 번호를 출력한다.)

    최대 20만개의 경로
    5만개의 노드

    gates.length + summits.length = n



    */
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};

        List<int[]>[] adjList = new ArrayList[50001];

        for (int i = 0; i < 50001; i++) adjList[i] = new ArrayList<>();

        for (int[] p : paths) {
            int u = p[0];
            int v = p[1];
            int w = p[2];
            adjList[u].add(new int[]{v, w});
            adjList[v].add(new int[]{u, w});
        }

        for (int u = 0; u < 50_001; u++)  {

        }


        return answer;
    }
}

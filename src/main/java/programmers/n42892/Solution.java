package programmers.n42892;

import java.util.*;
import java.util.stream.IntStream;

public class Solution {
    /*
    좌표로 주어진 노드들
    y가 같으면 같은 깊이.
    이진 트리를 전위순회 후위순회한 결과를 반환한다.
    노드
     */
    public static int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;
        int[][] answer = new int[2][n];

        // 좌표쌍을 보고. 그래프에 추가하는 방식
        int[] sortedIdx = IntStream.range(0, n)
                .boxed()
                .sorted(Comparator
                        .comparingInt((Integer i) -> nodeinfo[i][1])
                        .thenComparingInt(i -> nodeinfo[i][0]))
                .mapToInt(i -> i)
                .toArray();

        int[] binTree = new int[n];
        Arrays.fill(binTree, -1);
        binTree[0] = sortedIdx[0];

        for (int i = 1; i < n; i++) {
            insert(binTree, sortedIdx[i]);
        }

        answer[0] = preOrder();
        answer[0] = postOrder();


        for (int i : answer[0]) System.out.print(i + " ");
        System.out.println();
        for (int i : answer[1]) System.out.print(i + " ");
        return answer;
    }

    static void insert(int[] binTree, int data) {
        int i = 0;
//        while (binTree[i] == data) // compare data

    }

    static int[] preOrder() {
        return null;
    }

    static int[] postOrder() {
        return null;
    }
    public static void main(String[] args) {
        System.out.println(solution(new int[][] {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}}));
    }
}

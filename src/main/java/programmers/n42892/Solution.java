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
    static class Node {
        int idx;
        int x, y;
        int parent;
        int lChild;
        int rChild;
    }
    public static int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;
        int[][] answer = new int[2][n];

        List<int[]> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) nodes.add(new int[]{i, nodeinfo[i][0], nodeinfo[i][1]});


        nodes.sort(Comparator
                .comparingInt((int[] a) -> a[1])
                .reversed()
                .thenComparingInt(a -> a[0]));

        int[] root = nodes.get(0);
        
        for (int i = 0; i < n; i++) {
            insert(root, nodes.get(i));
        }
        
        answer[0] = preOrder();
        answer[0] = postOrder();


        for (int i : answer[0]) System.out.print(i + " ");
        System.out.println();
        for (int i : answer[1]) System.out.print(i + " ");
        return answer;
    }

    static boolean isParentGreater(int[] parent, int[] target) {
        if (parent[2] == target[2]) return parent[1] > target[1];
        return parent[2] > target[2];
    }
    
    static void insert(int[] parent, int[] target) {
        if (isParentGreater(parent, target)) {
            
        } else {

        }
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

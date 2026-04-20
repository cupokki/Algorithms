package programmers.n42892;

import java.util.*;

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
        Node left, right;
        Node (int idx, int x, int y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
    }
    static int[][] answer;
    static int i;
    public static int[][] solution(int[][] nodeinfo) {

        int n = nodeinfo.length;
        answer = new int[2][n];
        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            nodes.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        }

        nodes.sort(Comparator
                .comparingInt((Node a) -> a.y)
                .reversed()
                .thenComparingInt(a -> a.x));

        Node root = nodes.get(0);
        
        for (int i = 1; i < n; i++) {
            insert(root, nodes.get(i));
        }

        i = 0;
        preOrder(root);
        i = 0;
        postOrder(root);


        for (int i : answer[0]) System.out.print(i + " ");
        System.out.println();
        for (int i : answer[1]) System.out.print(i + " ");
        return answer;
    }

    static void insert(Node parent, Node child) {
        if (child.x < parent.x) {
            if (parent.left == null) parent.left = child;
            else insert(parent.left, child);
        } else {
            if (parent.right == null) parent.right = child;
            else insert(parent.right, child);
        }
    }

    static void preOrder(Node node) {
        if (node == null) return;
        answer[0][i++] = node.idx;
        preOrder(node.left);
        preOrder(node.right);
    }

    static void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        answer[1][i++] = node.idx;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][] {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}}));
    }
}

package boj.tree.n1991;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1991
 * 트리 순회
 * 이진트리를 입력받아 전위, 중위, 후위 순회를 출력하는 프로그램
 * 이진트리는 배열로 저장.
 */
//public class Main {
//    static int N; // max 26 //편향 트리라면 높이가 26임
//    static char[] binaryTree = new char[6720000]; // 충분히? 큰 배열
//    static void addNode(char p, char l, char r) {
//        l = l == '.' ? 0 : l;
//        r = r == '.' ? 0 : r;
//        for (int i = 1; i < binaryTree.length; i++) {
//            if (binaryTree[i] == p) {
//                binaryTree[2 * i] = l;
//                binaryTree[2 * i + 1] = r;
//                break;
//            }
//        }
//    }
//
//    static void preOrderTraversal(int i) {
//        if (i >= binaryTree.length || binaryTree[i] == 0) return;
//        System.out.print(binaryTree[i]);
//        preOrderTraversal(2 * i);
//        preOrderTraversal(2 * i + 1);
//    }
//
//    static void inOrderTraversal(int i) {
//        if (i >= binaryTree.length || binaryTree[i] == 0) return;
//        inOrderTraversal(2 * i);
//        System.out.print(binaryTree[i]);
//        inOrderTraversal(2 * i + 1);
//    }
//
//    static void postOrderTraversal(int i) {
//        if (i >= binaryTree.length || binaryTree[i] == 0) return;
//        postOrderTraversal(2 * i);
//        postOrderTraversal(2 * i + 1);
//        System.out.print(binaryTree[i]);
//
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        N = Integer.parseInt(br.readLine());
//        binaryTree[1] = 'A'; // 무조건 루트는 A로 설정됨
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            char parent = st.nextToken().charAt(0);
//            char lChild = st.nextToken().charAt(0);
//            char rChild = st.nextToken().charAt(0);
//            addNode(parent, lChild, rChild);
//        }
//
//
//        //전위
//        preOrderTraversal(1);
//        System.out.println();
//        //중위
//        inOrderTraversal(1);
//        System.out.println();
//        //후위
//        postOrderTraversal(1);
//
//        br.close();
//    }
//}

public class Main {
    static class Node {
//        char body;
        int left = -1;
        int right = -1;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    static int N;
    static Node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        tree = new Node[26];
        // 루트 설정 필요 A

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char p = st.nextToken().charAt(0);
            char l = st.nextToken().charAt(0);
            char r = st.nextToken().charAt(0);
            addNode(p, l, r);
        }
        br.close();
        preoderTraversal('A');
        System.out.println();
        inoderTraversal('A');
        System.out.println();
        postorderTraversal('A');
        System.out.println();


    }
    static void addNode(int p, int l, int r){
        l = l == '.' ? -1 : l;
        r = r == '.' ? -1 : r;
        tree[p - 'A'] = new Node(l, r);
    }
    static void preoderTraversal(int root) {
        if(root < 'A') return;
        System.out.print((char)root);
        preoderTraversal(tree[root - 'A'].left);
        preoderTraversal(tree[root - 'A'].right);
    }

    static void inoderTraversal(int root) {
        if(root < 'A') return;
        inoderTraversal(tree[root - 'A'].left);
        System.out.print((char)root);
        inoderTraversal(tree[root - 'A'].right);
    }
    static void postorderTraversal(int root) {
        if(root < 'A') return;
        postorderTraversal(tree[root - 'A'].left);
        postorderTraversal(tree[root - 'A'].right);
        System.out.print((char)root);
    }
}

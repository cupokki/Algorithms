package boj.unionfind.n1717;

import java.io.*;
import java.util.*;
/*
n개의 집합이 각각 하나의 원소를 가지며, 각 원소는 0부터 n까지이다.
m은 명령어의 개수
a,b는 각각 arguments
유니온-파인드를 구현하여 0이면 유니온, 1이면 파인드를 실행한다.
부모만 넘겨주는게 아니라, "루트"를 넘겨주어야한다.
 */
public class Main {
    static int[] nodes; // 인덱스는 노드정보이며, 값으로 부모노드를 가진다.

    static void union(int a,int b){
        int rootA = getRoot(a);
        int rootB = getRoot(b);
        if(rootA == rootB)
            return;
        nodes[rootA] = rootB;

    }
    static int getRoot(int a) {
        //자신이 자신을 참조하면 루트
        if(nodes[a] == a)
            return a;
        else{
            //노드의 값은?
            //루트를 찾는다.

            nodes[a] = getRoot(nodes[a]);
            return nodes[a];
        }
//            이걸 분해하면 어케되나
//            return nodes[a] = getRoot(nodes[a]);


    }
    static boolean find(int a, int b){
        return getRoot(a) == getRoot(b);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 집합의 개수 n + 1개
        int m = Integer.parseInt(st.nextToken()); // 입력으로 주어지는 연산의 개수
        nodes = new int[n + 1];
        //집합과 각 집합의 원소 초기화
        for(int i = 1; i <= n ; i++){
            nodes[i] = i;
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int operation = st.nextToken().charAt(0);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(operation == '0'){
                //union
                union(a, b);
            }else{
                //find
                System.out.println(find(a, b) ? "YES" : "NO");
            }
        }
    }
}

package programmers.n150367;

public class Solution {
    /*
    수를 이진트리로 표현한다. 표현가능하면 1, 불가능하면 0을 출력한다.
    더미노드를 추가하여 루트노드를 유지한체 포화이진트리를 만든다.
    노드의 번호는 높이 영향없이 왼쪽에서 오른쪽으로 번호가 매겨진다.
    번호대로 살펴보며, 살펴본 노드가 더미라면 문자열뒤에 0, 아니라면 1을 추가.
    문자열에 저장된 이진수를 십진수로 변환


    7 = 111
    42 = 00000 1 01000
    5 = 101


     */
    public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String bin = Long.toBinaryString(numbers[i]);
            int len = bin.length();

            int n = 1;
            while (n < len) {
                n = (n << 1) | 1; // 1, 3, 7, 15... 식으로 (2^h - 1) 증가
            }

            // 포화를 위한 패딩
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n - len; j++) sb.append("0");
            sb.append(bin);
            String tree = sb.toString();

            if (dfs(tree, 0, tree.length() - 1)) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }

    static boolean dfs(String s, int start, int end) {
        if (start == end) {
            return true;
        }

        // 현재 노드와 양쪽 자식 노드의 인덱스 계산
        int parent = (start + end) / 2;
        int leftChild = (start + parent - 1) / 2;
        int rightChild = (parent + 1 + end) / 2;

        if (s.charAt(parent) == '0') {
            if (s.charAt(leftChild) == '1' || s.charAt(rightChild) == '1') {
                return false;
            }
        }

        // 유효하다면 없다면 왼쪽, 오른쪽 서브트리도 계속해서 검사
        return dfs(s, start, parent - 1) && dfs(s, parent + 1, end);
    }

    public static void main(String[] args) {

    }
}

package programmers.n131704;

import java.util.Stack;

public class Solution {
    // 컨베이어에 1~N 순서대로 상품이 있다.
    // 트럭에 order배열(<= 1,000,000)의 요소 값 대로 상차해야한다.
    // 상품은 임시로 사이드 컨베이어에 넣을 수 있다.
    // 보조 컨테이어는 lifo구조이다.
    // 최대 몇개의 상품?
    public static int solution(int[] order) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>(); // 보조 컨베이어

        int boxNum = 0; // 상자 번호
        for (int i = 1; i <= order.length; i++ ) {
            while(!stack.isEmpty() && stack.peek() == order[boxNum]) {
                stack.pop();
                boxNum++;
            }

            if (i == order[boxNum]) {
                boxNum++; // 상차 하나 완료
                continue;
            }

            stack.push(i);
        }

        while(!stack.isEmpty() && stack.peek() == order[boxNum]) {
            stack.pop();
            boxNum++;
        }

        return boxNum;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{4, 3, 1, 2, 5}));
        System.out.println(solution(new int[]{5, 4, 3, 2, 1}));
    }

}


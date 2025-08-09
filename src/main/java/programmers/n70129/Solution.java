package programmers.n70129;

public class Solution {

    public static int[] solution(String s) {
        int removedCount = 0;
        int time = 0;

        while(!s.equals("1")) {
            time++;
            int currentLength = s.length();
            int newLength = s.replaceAll("0", "").length();
            removedCount += currentLength - newLength;
            s = Integer.toBinaryString(newLength);
        }

        return new int[]{time, removedCount};
    }
    public static void main(String[] args) {
        int[] result = solution("01110");
        System.out.println(result[0]+ " " +result[1]);
    }
}

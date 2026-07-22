package programmers.n49995;

public class Solution {
    /*
    번호가 붙은 바구니에 과자가 있다. 바구니의 과자 수가 주어질떄
    r <= m <= l에서 , [r, m], [m + 1, l] 의 합이 같아야한다.
    한명이 가질 수 있는 과자의 수 중 가장 큰 수를 출력한다.
    불가능 하다면 0

    배열의 길이는 2000

    2000 ^ 3 = 40000000 * 2000 = 8 000 000 000 => 80억회
    */
    public int solution(int[] cookie) {
        int answer = 0;

        int n = cookie.length;

        for (int m = 0; m < n - 1; m++) {
            int l = m;
            int r =  m + 1;
            int firstSon = cookie[l];
            int secondSon = cookie[r];
            while (l > 0 || r < n) {
                if (firstSon == secondSon) {
                    answer = Math.max(answer, firstSon);
                }

                if (firstSon <= secondSon) {
                    if (l == 0) break;
                    firstSon += cookie[--l];
                } else{
                    if (r == n - 1) break;
                    secondSon += cookie[++r];
                }

            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[]{1, 1, 2, 3}));
        System.out.println(sol.solution(new int[]{1, 2, 4, 5}));
    }
}
package programmers.n131703;

class Solution {
    /*
    앞뒤가 존재하는 동전이 놓인 격자에
    초기상태와 목표상태가 주어진다.
    초기상태에서 '최소' 몇번 뒤집어야 목표가 되는가? (실패시 -1)
    
    격자의 각 변의 길이는 10이하=
    
    
    */
    public int solution(int[][] beginning, int[][] target) {
        int answer = 0;
        int n = beginning.length;
        int m = beginning[0].length;

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (beginning[i][j] == 0) cnt++;
            }
        }

        if (n * m == cnt) {
            return -1;
        }





        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // 5
        System.out.println(sol.solution(
                new int[][]{
                        {0, 1, 0, 0, 0},
                        {1, 0, 1, 0, 1},
                        {0, 1, 1, 1, 0},
                        {1, 0, 1, 1, 0},
                        {0, 1, 0, 1, 0}
                },
                new int[][] {
                        {0, 0, 0, 1, 1},
                        {9, 0, 0, 0, 1},
                        {0, 0, 1, 0, 1},
                        {0, 0, 0, 1, 0},
                        {0, 0, 0, 0, 1}
                }
        ));

        // -1
        System.out.println(sol.solution(
                new int[][]{
                        {0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}
                },
                new int[][] {
                        {1, 0, 1},
                        {0, 0, 0},
                        {0, 0, 0},
                }
        ));
    }

}
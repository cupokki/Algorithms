package programmers.n68926;

public class Solution {
    /*
    01로 이루어진 2^n*2^n 행렬 입력.
    쿼드트리 방식 압축수행
    1. 대상 영억을 S로 칭함
    2. S가 모두 같은 값이라면 해당 수로 압축
    3. 아니라면 4개의 균일한 정사각형 영역으로 쪼갠 뒤, 각 영영에 대해 같은 방식으로 압축
    최종적으로 남는 0과 1의 개수를 배열로 나타내어라
     */
    static int[] answer;
    static int[][] matrix;
    public static int[] solution(int[][] arr) {
        answer = new int[]{0, 0};
        matrix = arr;
        int len = arr.length;
        compression(len, 0, 0);
        System.out.println(answer[0] + " " + answer[1]);
        return answer;
    }
    static void compression(int len, int r, int c) {
        if (validate(len, r, c)) {
            answer[matrix[r][c]]++;
            return;
        }

        len /= 2;
        compression(len, r, c); // 반복문 보단 4개정도면 그냥 풀어서 쓰는것도 ㄱㅊ
        compression(len, r, c + len);
        compression(len, r + len, c);
        compression(len, r + len, c + len);
    }
    static boolean validate(int len, int r, int c) {
        int temp = matrix[r][c];
        for (int i = r; i < r + len; i++) {
            for (int j = c; j < c + len; j++) {
                if (matrix[i][j] != temp) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] input1 = {
                {1,1,0,0},
                {1,0,0,0},
                {1,0,0,1},
                {1,1,1,1}
        };
        int[][] input3 = {
                {1,1,1,1},
                {1,1,1,1},
                {1,1,1,1},
                {1,1,1,1}
        };

        int[][] input2 = {
                {1,1,1,1,1,1,1,1},
                {0,1,1,1,1,1,1,1},
                {0,0,0,0,1,1,1,1},
                {0,1,0,0,1,1,1,1},
                {0,0,0,0,0,0,1,1},
                {0,0,0,0,0,0,0,1},
                {0,0,0,0,0,0,1,1},
                {0,0,0,0,1,1,1,1}
        };
        solution(input1);
        solution(input2);
        solution(input3);
    }
}

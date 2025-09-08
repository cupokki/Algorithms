package programmers.n42578;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static int solution(String[][] clothes) {
        Map<String, Integer> closet = new HashMap<>();
        // 같은 이름의 옷은 없었음 그냥 배열로도 처리가능

        for (String[] row : clothes) {
            closet.merge(row[1], 1, Integer::sum);
        }
        int result = 1;
        for (var entry : closet.entrySet()) {
            result *= entry.getValue() + 1;
        }
        // 아무것도 입지 않는 경우 제거
        result = result - 1; // 코니는 하루에 최소 한 개의 의상은 입습니다.
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(
                new String[][]{
                        {"yellow_hat", "headgear"},
                        {"blue_sunglasses", "eyewear"},
                        {"green_turban", "headgear"}
                }
        ));
        System.out.println(solution(
                new String[][]{
                        {"crow_mask", "face"},
                        {"blue_sunglasses", "face"},
                        {"smoky_makeup", "face"}
                }
        ));

    }
}

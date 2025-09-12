package programmers.n17680;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        Queue<String> cache = new LinkedList<>();

        int answer = 0;
        for (String city : cities) {
            String query = city.toUpperCase();
            if(cache.contains(query)){ // hit
                answer += 1;
                cache.remove(query);
            } else { // miss
                if (cache.size() == cacheSize) cache.poll();
                answer += 5;
            }
            cache.offer(query);
        }
        return answer; // 총 실행시간
    }

    public static void main(String[] args) {

        System.out.println(solution(
                2,
                new String[]{"Jeju", "Pangyo", "NewYork", "newyork"}
        ));

        System.out.println(solution(
                3,
                new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA" }
        ));
    }
}

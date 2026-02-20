package programmers.n42579;

import java.util.*;

public class Solution {
    /*
    장르별 가장 많이 재생된 노래 두개씩 모아 베스트앨범
    1. 속한 노래가장 많이 재생된 장르 먼저 수록
    2. 장르 내 많이 재생된 노래를 먼저 수록
    3. 장르 내에서 재생 수많인 노래 중 번호가 낮은 노래먼저
    */
    public static int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        int n = genres.length;
        Map<String, Integer> genreFreqMap = new HashMap<>();
        Map<String, List<int[]>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            genreFreqMap.merge(genres[i], plays[i], Integer::sum);
            var list = map.computeIfAbsent(
                    genres[i],
                    k -> new ArrayList<>()
            );
            list.add(new int[]{i, plays[i]});
            Collections.sort(list, Comparator.comparing((int[] a) -> a[1]).reversed()
                    .thenComparing(a -> a[0]));
            if (list.size() > 2) list.remove(2);
        }

        Map.Entry<String,Integer>[] sortedOrder = genreFreqMap.entrySet().toArray(Map.Entry[]::new);
        Arrays.sort(sortedOrder, Comparator.comparing((Map.Entry<String, Integer> e) -> e.getValue()).reversed());
        List<Integer> album = new ArrayList<>();
        for (var e : sortedOrder) {
            for (int[] song : map.get(e.getKey())) {
                album.add(song[0]);
            }
        }
        answer = album.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }

    public static void main(String[] args) {
        Arrays.stream(solution(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 800, 2500}))
                .forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
}
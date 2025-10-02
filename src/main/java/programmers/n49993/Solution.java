package programmers.n49993;

public class Solution {
    // 대문자알파벳으로 표기된 스킬트리 skill이 주어질때, skill_trees에 몇개의 요소가 가능한가.
    // 20개의 스킬트리를 검사해야한다.
    public static int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for (String row : skill_trees) {
            row = row.replaceAll(String.format("[^%s]", skill),"");
            int index = 0;
            boolean done = true;
            for (char c : row.toCharArray()) {
                if(c != skill.charAt(index)) {
                    done = false;
                    break;
                }
                index++;
            }
            if(done) answer++;
        }

        return answer;
    }
    public static void main(String[] args) {
        System.out.println(solution("CBD", new String[]{"BACDE", "CBADF", "AECB", "BDA"}));
    }
}

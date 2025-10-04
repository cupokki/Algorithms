package programmers.n42888;

import java.util.*;

public class Solution {
    //첫 단어는 Enter, Leave, Change
    //이름을 바꾸는 방법 두가지
    // 1. 나간후 새로 닉네임 바꾸고 다시 들어간다 -> 나간상태에서 기존 uid와 변경한 username으로 Enter
    // 2. 채팅방에서 닉네임 변경한다. -> Change
    public static String[] solution(String[] record) {
        Map<String, String> users = new HashMap(); // uid, username pair
        Set<String> chatMembers = new HashSet<>();
        List<String[]> chatLog = new ArrayList<>(); // uid
        String enterformat = "%s님이 들어왔습니다.";
        String leaveformat = "%s님이 나갔습니다.";
        for (String row : record) {
            String[] col = row.split(" ");
            String uid = col[1];
            String username;

            switch(col[0]) {
                case "Enter":
                    username = col[2];
                    users.put(uid, username);
                    chatLog.add(new String[]{enterformat, uid});
                    chatMembers.add(uid);
                    break;
                case "Leave":
                    chatLog.add(new String[]{leaveformat, uid});
                    chatMembers.remove(uid);
                    break;
                case "Change":
                    username = col[2];
                    users.put(uid, username);
                    break;
            }
        }

        String[] result = chatLog.stream()
                .map(l -> String.format(l[0], users.get(l[1])))
                .toArray(String[]::new);

        return result;
    }

    public static void main(String[] args) {
        solution(new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"});
    }
}

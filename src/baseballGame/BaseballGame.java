package baseballGame;

import java.util.HashSet;

public class BaseballGame {
    // 정답이 될 랜덤 세자리 숫자 생성
    public HashSet<Integer> randomNum(int count) {
        HashSet<Integer> resultNum = new HashSet<>();
        for (int i = 0; resultNum.size() < count; i++) {
            resultNum.add((int) (Math.random() * 9) + 1);
        }
        return resultNum;
    }
}

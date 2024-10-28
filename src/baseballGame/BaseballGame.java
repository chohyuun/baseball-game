package baseballGame;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// todo: map 다른 형식으로 변환
public class BaseballGame {
    private HashSet<Integer> randomNum = new HashSet<>();
    public HashMap<String, Integer> strikeBallCheck = new HashMap<>(){{
        put("Strike", 0);
        put("Ball", 0);
    }};
    private int tryCount = 0;

    // 정답이 될 랜덤 세자리 숫자 생성
    public void createRandomNum(int count) {
        randomNum.clear();
        while (randomNum.size() < count) {
            randomNum.add((int) (Math.random() * 9) + 1);
        }
    }

    // Strike ball 개수 확인
    public void strikeBallCheck(ArrayList<Integer> result) {
        AtomicInteger index = new AtomicInteger();
        strikeBallCheck.put("Strike", 0);
        strikeBallCheck.put("Ball", 0);

        randomNum.forEach((number) -> {
            if (Objects.equals(number, result.get(index.get()))) {
                strikeBallCheck.put("Strike", strikeBallCheck.get("Strike") + 1);
            } else {
                for (Integer integer : result) {
                    if (Objects.equals(integer, number)) {
                        strikeBallCheck.put("Ball", strikeBallCheck.get("Ball") + 1);
                        break;
                    }
                }
            }

            index.set(index.get() + 1);
        });
    }

    // 게임 종료 체크
    public boolean gameEndCheck(int digitNum, HashMap<Integer, Integer> gameCount) {
        tryCount++;

        if (strikeBallCheck.get("Strike") == digitNum) {
            System.out.println("3 Strike! Game is over!");
            gameCount.put(gameCount.size() + 1, tryCount);
            tryCount = 0;
            return true;
        } else if (strikeBallCheck.get("Strike") != 0 || strikeBallCheck.get("Ball") != 0) {
            System.out.println(strikeBallCheck.get("Strike") + " Strike, " + strikeBallCheck.get("Ball") + " Ball");
        } else {
            System.out.println("Out");
        }
        return false;
    }
}

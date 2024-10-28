package baseballGame;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BaseballGame {
    private HashSet<Integer> randomNum = new HashSet<>();

    // 정답이 될 랜덤 세자리 숫자 생성
    public void createRandomNum(int count) {
        randomNum.clear();
        while (randomNum.size() < count) {
            randomNum.add((int) (Math.random() * 9) + 1);
        }
    }

    // Strike ball 개수 확인
    public void StrikeBallCheck(HashMap<String, Integer> strikeBallCheck, ArrayList<Integer> result) {
        AtomicInteger index = new AtomicInteger();
        System.out.println(randomNum);

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
}

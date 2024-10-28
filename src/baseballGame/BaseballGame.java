package baseballGame;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BaseballGame {
    private HashSet<Integer> randomNum = new HashSet<>();
    private int tryCount = 0;
    private int strikeCount = 0;
    private int ballCount = 0;

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
        strikeCount = 0;
        ballCount = 0;

        randomNum.forEach((number) -> {
            if (Objects.equals(number, result.get(index.get()))) {
                strikeCount++;
            } else {
                for (Integer integer : result) {
                    if (Objects.equals(integer, number)) {
                        ballCount++;
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

        if (strikeCount == digitNum) {
            System.out.println("3 Strike! Game is over!");
            gameCount.put(gameCount.size() + 1, tryCount);
            tryCount = 0;
            return true;
        } else if (strikeCount != 0 || ballCount != 0) {
            System.out.println(strikeCount + " Strike, " + ballCount + " Ball");
        } else {
            System.out.println("Out");
        }
        return false;
    }
}

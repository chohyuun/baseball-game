import baseballGame.BaseballGame;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BaseballGame baseballGame = new BaseballGame();

        HashSet<Integer> randomNum = baseballGame.randomNum();


        while (true) {
            Map<String, Integer> strikeBallCheck = new HashMap<>() {{
                put("Strike", 0);
                put("Ball", 0);
            }};
            ArrayList<Integer> result = new ArrayList<>(3);
            AtomicInteger index = new AtomicInteger();

            try {
                // 정답을 맞출 3개의 숫자를 입력 받기
                while (result.size() < 3) {
                    System.out.print((result.size() + 1) + " 번째 숫자를 입력해 주세요: ");

                    int inputNum = scanner.nextInt();

                    if (result.contains(inputNum) || inputNum < 1 || inputNum > 9) {
                        // 숫자를 중복 입력했을 때의 예외처리
                        System.out.println("잘못된 입력입니다. 다시 입력해 주세요.(중복 숫자, 1 - 9를 벗어난 수는 입력될 수 없습니다)");
                        continue;
                    }
                    result.add(inputNum);
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("" + result.get(0) + result.get(1) + result.get(2));

            randomNum.forEach((number) -> {
                if (Objects.equals(number, result.get(index.get()))) {
                    // Strike 인지 체크
                    strikeBallCheck.put("Strike", strikeBallCheck.get("Strike") + 1);
                } else {
                    // Strike 가 아니라면 Ball 인지 체크
                    for (int i = 0; i < result.size(); i++) {
                        if (Objects.equals(result.get(i), number)) {

                            strikeBallCheck.put("Ball", strikeBallCheck.get("Ball") + 1);
                            break;
                        }
                    }
                }
                index.set(index.get() + 1);
            });

            // 게임 종료 체크
            if (strikeBallCheck.get("Strike") > 2) {
                System.out.println("3 Strike! Game is over!");
                break;
            } else if (strikeBallCheck.get("Strike") != 0 || strikeBallCheck.get("Ball") != 0) {
                System.out.println(strikeBallCheck.get("Strike") + " Strike, " + strikeBallCheck.get("Ball") + " Ball");
            } else {
                System.out.println("Out");
            }
        }
    }
}
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

            int inputResultNum = 0;

            try {
                System.out.print("숫자를 입력해 주세요: ");
                inputResultNum = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("숫자만 입력 가능합니다.");
                // 무한 루프 발생으로 인한 입력 초기화
                scanner.next();
                continue;
            }

            for (int i = inputResultNum; i > 0; i /= 10) {
                // 중복된 숫자가 있을 경우 예외 처리
                if (!result.contains(i % 10)) {
                    result.add(i % 10);
                } else {
                    break;
                }
            }

            if (result.size() != 3 || inputResultNum >= 1000 || result.contains(0)) {
                // 3자리가 아니거나 0이 포함 되었을 경우 예외처리
                System.out.println("다시 입력해 주세요.");
                continue;
            } else {
                // 입력 숫자와 같게 만들기 위해 Array reverse
                Collections.reverse(result);
            }

            System.out.println(inputResultNum);

            randomNum.forEach((number) -> {
                if (Objects.equals(number, result.get(index.get()))) {
                    // Strike 인지 체크
                    strikeBallCheck.put("Strike", strikeBallCheck.get("Strike") + 1);
                } else {
                    // Ball 인지 체크
                    for (Integer integer : result) {
                        if (Objects.equals(integer, number)) {
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
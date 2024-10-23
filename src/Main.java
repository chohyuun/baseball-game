import baseballGame.BaseballGame;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BaseballGame baseballGame = new BaseballGame();
        HashSet<Integer> randomNum = baseballGame.randomNum();
        Map<Integer, Integer> gameCount = new HashMap<>();

        int count = 0;
        // 게임을 다시 시작했는지 확인하기 위한 변수
        boolean gameReset = true;

        while (true) {
            Map<String, Integer> strikeBallCheck = new HashMap<>() {{
                put("Strike", 0);
                put("Ball", 0);
            }};

            ArrayList<Integer> result = new ArrayList<>(3);
            AtomicInteger index = new AtomicInteger();

            String gameType;

            if(gameReset) {
                System.out.print("1. 게임 시작  2. 게임 기록 보기  3. 종료하기 \n입력: ");

                gameType = scanner.nextLine();

                if (Objects.equals(gameType, "3")) {
                    // 게임 종료
                    System.out.println("게임이 종료 됩니다! 감사!");
                    break;
                } else if (Objects.equals(gameType, "2")) {
                    // 게임 기록 확인
                    if(gameCount.isEmpty()){
                        System.out.println("게임을 시작한 적이 없어 기록이 없습니다. 게임 시도 후 다시 확인해 주세요.");
                        continue;
                    }
                    gameCount.forEach((key, value) -> {
                        System.out.println(key + "번째 게임 : 시도 횟수 - " + value);
                    });
                    continue;
                } else if (!Objects.equals(gameType, "1")) {
                    // 잘못된 문자 입력을 받았을 경우 예외 처리
                    System.out.println("선택 가능한 숫자를 입력해 주세요.");
                    continue;
                }
                gameReset = false;
            }

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

            count ++;

            // 게임 종료 체크
            if (strikeBallCheck.get("Strike") > 2) {
                System.out.println("3 Strike! Game is over!");
                gameReset = true;
                scanner.nextLine();
                gameCount.put(gameCount.size() + 1, count);
                count = 0;
            } else if (strikeBallCheck.get("Strike") != 0 || strikeBallCheck.get("Ball") != 0) {
                System.out.println(strikeBallCheck.get("Strike") + " Strike, " + strikeBallCheck.get("Ball") + " Ball");
            } else {
                System.out.println("Out");
            }
        }
    }
}
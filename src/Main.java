import baseballGame.BaseballGame;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Objects;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BaseballGame baseballGame = new BaseballGame();
        baseballGame.createRandomNum(3);
        HashMap<Integer, Integer> gameCount = new HashMap<>();

        // 게임 자리수 변수
        int digitNum = 3;
        // 게임을 다시 시작했는지 확인하기 위한 변수
        boolean isGameReset = true;


        while (true) {
            ArrayList<Integer> result = new ArrayList<>();
            String gameType;

            if (isGameReset) {
                System.out.print("0. 자리수 설정(default: 3)  1. 게임 시작  2. 게임 기록 보기  3. 종료하기 \n입력: ");

                gameType = scanner.nextLine();
                baseballGame.createRandomNum(3);

                if (Objects.equals(gameType, "0")) {

                    while (true) {
                        try {
                            // 게임 난이도 지정
                            System.out.print("설정하고자 하는 자리수를 입력 하세요(3, 4, 5 중 선택해 주세요.): ");
                            digitNum = scanner.nextInt();

                            if (digitNum > 2 && digitNum < 6) {
                                baseballGame.createRandomNum(digitNum);
                                break;
                            } else {
                                throw new Exception();
                            }
                        } catch (Exception e) {
                            System.out.println("숫자가 아니거나 범위를 벗어났습니다. 다시 입력해 주세요.");
                            scanner.nextLine();
                        }
                    }

                } else if (Objects.equals(gameType, "3")) {
                    // 게임 종료
                    System.out.println("게임이 종료 됩니다! 감사!");
                    break;
                } else if (Objects.equals(gameType, "2")) {
                    // 게임 기록 확인
                    if (gameCount.isEmpty()) {
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
                isGameReset = false;
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

            if (result.size() != digitNum || inputResultNum >= Math.pow(10, digitNum) || result.contains(0)) {
                // 3자리가 아니거나 0이 포함 되었을 경우 예외처리
                System.out.println("다시 입력해 주세요.");
                continue;
            } else {
                // 입력 숫자와 같게 만들기 위해 Array reverse
                Collections.reverse(result);
            }

            System.out.println(inputResultNum);

            baseballGame.strikeBallCheck(result);
            isGameReset = baseballGame.gameEndCheck(digitNum, gameCount);

            // 버퍼 내용 제거
            if (isGameReset) {
                scanner.nextLine();
            }
        }
    }
}
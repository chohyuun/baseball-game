import baseballGame.BaseballGame;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    // todo: 입력 부 class 분리 또는 오류 발생시 재 입력 받을 수 있도록 변경 필요.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BaseballGame baseballGame = new BaseballGame();
        ArrayList<Integer> result = new ArrayList<>(3);

        baseballGame.randomNum();

        try {
            // 정답을 맞출 3개의 숫자를 입력 받기
            while (result.size() < 3) {
                System.out.print((result.size() + 1) + " 번째 숫자를 입력해 주세요: ");

                int inputNum = scanner.nextInt();

                if (result.contains(inputNum) || inputNum == 0) {
                    // 숫자를 중복 입력했을 때의 예외처리
                    System.out.println("잘못된 입력입니다. 다시 입력해 주세요.(중복 숫자 또는 0은 입력될 수 없습니다)");
                    continue;
                }

                result.add(inputNum);
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }
}
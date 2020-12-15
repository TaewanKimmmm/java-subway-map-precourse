package subway.view;

import subway.type.StationScreenFunctionType;

import java.util.Scanner;

public class StationInputView {
    private static final String SELECT_MESSAGE = "## 원하는 기능을 선택하세요.";
    private static final String CANNOT_SELECT_MESSAGE = "[ERROR] 선택할 수 없는 기능입니다.";
    private static final String REGISTER_STATION_MESSAGE = "## 등록할 역 이름을 입력하세요.";
    private static final String REMOVE_STATION_MESSAGE = "## 삭제할 역 이름을 입력하세요.";
    private static final String STATION_NAME_LENGTH_MESSAGE = "[ERROR] 역 이름은 두 글자 이상이어야 합니다.";
    private static final String STATION_NAME_INCLUDE_NOT_KOREAN_MESSAGE = "[ERROR] 역 이름은 한글이어야 합니다.";
    private static final String STATION_NAME_SHOULD_BE_ENDED_WITH_STATION_MESSAGE = "[ERROR] 역 이름 맨 뒤에 '역'을 붙여주세요.";
    private static final String STATION_NAME_INCLUDE_NUMBER_MESSAGE = "[ERROR] 역 이름에 숫자를 포함하면 안 됩니다.";
    private static final String STATION_NAME_INCLUDE_SPACE_MESSAGE = "[ERROR] 역 이름에 공백을 포함하면 안 됩니다.";
    private static final char ONE = '1';
    private static final char MINIMUM_STATION_NAME_LENGTH = 2;
    private static final char THREE = '3';
    private static final char BACK = 'B';
    private static final String SPACE = " ";


    private static final Scanner scanner = new Scanner(System.in);

    private StationInputView() {
    }

    public static void getStationScreenUserSelection() {
        StationOutputView.printManageStationScreen();
        System.out.println(SELECT_MESSAGE);
        String userInput = scanner.nextLine();
        try {
            validateUserInput(userInput);
            if (isUserInputBACK(userInput.charAt(0))) {
                MainScreenInputView.getMainScreenUserSelection(scanner);
                return;
            }
            executeFunction(userInput);
        } catch (Exception e) {
            System.out.println();
            System.out.println(e.getMessage());
            getStationScreenUserSelection();
        }
    }

    private static void executeFunction(String userInput) {
        for (StationScreenFunctionType stationScreenFunctionType : StationScreenFunctionType.values()) {
            if (stationScreenFunctionType.isSameFunctionCode(Integer.parseInt(userInput))) {
                stationScreenFunctionType.execute();
            }
        }
    }

    private static void validateUserInput(String userInput) {
        validateUserInputLength(userInput);
        char characterizedUserInput = userInput.charAt(0);
        if (!isUserInputBACK(characterizedUserInput)) {
            validateUserInputRange(characterizedUserInput);
        }
    }

    private static void validateUserInputLength(String userInput) {
        if (userInput.length() != 1) {
            throw new IllegalArgumentException(CANNOT_SELECT_MESSAGE);
        }
    }

    private static boolean isUserInputBACK(char userInput) {
        return Character.toUpperCase(userInput) == BACK;
    }

    private static void validateUserInputRange(char userInput) {
        if ((userInput < ONE || userInput > THREE)) {
            throw new IllegalArgumentException(CANNOT_SELECT_MESSAGE);
        }
    }

    public static void getStationName() {
        System.out.println(REGISTER_STATION_MESSAGE);
        String stationName = scanner.nextLine();
        try {
            validateStationName(stationName);
            // TODO 역 추가
        } catch (Exception e) {
            System.out.println();
            System.out.println(e.getMessage());
            getStationName();
        }
    }

    private static void validateStationName(String stationName) {
        validateStationNameLength(stationName);
        validateStationNameEndedWithStation(stationName);
        validateStationNameHasNumber(stationName);
        validateStationNameHasOnlyKorean(stationName);
        validateStationNameHasSpace(stationName);
    }

    private static void validateStationNameLength(String stationName) {
        if (stationName.length() < MINIMUM_STATION_NAME_LENGTH) {
            throw new IllegalArgumentException(STATION_NAME_LENGTH_MESSAGE);
        }
    }

    private static void validateStationNameEndedWithStation(String stationName) {
        if (!(stationName.charAt(stationName.length() - 1) == '역')) {
            throw new IllegalArgumentException(STATION_NAME_SHOULD_BE_ENDED_WITH_STATION_MESSAGE);
        }
    }

    private static void validateStationNameHasNumber(String stationName) {
        if (stationName.matches(".*[0-9].*")) {
            throw new IllegalArgumentException(STATION_NAME_INCLUDE_NUMBER_MESSAGE);
        }
    }

    private static void validateStationNameHasOnlyKorean(String stationName) {
        if (!stationName.matches("^[가-힣]*$")) {
            throw new IllegalArgumentException(STATION_NAME_INCLUDE_NOT_KOREAN_MESSAGE);
        }
    }

    private static void validateStationNameHasSpace(String stationName) {
        if (stationName.contains(SPACE)) {
            throw new IllegalArgumentException(STATION_NAME_INCLUDE_SPACE_MESSAGE);
        }
    }

}
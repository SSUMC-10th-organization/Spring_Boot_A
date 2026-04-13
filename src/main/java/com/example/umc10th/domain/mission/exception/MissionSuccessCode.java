@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseCode {

    MISSION_FOUND("MISSION200_1", "미션 조회에 성공했습니다"),
    MISSION_CREATED("MISSION201", "미션이 생성되었습니다"),
    MISSION_COMPLETE("MISSION200_2", "미션을 완료했습니다");

    private final String code;
    private final String message;
}

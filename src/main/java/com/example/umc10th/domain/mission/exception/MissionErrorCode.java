@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(404, "MISSION404", "존재하지 않는 미션입니다"),
    MISSION_ALREADY_COMPLETE(400, "MISSION400_1", "이미 완료된 미션입니다"),
    MISSION_NOT_AVAILABLE(400, "MISSION400_2", "도전 가능한 미션이 없습니다");

    private final int status;
    private final String code;
    private final String message;
}

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    DUPLICATE_EMAIL(400, "MEMBER400_1", "이미 사용중인 이메일입니다"),
    WRONG_PASSWORD(400, "MEMBER400_2", "비밀번호가 틀렸습니다"),
    MEMBER_NOT_FOUND(404, "MEMBER404", "존재하지 않는 회원입니다");

    private final int status;
    private final String code;
    private final String message;
}

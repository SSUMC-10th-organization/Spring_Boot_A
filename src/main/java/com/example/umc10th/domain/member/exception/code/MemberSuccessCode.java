@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseCode {

    MEMBER_FOUND("MEMBER200_1", "성공적으로 유저를 조회했습니다"),
    MEMBER_CREATED("MEMBER201", "회원가입이 완료되었습니다"),
    MEMBER_UPDATED("MEMBER200_2", "회원 정보가 수정되었습니다");

    private final String code;
    private final String message;
}

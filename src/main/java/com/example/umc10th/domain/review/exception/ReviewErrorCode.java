@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(404, "REVIEW404", "존재하지 않는 리뷰입니다"),
    REVIEW_ALREADY_EXISTS(400, "REVIEW400_1", "이미 작성한 리뷰입니다"),
    NOT_REVIEW_OWNER(403, "REVIEW403", "리뷰 작성자가 아닙니다");

    private final int status;
    private final String code;
    private final String message;
}

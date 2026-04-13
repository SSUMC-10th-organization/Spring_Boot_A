@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseCode {

    REVIEW_FOUND("REVIEW200_1", "리뷰 조회에 성공했습니다"),
    REVIEW_CREATED("REVIEW201", "리뷰가 작성되었습니다"),
    REVIEW_DELETED("REVIEW200_2", "리뷰가 삭제되었습니다");

    private final String code;
    private final String message;
}

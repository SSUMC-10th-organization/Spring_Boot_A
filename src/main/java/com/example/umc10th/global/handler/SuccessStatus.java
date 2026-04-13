@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    _OK("COMMON200", "성공입니다");

    private final String code;
    private final String message;
}

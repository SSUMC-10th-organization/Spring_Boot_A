@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(GeneralException.class)
    public ApiResponse<?> handleGeneralException(GeneralException e) {
        return ApiResponse.onFailure(e.getCode(), null);
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleException(Exception e) {
        return ApiResponse.onFailure(GeneralErrorCode.INTERNAL_ERROR, null);
    }
}

package UMCFatMan.fatman.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {


    // hashmap 사용해서 바꿔보기

    // Common
    USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "U001", "사용자를 찾을 수 없습니다."),
    SOCIAL_AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "U002","사용자가 OAuth2 로그인에 실패하였습니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "U003", "이미 존재하는 이메일입니다."),
    REFRESH_TOKEN_IS_INVALID(HttpStatus.BAD_REQUEST, "U004", "리프레시 토큰이 유효하지 않습니다."),
    USER_HAVE_NO_MONEY(HttpStatus.BAD_REQUEST, "U005", "유저의 잔액이 부족합니다."),
    MONSTER_NOT_FOUND(HttpStatus.NOT_FOUND, "M001", "존재하지 않는 몬스터입니다."),
    MONSTER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "M002", "이미 잡은 몬스터입니다."),
    IMAGE_NOT_FOUND(HttpStatus.BAD_REQUEST, "I001", "유효하지 않은 이미지입니다."),
    FATMAN_NOT_FOUND(HttpStatus.NOT_FOUND, "F001", "존재하지 않는 팻맨입니다."),
    FATMAN_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "F002", "이미 존재하는 팻맨 입니다.");


    private HttpStatus status;
    private String code;
    private String message;


    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}

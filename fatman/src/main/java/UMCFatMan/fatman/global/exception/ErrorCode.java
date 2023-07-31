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
    MONSTER_NOT_FOUND(HttpStatus.NOT_FOUND, "M001", "존재하지 않는 몬스터입니다."),
    MONSTER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "M002", "이미 잡은 몬스터입니다."),
    IMAGE_NOT_FOUND(HttpStatus.BAD_REQUEST, "I001", "유효하지 않은 이미지입니다.");
    private HttpStatus status;
    private String code;
    private String message;


    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}

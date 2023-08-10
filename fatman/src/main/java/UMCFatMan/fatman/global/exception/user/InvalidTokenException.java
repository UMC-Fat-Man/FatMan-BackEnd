package UMCFatMan.fatman.global.exception.user;

import UMCFatMan.fatman.global.exception.CustomException;
import UMCFatMan.fatman.global.exception.ErrorCode;

public class InvalidTokenException extends CustomException {
    public InvalidTokenException() {
        super(ErrorCode.REFRESH_TOKEN_IS_INVALID);
    }
}

package UMCFatMan.fatman.global.exception.user;

import UMCFatMan.fatman.global.exception.CustomException;
import UMCFatMan.fatman.global.exception.ErrorCode;

public class UserEmailAlreadyExistsException extends CustomException {

    public UserEmailAlreadyExistsException() {
        super(ErrorCode.EMAIL_ALREADY_EXISTS);
    }
}

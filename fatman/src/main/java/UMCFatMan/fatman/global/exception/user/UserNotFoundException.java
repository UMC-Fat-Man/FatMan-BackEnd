package UMCFatMan.fatman.global.exception.user;

import UMCFatMan.fatman.global.exception.CustomException;
import UMCFatMan.fatman.global.exception.ErrorCode;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}

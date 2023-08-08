package UMCFatMan.fatman.global.exception.user;

import UMCFatMan.fatman.global.exception.CustomException;
import UMCFatMan.fatman.global.exception.ErrorCode;

public class AuthenticationFailedException extends CustomException {

    public AuthenticationFailedException() {
        super(ErrorCode.SOCIAL_AUTHENTICATION_FAILED);
    }
}

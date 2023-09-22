package UMCFatMan.fatman.global.exception.user;

import UMCFatMan.fatman.global.exception.CustomException;
import UMCFatMan.fatman.global.exception.ErrorCode;


public class UserNoMoneyException extends CustomException {

    public UserNoMoneyException() {
        super(ErrorCode.USER_HAVE_NO_MONEY);
    }
}


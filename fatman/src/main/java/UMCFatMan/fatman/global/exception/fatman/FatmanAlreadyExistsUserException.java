package UMCFatMan.fatman.global.exception.fatman;

import UMCFatMan.fatman.global.exception.CustomException;
import UMCFatMan.fatman.global.exception.ErrorCode;

public class FatmanAlreadyExistsUserException extends CustomException {

    public FatmanAlreadyExistsUserException() { super(ErrorCode.FATMAN_ALREADY_EXISTS);}
}

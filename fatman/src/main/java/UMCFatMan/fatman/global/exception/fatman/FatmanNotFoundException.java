package UMCFatMan.fatman.global.exception.fatman;

import UMCFatMan.fatman.global.exception.CustomException;
import UMCFatMan.fatman.global.exception.ErrorCode;

public class FatmanNotFoundException extends CustomException {

    public FatmanNotFoundException() {
        super(ErrorCode.FATMAN_NOT_FOUND);
    }
}


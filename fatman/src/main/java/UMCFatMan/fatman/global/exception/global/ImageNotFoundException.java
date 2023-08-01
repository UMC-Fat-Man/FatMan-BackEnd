package UMCFatMan.fatman.global.exception.global;

import UMCFatMan.fatman.global.exception.CustomException;
import UMCFatMan.fatman.global.exception.ErrorCode;

public class ImageNotFoundException extends CustomException {

    public ImageNotFoundException() {
        super(ErrorCode.IMAGE_NOT_FOUND);
    }
}

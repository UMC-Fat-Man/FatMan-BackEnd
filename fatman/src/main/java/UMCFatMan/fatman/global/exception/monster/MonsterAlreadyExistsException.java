package UMCFatMan.fatman.global.exception.monster;

import UMCFatMan.fatman.global.exception.CustomException;
import UMCFatMan.fatman.global.exception.ErrorCode;

public class MonsterAlreadyExistsException extends CustomException {
    public MonsterAlreadyExistsException() {
        super(ErrorCode.MONSTER_ALREADY_EXISTS);
    }
}

package UMCFatMan.fatman.global.exception.monster;

import UMCFatMan.fatman.global.exception.CustomException;
import UMCFatMan.fatman.global.exception.ErrorCode;

public class MonsterNotFoundException extends CustomException {

    public MonsterNotFoundException() {
        super(ErrorCode.MONSTER_NOT_FOUND);
    }
}

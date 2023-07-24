package UMCFatMan.fatman.domain.monster;


import UMCFatMan.fatman.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonsterService {

    private final UserMonsterRepository userMonsterRepository;
    private final MonsterRepository monsterRepository;

    @Transactional
    public Object postUserMonster(UserMonsterRequestDto userMonsterRequestDto, UserDetailsImpl userDetails) {

        Monster monster = monsterRepository.findByName(userMonsterRequestDto.getMonsterName());
        return userMonsterRepository.save(userMonsterRequestDto.toUserMonster(monster, userDetails.getUser()));
    }

    @Transactional
    public List<Monster> getUserMonster(UserDetailsImpl userDetails) {

        List<UserMonster> userMonsterList = userMonsterRepository.findByUser(userDetails.getUser());
        return userMonsterList.stream().map(UserMonster::getMonster).toList();
    }
}

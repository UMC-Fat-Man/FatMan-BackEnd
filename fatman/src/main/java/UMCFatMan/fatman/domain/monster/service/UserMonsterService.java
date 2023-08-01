package UMCFatMan.fatman.domain.monster.service;


import UMCFatMan.fatman.domain.monster.domain.Monster;
import UMCFatMan.fatman.domain.monster.domain.UserMonster;
import UMCFatMan.fatman.domain.monster.dto.UserMonsterRequestDto;
import UMCFatMan.fatman.domain.monster.repository.MonsterRepository;
import UMCFatMan.fatman.domain.monster.repository.UserMonsterRepository;
import UMCFatMan.fatman.global.exception.monster.MonsterAlreadyExistsException;
import UMCFatMan.fatman.global.exception.monster.MonsterNotFoundException;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMonsterService {

    private final UserMonsterRepository userMonsterRepository;
    private final MonsterRepository monsterRepository;

    @Transactional
    public UserMonster postUserMonster(UserMonsterRequestDto userMonsterRequestDto, UserDetailsImpl userDetails) {

        Monster monster = monsterRepository.findByName(userMonsterRequestDto.getMonsterName())
                .orElseThrow(MonsterNotFoundException::new);

        if (userMonsterRepository.existsByUserAndMonster(userDetails.getUser(), monster)){
            throw new MonsterAlreadyExistsException();
        }
        return userMonsterRepository.save(userMonsterRequestDto.toUserMonster(monster, userDetails.getUser()));
    }

    @Transactional
    public List<Monster> getUserMonster(UserDetailsImpl userDetails) {

        List<UserMonster> userMonsterList = userMonsterRepository.findByUser(userDetails.getUser());
        return userMonsterList.stream().map(UserMonster::getMonster).toList();
    }

}

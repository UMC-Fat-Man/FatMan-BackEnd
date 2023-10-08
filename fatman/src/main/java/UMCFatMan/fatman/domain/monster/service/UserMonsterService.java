package UMCFatMan.fatman.domain.monster.service;


import UMCFatMan.fatman.domain.monster.domain.Monster;
import UMCFatMan.fatman.domain.monster.domain.UserMonster;
import UMCFatMan.fatman.domain.monster.dto.UserMonsterRequestDto;
import UMCFatMan.fatman.domain.monster.repository.MonsterRepository;
import UMCFatMan.fatman.domain.monster.repository.UserMonsterRepository;
import UMCFatMan.fatman.domain.users.entity.Users;
import UMCFatMan.fatman.global.exception.monster.MonsterNotFoundException;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserMonsterService {

    private final UserMonsterRepository userMonsterRepository;
    private final MonsterRepository monsterRepository;

    @Transactional
    public Users postUserMonster(UserMonsterRequestDto userMonsterRequestDto, UserDetailsImpl userDetails) {

        Monster monster = monsterRepository.findByName(userMonsterRequestDto.getMonsterName())
                .orElseThrow(MonsterNotFoundException::new);
        Users user = userDetails.getUser();

        boolean isCaughtMonster = userMonsterRepository.existsByUserAndMonster(user, monster);
        if (!isCaughtMonster) {
            userMonsterRepository.save(userMonsterRequestDto.toUserMonster(monster, user));
        }

        addRandomUserPoint(user);
        return user;
    }
    @Transactional
    public List<Monster> getUserMonster(UserDetailsImpl userDetails) {

        List<UserMonster> userMonsterList = userMonsterRepository.findByUser(userDetails.getUser());
        return userMonsterList.stream().map(UserMonster::getMonster).toList();
    }

    private void addRandomUserPoint(Users user) {
        Random random = new Random(System.nanoTime());
        user.updateMoney(user.getMoney() + random.nextInt(10));
    }

}

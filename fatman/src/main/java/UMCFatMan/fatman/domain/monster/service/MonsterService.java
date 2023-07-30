package UMCFatMan.fatman.domain.monster.service;


import UMCFatMan.fatman.domain.monster.repository.MonsterRepository;
import UMCFatMan.fatman.domain.monster.dto.MonsterRequestDto;
import UMCFatMan.fatman.domain.monster.repository.UserMonsterRepository;
import UMCFatMan.fatman.domain.monster.dto.UserMonsterRequestDto;
import UMCFatMan.fatman.domain.monster.domain.Monster;
import UMCFatMan.fatman.domain.monster.domain.UserMonster;
import UMCFatMan.fatman.global.S3.S3Service;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MonsterService {

    private final UserMonsterRepository userMonsterRepository;
    private final MonsterRepository monsterRepository;
    private final S3Service s3Service;

    @Transactional
    public UserMonster postUserMonster(UserMonsterRequestDto userMonsterRequestDto, UserDetailsImpl userDetails) {

        Monster monster = monsterRepository.findByName(userMonsterRequestDto.getMonsterName());
        return userMonsterRepository.save(userMonsterRequestDto.toUserMonster(monster, userDetails.getUser()));
    }

    @Transactional
    public List<Monster> getUserMonster(UserDetailsImpl userDetails) {

        List<UserMonster> userMonsterList = userMonsterRepository.findByUser(userDetails.getUser());
        return userMonsterList.stream().map(UserMonster::getMonster).toList();
    }

    @Transactional
    public Monster addMonster(UserDetailsImpl userDetails, MonsterRequestDto monsterRequestDto, MultipartFile image) throws IOException {
        String imageUrl = s3Service.upload(image);
        Monster monster = monsterRequestDto.toMonster(imageUrl);
        return monsterRepository.save(monster);
    }

    @Transactional
    public Monster updateMonster(UserDetailsImpl userDetails, MonsterRequestDto monsterRequestDto, MultipartFile image) throws IOException {
        Monster monster = monsterRepository.findByName(monsterRequestDto.getName());
        String imageUrl = s3Service.reUpload(image, monster.getImageUrl());

        monster.update(monsterRequestDto, imageUrl);
        return monster;
    }

    @Transactional
    public Object deleteMonster(UserDetailsImpl userDetails, MonsterRequestDto monsterRequestDto) {

        s3Service.delete(monsterRequestDto.getImageUrl());
        return monsterRepository.deleteByName(monsterRequestDto.getName());
    }
}

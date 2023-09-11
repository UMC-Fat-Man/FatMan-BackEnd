package UMCFatMan.fatman.domain.monster.service;


import UMCFatMan.fatman.domain.monster.domain.Monster;
import UMCFatMan.fatman.domain.monster.dto.MonsterRequestDto;
import UMCFatMan.fatman.domain.monster.repository.MonsterRepository;
import UMCFatMan.fatman.global.S3.BucketDir;
import UMCFatMan.fatman.global.S3.ImageService;
import UMCFatMan.fatman.global.S3.S3Service;
import UMCFatMan.fatman.global.exception.monster.MonsterNotFoundException;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MonsterService {
    private final MonsterRepository monsterRepository;
    private final S3Service s3Service;
    private final ImageService imageService;


    @Transactional
    public Monster addMonster(UserDetailsImpl userDetails, String monsterName, MultipartFile image) throws IOException {
        String imageUrl = s3Service.uploadImage(BucketDir.MONSTER, image);

        return monsterRepository.save(Monster.builder()
                .name(monsterName)
                .imageUrl(imageUrl)
                .build());
    }

    @Transactional
    public Monster updateMonster(UserDetailsImpl userDetails, String monsterName, MultipartFile newImage) throws IOException {

        Monster monster = getMonsterByName(monsterName);
        String originImageUrl = monster.getImageUrl();

        String newImageUrl = imageService.reUploadImage(BucketDir.MONSTER, newImage, originImageUrl);
        monster.update(newImageUrl);
        return monster;
    }

    @Transactional
    public void deleteMonster(UserDetailsImpl userDetails, String monsterName) {
        Monster monster = getMonsterByName(monsterName);
        s3Service.deleteImage(monster.getImageUrl());
        monsterRepository.delete(monster);
    }

    @Transactional(readOnly = true)
    public Monster getRandomMonster(UserDetailsImpl userDetails) {
        return monsterRepository.getRandomMonster();
    }

    private Monster getMonsterByName(String monsterName) {
        return monsterRepository.findByName(monsterName)
                .orElseThrow(MonsterNotFoundException::new);
    }
}

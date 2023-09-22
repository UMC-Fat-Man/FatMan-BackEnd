package UMCFatMan.fatman.domain.fatman.service;

import UMCFatMan.fatman.domain.fatman.dto.FatmanRequestDto;
import UMCFatMan.fatman.domain.fatman.dto.FatmanResponseDto;
import UMCFatMan.fatman.domain.fatman.dto.UserFatmanResponseDto;
import UMCFatMan.fatman.domain.fatman.entity.Fatman;
import UMCFatMan.fatman.domain.fatman.mapper.FatmanMapper;
import UMCFatMan.fatman.domain.fatman.repository.FatmanRepository;
import UMCFatMan.fatman.global.S3.BucketDir;
import UMCFatMan.fatman.global.S3.ImageService;
import UMCFatMan.fatman.global.S3.S3Service;
import UMCFatMan.fatman.global.exception.fatman.FatmanNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FatmanService {

    private final FatmanRepository fatmanRepository;
    private final FatmanMapper fatmanMapper;
    private final S3Service s3Service;
    private final ImageService imageService;



    /*
    //   팻맨 추가하기
     */
    @Transactional
    public Fatman addFatman(FatmanRequestDto fatmanRequestDto) throws IOException {
        String fatmanName = fatmanRequestDto.getFatmanName();
        String imageUrl = s3Service.uploadImage(BucketDir.FATMAN,  fatmanRequestDto.getFatmanImage());
        int fatmanCost = fatmanRequestDto.getFatmanCost();

        return fatmanRepository.save(Fatman.builder()
                .name(fatmanName)
                .fatmanImageUrl(imageUrl)
                .cost(fatmanCost)
                .build());
    }



    /*
    //  팻맨 수정하기
     */
    @Transactional
    public FatmanResponseDto updateFatman(Long fatmanId, FatmanRequestDto fatmanRequestDto) throws IOException {
        Fatman fatman = getFatmanById(fatmanId);
        String newfatmanName = fatmanRequestDto.getFatmanName();
        int newCost = fatmanRequestDto.getFatmanCost();
        String originalImageUrl = fatman.getFatmanImageUrl();
        String newImageUrl = imageService.reUploadImage(BucketDir.FATMAN,  fatmanRequestDto.getFatmanImage(), originalImageUrl);


        fatman.update(newfatmanName,newImageUrl,newCost);

        Fatman updatedFatman = fatmanRepository.save(fatman);

        return FatmanMapper.fromEntity(updatedFatman);

    }




    /*
    //  팻맨 삭제하기
     */
    @Transactional
    public void deleteFatman(Long fatmanId) {
        Fatman fatman = getFatmanById(fatmanId);
        s3Service.deleteImage(fatman.getFatmanImageUrl());
        fatmanRepository.delete(fatman);
    }



    /*
    //  팻맨 목록 조회하기
     */
    @Transactional
    public List<FatmanResponseDto> getFatmanList() {
        return fatmanRepository.findAll().stream()
                .map(FatmanMapper::fromEntity)
                .collect(Collectors.toList());
    }


    private Fatman getFatmanById(Long fatmanId) {
        return fatmanRepository.findById(fatmanId)
                .orElseThrow(FatmanNotFoundException::new);
    }

}
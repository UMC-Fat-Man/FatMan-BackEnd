package UMCFatMan.fatman.domain.fatman.service;

import UMCFatMan.fatman.domain.fatman.dto.AddFatmanRequestDto;
import UMCFatMan.fatman.domain.fatman.dto.FatmanResponseDto;
import UMCFatMan.fatman.domain.fatman.entity.Fatman;
import UMCFatMan.fatman.domain.fatman.entity.UserFatman;
import UMCFatMan.fatman.domain.fatman.mapper.FatmanMapper;
import UMCFatMan.fatman.domain.fatman.repository.FatmanRepository;
import UMCFatMan.fatman.domain.fatman.repository.UserFatmanRepository;
import UMCFatMan.fatman.domain.users.entity.Users;
import UMCFatMan.fatman.domain.users.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FatmanService {

    private final UsersRepository userRepository;
    private final FatmanRepository fatmanRepository;
    private final UserFatmanRepository userFatmanRepository;
    private final FatmanMapper fatmanMapper;



    /*
    //  팻맨 추가하기
     */
    @Transactional
    public ResponseEntity<String> addFatman(Long fatmanId, AddFatmanRequestDto addFatmanRequestDto) {
        Long userId = addFatmanRequestDto.getUserId();
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found."));

        Fatman fatman = fatmanRepository.findById(fatmanId)
                .orElseThrow(() -> new NoSuchElementException("Fatman not found."));

        // 해당 유저가 팻맨을 이미 가졌는지 확인
        if (userFatmanRepository.existsByUserAndFatman(user, fatman)) {
            throw new IllegalArgumentException("Fatman already exists for this user.");
        }

        UserFatman userFatman = fatmanMapper.toEntity( user, fatman);
        userFatmanRepository.save(userFatman);
        return ResponseEntity.ok("Fatman added successfully.");
    }



    /*
    //  팻맨 조회하기
     */
    @Transactional
    public FatmanResponseDto getFatman(Long userId) {
        List<UserFatman> userFatmanList = userFatmanRepository.findByUserId(userId);
        List<Long> fatmanId = userFatmanList.stream()
                .map(userFatman -> userFatman.getFatman().getId())
                .collect(Collectors.toList());
        return fatmanMapper.toResponseDto(fatmanId);
    }




}
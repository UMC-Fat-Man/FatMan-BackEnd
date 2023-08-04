package UMCFatMan.fatman.domain.fatman.service;

import UMCFatMan.fatman.domain.fatman.dto.AddFatmanRequestDto;
import UMCFatMan.fatman.domain.fatman.dto.UserFatmanResponseDto;
import UMCFatMan.fatman.domain.fatman.entity.Fatman;
import UMCFatMan.fatman.domain.fatman.entity.UserFatman;
import UMCFatMan.fatman.domain.fatman.mapper.FatmanMapper;
import UMCFatMan.fatman.domain.fatman.repository.FatmanRepository;
import UMCFatMan.fatman.domain.fatman.repository.UserFatmanRepository;
import UMCFatMan.fatman.domain.users.entity.Users;
import UMCFatMan.fatman.domain.users.repository.UsersRepository;
import UMCFatMan.fatman.global.exception.fatman.FatmanAlreadyExistsUserException;
import UMCFatMan.fatman.global.exception.fatman.FatmanNotFoundException;
import UMCFatMan.fatman.global.exception.user.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserFatmanService {

    private final UsersRepository userRepository;
    private final FatmanRepository fatmanRepository ;
    private final UserFatmanRepository userFatmanRepository;
    private final FatmanMapper fatmanMapper;


    /*
    //  유저 팻맨 추가하기
     */
    @Transactional
    public ResponseEntity<String> addUserFatman(Long fatmanId, AddFatmanRequestDto addFatmanRequestDto) {
        Long userId = addFatmanRequestDto.getUserId();
        Users user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Fatman fatman = fatmanRepository.findById(fatmanId)
                .orElseThrow(FatmanNotFoundException::new);

        // 해당 유저가 팻맨을 이미 가졌는지 확인
        if (userFatmanRepository.existsByUserAndFatman(user, fatman)) {
            throw new FatmanAlreadyExistsUserException();
        }

        UserFatman userFatman = fatmanMapper.toEntity( user, fatman);
        userFatmanRepository.save(userFatman);
        return ResponseEntity.ok("Fatman added successfully.");
    }



    /*
    //  유저의 팻맨 조회하기
     */
    @Transactional
    public UserFatmanResponseDto getUserFatman(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        
        List<UserFatman> userFatmanList = userFatmanRepository.findByUserId(userId);
        List<Long> fatmanId = userFatmanList.stream()
                .map(userFatman -> userFatman.getFatman().getId())
                .collect(Collectors.toList());
        return fatmanMapper.toResponseDto(fatmanId);
    }

}
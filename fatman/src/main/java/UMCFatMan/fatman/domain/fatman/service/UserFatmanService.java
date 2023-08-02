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
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    //  팻맨 추가하기
     */
    @Transactional
    public ResponseEntity<String> addUserFatman(Long fatmanId, UserDetailsImpl userDetails) {
//        Long userId = addFatmanRequestDto.getUserId();
//        Users user = userRepository.findById(userId)
//                .orElseThrow(() -> new NoSuchElementException("User not found."));

        // 팻맨 확인
        Fatman fatman = fatmanRepository.findById(fatmanId)
                .orElseThrow(() -> new NoSuchElementException("Fatman not found."));

        // 해당 유저가 팻맨을 이미 가졌는지 확인
        if (userFatmanRepository.existsByUserAndFatman(userDetails.getUser(), fatman)) {
            throw new IllegalArgumentException("Fatman already exists for this user.");
        }

        UserFatman userFatman = fatmanMapper.toEntity(userDetails.getUser() , fatman);
        userFatmanRepository.save(userFatman);
        return ResponseEntity.ok("Fatman added successfully.");
    }


    /*
    //  팻맨 조회하기
     */
    @Transactional
    public FatmanResponseDto getUserFatman(UserDetailsImpl userDetails) {
        List<UserFatman> userFatmanList = userFatmanRepository.findByUser(userDetails.getUser());
        List<Long> fatmanId = userFatmanList.stream()
                .map(userFatman -> userFatman.getFatman().getId())
                .collect(Collectors.toList());
        return fatmanMapper.toResponseDto(fatmanId);
    }




}
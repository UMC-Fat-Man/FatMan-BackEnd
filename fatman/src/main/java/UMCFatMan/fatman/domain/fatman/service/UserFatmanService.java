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
import UMCFatMan.fatman.global.exception.user.UserNoMoneyException;
import UMCFatMan.fatman.global.exception.user.UserNotFoundException;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
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
    public ResponseEntity<String> addUserFatman(UserDetailsImpl userDetails, Long fatmanId ) {
        Long userId = userDetails.getUser().getId();
        Users user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);


        Fatman fatman = fatmanRepository.findById(fatmanId)
                .orElseThrow(FatmanNotFoundException::new);

        // 해당 유저가 팻맨을 이미 가졌는지 확인
        if (userFatmanRepository.existsByUserAndFatman(user, fatman)) {
            throw new FatmanAlreadyExistsUserException();
        }

        // 해당 유저의 money 가져오기
        int UserMoney = user.getMoney();

        // 팻맨의 cost 가져오기
        int FatmanCost = fatman.getCost();

        // 해당 유저에 money에서 팻맨 cost 빼고 -> DB에 저장
        int NewUserMoney = UserMoney - FatmanCost ;

        // 유저 돈 부족하면 오류
        if (NewUserMoney < 1) {
            throw new UserNoMoneyException() ;
        }

        user.setMoney(NewUserMoney);

        UserFatman userFatman = fatmanMapper.toEntity(user, fatman);
        userFatmanRepository.save(userFatman);

        return ResponseEntity.ok("Fatman added successfully.");
    }



    /*
    //  유저의 팻맨 조회하기
     */
    @Transactional
    public UserFatmanResponseDto getUserFatman(UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        Users user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        
        List<UserFatman> userFatmanList = userFatmanRepository.findByUserId(userId);
        List<Long> fatmanId = userFatmanList.stream()
                .map(userFatman -> userFatman.getFatman().getId())
                .collect(Collectors.toList());
        return fatmanMapper.toResponseDto(fatmanId);
    }

}
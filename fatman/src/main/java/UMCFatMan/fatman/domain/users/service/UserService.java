package UMCFatMan.fatman.domain.users.service;


import UMCFatMan.fatman.domain.fatman.entity.UserFatman;
import UMCFatMan.fatman.domain.users.dto.SignupRequestDto;
import UMCFatMan.fatman.domain.users.dto.UserDetailResponseDto;
import UMCFatMan.fatman.domain.users.entity.AuthProvider;
import UMCFatMan.fatman.domain.users.entity.Users;
import UMCFatMan.fatman.domain.users.mapper.UserMapper;
import UMCFatMan.fatman.domain.users.repository.UsersRepository;
import UMCFatMan.fatman.global.exception.user.UserNotFoundException;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    /*
    //   일반 회원가입
    */
    @Transactional
    public void signUp(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();          // 이메일 중복 여부 확인
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }
        Users user = requestDto.getUser(requestDto, passwordEncoder);
        user.setActivated(true);
        user.setAuthProvider(AuthProvider.LOCAL);
        userRepository.save(user);
    }


    /*
    //   내 정보 조회
    */
    @Transactional
    public UserDetailResponseDto getUserInfo(UserDetails userDetails) {
        Users user = ((UserDetailsImpl) userDetails).getUser();
        return UserMapper.toUserDetailResponseDto(user);
    }


    /*
    //   회원 탈퇴
    */
    @Transactional
    public void deleteUser(UserDetails userDetails){
        Users user = ((UserDetailsImpl) userDetails).getUser();
        user.setActivated(false);
        userRepository.save(user);
    }



}

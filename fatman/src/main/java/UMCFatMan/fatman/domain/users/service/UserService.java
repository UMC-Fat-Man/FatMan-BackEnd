package UMCFatMan.fatman.domain.users.service;


import UMCFatMan.fatman.domain.users.dto.SignupRequestDto;
import UMCFatMan.fatman.domain.users.entity.Users;
import UMCFatMan.fatman.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        userRepository.save(user);
    }

}

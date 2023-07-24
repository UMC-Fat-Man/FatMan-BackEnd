package UMCFatMan.fatman.domain.users.service;


import UMCFatMan.fatman.domain.users.dto.SignUpRequestDto;
import UMCFatMan.fatman.domain.users.entity.Users;
import UMCFatMan.fatman.domain.users.mapper.UserMapper;
import UMCFatMan.fatman.domain.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /*
    //   일반 회원가입 [임시]
     */
    public Users signUp(SignUpRequestDto signUpRequestDto) {
        String email = signUpRequestDto.getEmail();          // 이메일 중복 여부 확인
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }
        Users users = userMapper.toEntity(signUpRequestDto);
        return userRepository.save(users);

    }




}

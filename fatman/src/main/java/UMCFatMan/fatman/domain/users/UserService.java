package UMCFatMan.fatman.domain.users;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(SignupRequestDto requestDto) {
        Users user = requestDto.getUser(requestDto, passwordEncoder);
        userRepository.save(user);
    }

}

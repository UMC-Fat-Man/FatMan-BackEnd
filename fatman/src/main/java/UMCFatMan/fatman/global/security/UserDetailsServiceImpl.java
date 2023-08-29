package UMCFatMan.fatman.global.security;

import UMCFatMan.fatman.domain.users.entity.Users;
import UMCFatMan.fatman.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findUsersByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + username));

        // 활성화 상태 검사
        if (!user.isActivated()) {
            throw new DisabledException("User is deactivated");
        }

        return new UserDetailsImpl(user);

    }



}
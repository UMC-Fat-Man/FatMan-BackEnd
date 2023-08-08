package UMCFatMan.fatman.domain.users.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenDto {
    private String jwtToken;
    private String refreshToken;
}

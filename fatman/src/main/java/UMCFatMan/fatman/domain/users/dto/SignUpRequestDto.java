package UMCFatMan.fatman.domain.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {

    private String email;      // 임시

    private String name;       // 임시

    private String nickname ;

    private String address ;

    private String birth;

}

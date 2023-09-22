package UMCFatMan.fatman.domain.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDetailResponseDto {

    private String email;
    private String name;
    private String nickname ;
    private String address ;
    private String birth;
    private int money ;

}

package UMCFatMan.fatman.domain.users.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class SocialDetailRequestDto {

    private String nickname;

    @NonNull
    private String address;

    private String birth;

}

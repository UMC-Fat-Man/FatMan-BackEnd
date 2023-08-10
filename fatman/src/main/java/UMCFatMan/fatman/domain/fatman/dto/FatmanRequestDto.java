package UMCFatMan.fatman.domain.fatman.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FatmanRequestDto {

    private String fatmanName;

    private MultipartFile fatmanImage ;
}

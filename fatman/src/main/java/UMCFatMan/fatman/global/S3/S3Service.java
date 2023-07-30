package UMCFatMan.fatman.global.S3;

import UMCFatMan.fatman.domain.monster.repository.MonsterRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;
    private final MonsterRepository monsterRepository;

    public String upload(MultipartFile multipartFile) throws IOException {
        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(multipartFile.getInputStream().available());

        amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);
        return amazonS3.getUrl(bucket, s3FileName).toString();
    }

    public void delete(String imageUrl) {
        String s3FileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, s3FileName);
        amazonS3.deleteObject(deleteObjectRequest);
    }

    public String reUpload(MultipartFile newImage, String oriImage) throws IOException {
        if (newImage != null && !newImage.isEmpty()) {
            delete(oriImage);
            return upload(newImage);
        }

        return oriImage;
    }


}

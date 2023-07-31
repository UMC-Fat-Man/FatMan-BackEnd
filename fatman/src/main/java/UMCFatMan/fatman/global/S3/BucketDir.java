package UMCFatMan.fatman.global.S3;

import lombok.Getter;

@Getter
public enum BucketDir {
    MONSTER("monster"),
    FATMAN("fatman");

    private String dirName;

    BucketDir(String dirName) {
        this.dirName = dirName;
    }
}

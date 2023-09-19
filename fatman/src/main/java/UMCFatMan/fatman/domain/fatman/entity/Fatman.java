package UMCFatMan.fatman.domain.fatman.entity;

import UMCFatMan.fatman.global.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="fatman")
public class Fatman extends BaseEntity {

    @Id
    @Column(name="id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column (name="name")
    private String name ;

    @Column(name = "fatman_image_url", nullable = false)
    private String fatmanImageUrl ;

    @Column(name = "cost")
    private int cost ;


    public void update(String name, String fatmanImageUrl, int cost) {
        this.name = name ;
        this.fatmanImageUrl = fatmanImageUrl;
        this.cost = cost;
    }

}

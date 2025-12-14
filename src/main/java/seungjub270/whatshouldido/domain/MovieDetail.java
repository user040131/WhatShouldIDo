package seungjub270.whatshouldido.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "movie_details")
public class MovieDetail {

    @Id
    private Long movieId;

    @Column(columnDefinition = "TEXT")
    private String overview;

    private String posterPath;

    // movie_vectors 테이블과 1:1 매핑 (MapsId 사용)
    @OneToOne
    @MapsId // movieId를 PK이자 FK로 씀
    @JoinColumn(name = "movie_id")
    private MovieVector movieVector;
}

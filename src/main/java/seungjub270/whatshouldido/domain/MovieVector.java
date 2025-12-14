package seungjub270.whatshouldido.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "movie_vectors")
public class MovieVector {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // 주요 카테고리 (데이터 타입 BigDecimal 사용)
    @Column(name = "drama_total") private BigDecimal dramaTotal;
    @Column(name = "action_total") private BigDecimal actionTotal;
    @Column(name = "imagination_total") private BigDecimal imaginationTotal;
    @Column(name = "humor_total") private BigDecimal humorTotal;
    @Column(name = "horror_total") private BigDecimal horrorTotal;
    @Column(name = "romance_total") private BigDecimal romanceTotal;
    @Column(name = "crime_total") private BigDecimal crimeTotal;
    @Column(name = "docu_total") private BigDecimal docuTotal;
    @Column(name = "music_total") private BigDecimal musicTotal;
    @Column(name = "war_total") private BigDecimal warTotal;

    @OneToOne(mappedBy = "movieVector", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MovieDetail movieDetail;

    public MovieDetail getMovieDetail() {
        return movieDetail;
    }
}
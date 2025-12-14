package seungjub270.whatshouldido.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "user_vectors")
public class UserVector {

    @Id
    private Long userId; // 실제 서비스에선 로그인 ID, 여기선 임시로 1번 고정

    private int ratedCount = 0;

    private BigDecimal dramaTotal = BigDecimal.ZERO;
    private BigDecimal actionTotal = BigDecimal.ZERO;
    private BigDecimal imaginationTotal = BigDecimal.ZERO;
    private BigDecimal humorTotal = BigDecimal.ZERO;
    private BigDecimal horrorTotal = BigDecimal.ZERO;
    private BigDecimal romanceTotal = BigDecimal.ZERO;
    private BigDecimal crimeTotal = BigDecimal.ZERO;
    private BigDecimal docuTotal = BigDecimal.ZERO;
    private BigDecimal musicTotal = BigDecimal.ZERO;
    private BigDecimal warTotal = BigDecimal.ZERO;

    public UserVector(Long userId) {
        this.userId = userId;
    }
}

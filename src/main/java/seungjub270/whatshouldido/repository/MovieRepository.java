package seungjub270.whatshouldido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import seungjub270.whatshouldido.domain.MovieVector;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieVector, Long> {
    // 랜덤으로 영화 목록을 가져오는 쿼리 (초기 선택용)
    @Query(value = "SELECT * FROM movie_vectors ORDER BY RAND() LIMIT 50", nativeQuery = true)
    List<MovieVector> findRandomMovies();

    @Query("SELECT m FROM MovieVector m JOIN FETCH m.movieDetail")
    List<MovieVector> findAll();

    List<MovieVector> findTop50ByOrderByIdAsc();
}

package seungjub270.whatshouldido.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import seungjub270.whatshouldido.domain.MovieVector;
import seungjub270.whatshouldido.domain.UserVector;
import seungjub270.whatshouldido.repository.MovieRepository;
import seungjub270.whatshouldido.repository.UserRepository;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    // 1. 초기 영화 목록 제공
    public List<MovieVector> getInitialMovies() {
        return movieRepository.findRandomMovies();
    }

    public List<MovieVector> getFiftyMovies() {
        return movieRepository.findTop50ByOrderByIdAsc();
    }

    // 님 TMDB API 키
    private final String API_KEY = System.getenv("TMDB_API_KEY");

    public String getPosterUrlByTitle(String title) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            // 1. TMDB 검색 API 호출 (한국어 요청)
            String url = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY
                    + "&query=" + title + "&language=ko-KR&page=1";

            Map response = restTemplate.getForObject(url, Map.class);
            List<Map> results = (List<Map>) response.get("results");

            // 2. 결과가 있으면 첫 번째 영화의 포스터 경로 반환
            if (results != null && !results.isEmpty()) {
                String posterPath = (String) results.get(0).get("poster_path");
                if (posterPath != null) {
                    return "https://image.tmdb.org/t/p/w500" + posterPath;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 3. 실패하거나 없으면 엑박 방지용 대체 이미지 리턴
        return "https://dummyimage.com/500x750/cccccc/000000&text=No+Image";
    }

    // 2. 초기 10개 선택 -> 유저 벡터 생성 (단순 평균)
    @Transactional
    public void initUserPreferences(Long userId, List<Long> selectedMovieIds) {
        List<MovieVector> movies = movieRepository.findAllById(selectedMovieIds);
        UserVector user = userRepository.findById(userId).orElse(new UserVector(userId));

        // 모든 값을 0으로 리셋 후 계산
        resetUser(user);

        for (MovieVector m : movies) {
            accumulate(user, m);
        }

        // 평균 나누기
        int count = movies.size();
        if (count > 0) {
            divideByCount(user, count);
            user.setRatedCount(count);
        }
        userRepository.save(user);
    }

    // 3. 추천 알고리즘 (유클리드 거리 계산 - 단순화 버전)
    @Transactional(readOnly = true)
    public List<MovieVector> recommendMovies(Long userId) {
        UserVector user = userRepository.findById(userId).orElseThrow();
        List<MovieVector> allMovies = movieRepository.findAll();

        // 거리 점수 계산 후 정렬 (거리가 짧을수록 유사함)
        return allMovies.stream()
                .sorted(Comparator.comparingDouble(m -> calculateDistance(user, m)))
                .limit(5) // 상위 5개만 추천
                .collect(Collectors.toList());
    }

    // 4. 피드백 루프: 좋아요 클릭 시 가중 평균 업데이트
    @Transactional
    public void updateUserPreference(Long userId, Long likedMovieId) {
        UserVector user = userRepository.findById(userId).orElseThrow();
        MovieVector movie = movieRepository.findById(likedMovieId).orElseThrow();

        // 공식: (기존값 * N + 새값) / (N + 1)
        int n = user.getRatedCount();
        user.setDramaTotal(calcWeightedAvg(user.getDramaTotal(), movie.getDramaTotal(), n));
        user.setActionTotal(calcWeightedAvg(user.getActionTotal(), movie.getActionTotal(), n));
        user.setImaginationTotal(calcWeightedAvg(user.getImaginationTotal(), movie.getImaginationTotal(), n));
        user.setHumorTotal(calcWeightedAvg(user.getHumorTotal(), movie.getHumorTotal(), n));
        user.setHorrorTotal(calcWeightedAvg(user.getHorrorTotal(), movie.getHorrorTotal(), n));
        user.setRomanceTotal(calcWeightedAvg(user.getRomanceTotal(), movie.getRomanceTotal(), n));
        user.setCrimeTotal(calcWeightedAvg(user.getCrimeTotal(), movie.getCrimeTotal(), n));
        user.setDocuTotal(calcWeightedAvg(user.getDocuTotal(), movie.getDocuTotal(), n));
        user.setMusicTotal(calcWeightedAvg(user.getMusicTotal(), movie.getMusicTotal(), n));
        user.setWarTotal(calcWeightedAvg(user.getWarTotal(), movie.getWarTotal(), n));

        user.setRatedCount(n + 1);
        userRepository.save(user);
    }

    // --- Helper Methods ---

    private double calculateDistance(UserVector u, MovieVector m) {
        // 유클리드 거리: sqrt( (u1-m1)^2 + (u2-m2)^2 + ... )
        double sum = 0;
        sum += Math.pow(u.getDramaTotal().subtract(m.getDramaTotal()).doubleValue(), 2);
        sum += Math.pow(u.getActionTotal().subtract(m.getActionTotal()).doubleValue(), 2);
        // ... 나머지 필드들도 동일하게 추가 ...
        // (지면상 생략, 실제 구현 시 모든 필드 포함해야 정확합니다)
        return Math.sqrt(sum);
    }

    private BigDecimal calcWeightedAvg(BigDecimal oldVal, BigDecimal newVal, int count) {
        // ((Old * Count) + New) / (Count + 1)
        BigDecimal totalOld = oldVal.multiply(new BigDecimal(count));
        return totalOld.add(newVal).divide(new BigDecimal(count + 1), MathContext.DECIMAL32);
    }

    private void accumulate(UserVector u, MovieVector m) {
        u.setDramaTotal(u.getDramaTotal().add(m.getDramaTotal()));
        u.setActionTotal(u.getActionTotal().add(m.getActionTotal()));
        // ... 나머지 필드들도 add ...
    }

    private void divideByCount(UserVector u, int count) {
        BigDecimal div = new BigDecimal(count);
        u.setDramaTotal(u.getDramaTotal().divide(div, MathContext.DECIMAL32));
        u.setActionTotal(u.getActionTotal().divide(div, MathContext.DECIMAL32));
        // ... 나머지 필드들도 divide ...
    }

    private void resetUser(UserVector u) {
        // 모든 필드 0으로 초기화 로직
    }
}

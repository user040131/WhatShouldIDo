package seungjub270.whatshouldido.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import seungjub270.whatshouldido.domain.MovieVector;
import seungjub270.whatshouldido.service.MovieService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class Movie {

    private final MovieService recService;
    private final Long TEMP_USER_ID = 1L; // 데모용 고정 ID

    public record MovieInfo(Long id, String title, String posterUrl) {
    }

    // 2. 취향 분석 시작 (영화 선택 화면)
    @GetMapping("/select")
    public String selectMovies(Model model) {
        List<MovieVector> initialMovies = recService.getFiftyMovies();
        List<MovieInfo> movies = initialMovies.stream()
                .map(movieVector -> {
                    String title = movieVector.getTitle();
                    String posterUrl = recService.getPosterUrlByTitle(title);
                    return new MovieInfo(movieVector.getId(), title, posterUrl);
                })
                .collect(Collectors.toList());
        model.addAttribute("movies", movies);

        return "select";
    }
    // 3. 선택 완료 처리
    @PostMapping("/analyze")
    public String analyze(@RequestParam("selectedIds") List<Long> selectedIds) {
        if (selectedIds.size() < 5) {
            return "redirect:/select?error=min5"; // 최소 5개 선택 유도
        }
        recService.initUserPreferences(TEMP_USER_ID, selectedIds);
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String showResult(Model model) {
        // 1. 추천 리스트 가져오기 (Service 호출)
        List<MovieVector> allMovies = recService.recommendMovies(1L); // 사용자 ID 임시 1

        if (allMovies.isEmpty()) {
            return "redirect:/";
        }

        // 2. 1등 영화 (Best Pick) 선정
        MovieVector bestMovie = allMovies.get(0);
        model.addAttribute("bestMovie", bestMovie);

        // ✅ [핵심] 1등 영화의 줄거리를 꺼내서 "overview"라는 이름으로 보냅니다.
        // 데이터가 없을 수도 있으니 null 체크를 해줍니다.
        String overviewText = "줄거리 정보가 없습니다.";

        if (bestMovie.getMovieDetail() != null && bestMovie.getMovieDetail().getOverview() != null) {
            overviewText = bestMovie.getMovieDetail().getOverview();
        }

        // 모델에 담기 (이제 HTML에서 ${overview} 로 쓸 수 있습니다)
        model.addAttribute("overview", overviewText);

        // 서비스 호출해서 URL 가져옴
        String posterUrl = recService.getPosterUrlByTitle(allMovies.get(0).getTitle());
        model.addAttribute("posterUrl", posterUrl);

        // ✅ [수정 파트] 2등~5등 영화 (나머지 4개) 처리
        // 데이터 개수가 부족할 경우를 대비해 안전하게 자릅니다.
        int endIndex = Math.min(allMovies.size(), 5);
        List<MovieVector> subMovies = allMovies.subList(1, endIndex); // 1번 인덱스부터 최대 4개

        // URL들도 리스트 하나에 담습니다.
        List<String> subPosters = new ArrayList<>();
        for (MovieVector movie : subMovies) {
            String url = recService.getPosterUrlByTitle(movie.getTitle());
            subPosters.add(url);
        }

        // 모델에 리스트 통째로 넘김
        model.addAttribute("subMovies", subMovies);   // 영화 정보 리스트 (제목 등)
        model.addAttribute("subPosters", subPosters); // 포스터 URL 리스트

        return "result";
    }

    // 5. 피드백 루프 (좋아요 누르면 취향 업데이트 후 다시 추천)
    @PostMapping("/feedback")
    public String feedback(@RequestParam("movieId") Long movieId) {
        recService.updateUserPreference(TEMP_USER_ID, movieId);
        return "redirect:/result"; // 새로고침 효과
    }
}

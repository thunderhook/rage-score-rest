package thunderhook.ragescore.score;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ScoreRepository extends CrudRepository<Score, Long> {

	List<Score> findByRoundId(Long roundId);

}

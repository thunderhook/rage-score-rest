package thunderhook.ragescore.round;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RoundRepository extends CrudRepository<Round, Long> {

	List<Round> findByGameId(Long gameId);
	
}

package thunderhook.ragescore.round;

import java.util.List;
import java.util.Optional;

public interface RoundService {

	void create(Long gameId, Round round);

	List<Round> findByGameId(Long gameId);

	Optional<Round> findById(Long id);

	void delete(Round round);

}

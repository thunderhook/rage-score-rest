package thunderhook.ragescore.game;

import java.util.Optional;

public interface GameService {

	Game create(Game game);

	Optional<Game> findById(Long id);

	void delete(Game game);

	void deleteById(Long id);

}

package thunderhook.ragescore.game;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class GameServiceImpl implements GameService {

	private final GameRepository gameRepository;

	@Autowired
	public GameServiceImpl(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	@Override
	public Game save(Game game) {
		return gameRepository.save(game);
	}

	@Override
	public Optional<Game> findById(Long id) {
		return gameRepository.findById(id);
	}

	@Override
	public void delete(Game game) {
		gameRepository.delete(game);
	}
	
	@Override
	public void deleteById(Long id) {
		gameRepository.deleteById(id);
	}

}

package thunderhook.ragescore.round;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import thunderhook.ragescore.game.Game;

@Service
class RoundServiceImpl implements RoundService {

	private final RoundRepository roundRepository;

	@Autowired
	public RoundServiceImpl(RoundRepository roundRepository) {
		this.roundRepository = roundRepository;
	}

	@Override
	public void create(Long gameId, Round round) {
		Game game = new Game();
		game.setId(gameId);
		round.setGame(game);
		roundRepository.save(round);
	}

	@Override
	public List<Round> findByGameId(Long gameId) {
		return roundRepository.findByGameId(gameId);
	}

	@Override
	public Optional<Round> findById(Long id) {
		return roundRepository.findById(id);
	}

	@Override
	public void delete(Round round) {
		roundRepository.delete(round);
	};

}

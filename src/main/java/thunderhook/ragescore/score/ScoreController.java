package thunderhook.ragescore.score;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import thunderhook.ragescore.common.BaseController;
import thunderhook.ragescore.round.Round;
import thunderhook.ragescore.round.RoundService;

@RestController
public class ScoreController extends BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(ScoreController.class);

	private final EntityManager entityManager;

	private final ScoreRepository scoreRepository;

	private final RoundService roundService;

	@Autowired
	public ScoreController(EntityManager entityManager, ScoreRepository scoreRepository,
			RoundService roundService) {
		this.entityManager = entityManager;
		this.scoreRepository = scoreRepository;
		this.roundService = roundService;
	}

	// FIXME
	@GetMapping(value = "/games/{gameId}/rounds/{roundId}/scores")
	public List<Score> findByGame(@PathVariable Long gameId, @PathVariable Long roundId) {
		return scoreRepository.findByRoundId(roundId);
	}

	@GetMapping(value = "/games/{gameId}/rounds/{roundId}/scores/{id}")
	public Score findByGame(@PathVariable Long gameId, @PathVariable Long roundId, @PathVariable Long id) {
		return scoreRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	@PostMapping("/games/{gameId}/rounds/{roundId}/scores")
	@Transactional
	public ResponseEntity<?> create(@PathVariable Long gameId, @PathVariable Long roundId, @RequestBody Score score) {
		if (score.getPlayer() != null && score.getPlayer().getId() != null) {
			score.setPlayer(entityManager.merge(score.getPlayer()));
		}

		Round round = roundService.findById(roundId).get();
		score.setRound(round);
		scoreRepository.save(score);
		LOG.info("Created Score {}", ReflectionToStringBuilder.toString(score));
		return ResponseEntity.created(buildCreatedLocation(score.getId())).build();
	}

	@DeleteMapping(value = "/games/{gameId}/rounds/{roundId}/scores/{id}")
	public ResponseEntity<?> delete(@PathVariable Long gameId, @PathVariable Long roundId, @PathVariable Long id) {
		Optional<Score> score = scoreRepository.findById(id);
		if (!score.isPresent()) {
			LOG.warn("Score with id {} does not exist", id);
			return ResponseEntity.notFound().build();
		}
		if (!score.get().getRound().getId().equals(roundId)) {
			LOG.warn("Score with id {} does not exist in round with id {}", id, gameId);
			return ResponseEntity.notFound().build();
		}
		if (!score.get().getRound().getGame().getId().equals(gameId)) {
			LOG.warn("Score with id {} does not exist in game with id {}", id, gameId);
			return ResponseEntity.notFound().build();
		}

		scoreRepository.delete(score.get());
		LOG.info("Deleted Score {}", ReflectionToStringBuilder.toString(score.get()));
		return ResponseEntity.noContent().build();
	}

}

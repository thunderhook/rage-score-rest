package thunderhook.ragescore.round;

import java.util.List;
import java.util.Optional;

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

@RestController
public class RoundController extends BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(RoundController.class);

	private final RoundService roundService;

	@Autowired
	public RoundController(RoundService roundService) {
		this.roundService = roundService;
	}
	
	@GetMapping(value = "/games/{gameId}/rounds")
	public List<Round> findByGame(@PathVariable Long gameId) {
		return roundService.findByGameId(gameId);
	}

	@PostMapping("/games/{gameId}/rounds")
	public ResponseEntity<?> create(@PathVariable Long gameId, @RequestBody Round round) {
		roundService.create(gameId, round);
		LOG.info("Created Round {}", ReflectionToStringBuilder.toString(round));
		return ResponseEntity.created(buildCreatedLocation(round.getId())).build();
	}
	
	@DeleteMapping(value = "/games/{gameId}/rounds/{id}")
	public ResponseEntity<?> delete(@PathVariable Long gameId, @PathVariable Long id) {
		Optional<Round> round = roundService.findById(id);
		if (!round.isPresent()) {
			LOG.warn("Round with id {} does not exist", id);
			return ResponseEntity.notFound().build();
		}
		if (!round.get().getGame().getId().equals(gameId)) {
			LOG.warn("Round with id {} does not exist in game with id {}", id, gameId);
			return ResponseEntity.notFound().build();
		}

		roundService.delete(round.get());
		LOG.info("Deleted Round {}", ReflectionToStringBuilder.toString(round.get()));
		return ResponseEntity.noContent().build();
	}

}

package thunderhook.ragescore.game;

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
import thunderhook.ragescore.game.Game;
import thunderhook.ragescore.game.GameNotFoundException;
import thunderhook.ragescore.game.GameService;

@RestController
public class GameController extends BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(GameController.class);

	private final GameService gameService;

	@Autowired
	public GameController(GameService gameService) {
		this.gameService = gameService;
	}

	@GetMapping(value = "/games/{id}")
	public Game findById(@PathVariable Long id) {
		return gameService.findById(id).orElseThrow(GameNotFoundException::new);
	}

	@PostMapping("/games")
	public ResponseEntity<?> create(@RequestBody Game game) {
		gameService.createGame(game);
		LOG.info("Created game {}", ReflectionToStringBuilder.toString(game));
		return ResponseEntity.created(buildCreatedLocation(game.getId())).build();
	}

	@DeleteMapping(value = "/games/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Game> game = gameService.findById(id);
		if (!game.isPresent()) {
			LOG.warn("Game with id {} does not exist", id);
			return ResponseEntity.notFound().build();
		}

		gameService.delete(game.get());
		LOG.info("Deleted game {}", ReflectionToStringBuilder.toString(game.get()));
		return ResponseEntity.noContent().build();
	}

}

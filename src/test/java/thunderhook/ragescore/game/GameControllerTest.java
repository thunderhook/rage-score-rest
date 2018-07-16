package thunderhook.ragescore.game;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import thunderhook.ragescore.common.BaseControllerTest;

public class GameControllerTest extends BaseControllerTest {

	@Autowired
	private GameRepository gameRepository;

	protected void prepareData() {
		gameRepository.deleteAll();
	}

	@Test
	public void gameNotFound() throws Exception {
		mockMvc.perform(get("/games/1")).andExpect(status().isNotFound());
	}

	@Test
	public void createGame() throws Exception {
		Game game = new Game();
		game.setCreated(LocalDateTime.now());
		String content = json(game);
		mockMvc.perform(post("/games").content(content).contentType(contentType)).andExpect(status().isCreated());
	}

}

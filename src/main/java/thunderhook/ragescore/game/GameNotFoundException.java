package thunderhook.ragescore.game;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Game does not exist")
public class GameNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9196577308422653374L;

}

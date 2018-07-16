package thunderhook.ragescore.common;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Entity does not exist")
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9196577308422653374L;

}

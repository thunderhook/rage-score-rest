package thunderhook.ragescore;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public abstract class BaseController {

	protected <T> ResponseEntity<?> buildCreatedResponseEntity(T id) {
		URI location = buildCreatedLocation(id);
		return ResponseEntity.created(location).build();
	}

	protected <T> URI buildCreatedLocation(T id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

}
package thunderhook.ragescore.game;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import thunderhook.ragescore.round.Round;

@Entity
public class Game {

	@Id
	@GeneratedValue
	private Long id;

	private LocalDateTime created;

	@OneToMany(mappedBy = "game")
	private List<Round> rounds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public List<Round> getRounds() {
		return rounds;
	}

	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}

}

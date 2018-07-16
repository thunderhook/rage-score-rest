package thunderhook.ragescore.round;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import thunderhook.ragescore.game.Game;
import thunderhook.ragescore.score.Score;

@Entity
public class Round {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id")
	private Game game;

	private int number;

	private int numberOfCards;

	@JsonManagedReference
	@OneToMany(mappedBy = "round", cascade = CascadeType.ALL)
	private List<Score> scores;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	public int getNumberOfCards() {
		return numberOfCards;
	}

	public void setNumberOfCards(int numberOfCards) {
		this.numberOfCards = numberOfCards;
	}

}

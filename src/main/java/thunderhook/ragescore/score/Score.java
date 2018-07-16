package thunderhook.ragescore.score;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import thunderhook.ragescore.player.Player;
import thunderhook.ragescore.round.Round;

@Entity
public class Score {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "round_id")
	private Round round;

	@OneToOne(cascade = CascadeType.ALL)
	private Player player;

	private int value;

	private int numberOfMinus5;

	private int numberOfPlus5;

	private boolean customScore;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getNumberOfMinus5() {
		return numberOfMinus5;
	}

	public void setNumberOfMinus5(int numberOfMinus5) {
		this.numberOfMinus5 = numberOfMinus5;
	}

	public int getNumberOfPlus5() {
		return numberOfPlus5;
	}

	public void setNumberOfPlus5(int numberOfPlus5) {
		this.numberOfPlus5 = numberOfPlus5;
	}

	public boolean isCustomScore() {
		return customScore;
	}

	public void setCustomScore(boolean customScore) {
		this.customScore = customScore;
	}

}

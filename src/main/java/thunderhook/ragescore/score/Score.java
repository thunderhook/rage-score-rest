package thunderhook.ragescore.score;

import thunderhook.ragescore.player.Player;

public class Score {

	private Long id;

	private Player player;

	private int value;

	public Score() {
	}

	public Score(Player player, int value) {
		this.player = player;
		this.value = value;
	}

	public Score(Long id, Player player, int value) {
		this.id = id;
		this.player = player;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}

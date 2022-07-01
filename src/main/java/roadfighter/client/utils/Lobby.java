package roadfighter.client.utils;

import java.util.Objects;

public class Lobby {
	
	private int id;
	private String name;
	private int currentPlayers;
	private int maxPlayers;
	private String currentPlayersString;
	private boolean playing;
	
	public Lobby(int id, String name, int maxPlayers) {
		this.id = id;
		this.name = name;
		this.currentPlayers = 0;
		this.maxPlayers = maxPlayers;
		playing = false;
		currentPlayersString = currentPlayers + "/" + maxPlayers;
	}

	public int getCurrentPlayers() {
		return currentPlayers;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lobby other = (Lobby) obj;
		return id == other.id;
	}

	public void setCurrentPlayers(int currentPlayers) {
		this.currentPlayers = currentPlayers;
		currentPlayersString = currentPlayers + "/" + maxPlayers;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public String getCurrentPlayersString() {
		return currentPlayersString;
	}
}

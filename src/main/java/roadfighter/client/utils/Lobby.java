package roadfighter.client.utils;

public class Lobby {
	
	private String name;
	private int currentPlayers;
	private int maxPlayers;
	private String currentPlayersString;
	private boolean playing;
	
	public Lobby(String name, int maxPlayers) {
		this.name = name;
		this.currentPlayers = 0;
		this.maxPlayers = maxPlayers;
		playing = false;
		currentPlayersString = currentPlayers + "/" + maxPlayers;
	}

	public int getCurrentPlayers() {
		return currentPlayers;
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

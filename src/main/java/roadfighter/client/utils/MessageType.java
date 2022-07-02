package roadfighter.client.utils;

import com.google.gson.annotations.SerializedName;

//los @SerializedName son para que cuando lo convierta en json le meta ese valor asi sale igual de los 2 lados
public enum MessageType {
	@SerializedName("0") SESSION_LOGIN,
	@SerializedName("1") SESSION_LOGOUT,
	@SerializedName("2") LOBBY_NEW,
	@SerializedName("3") LOBBY_JOIN,
	@SerializedName("4") LOBBY_CONTROL,
	@SerializedName("5") LOBBY_QUIT,
	@SerializedName("6") LOBBY_CHAT,
	@SerializedName("7") PLAYER_MOVE,
	@SerializedName("8") PLAYER_STOP,
	@SerializedName("9") PLAYER_OTHER,
	@SerializedName("10") PLAYER_STATE,
	@SerializedName("11") ENEMY_NEW,
	@SerializedName("12") ENEMY_DESTROY,
	@SerializedName("13") ITEM_NEW
}

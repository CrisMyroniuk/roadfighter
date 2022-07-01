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
	@SerializedName("7") UP,
	@SerializedName("8") LEFT,
	@SerializedName("9") RIGHT,
	@SerializedName("10") DOWN,
	@SerializedName("11") NO_UP,
	@SerializedName("12") NO_LEFT,
	@SerializedName("13") NO_RIGHT,
	@SerializedName("14") NO_DOWN
	
}

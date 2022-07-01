package roadfighter.client.utils;

import com.google.gson.Gson;

public class Message {
	private final MessageType type;
	private final String content;
	
	public Message(MessageType type, String content) {
		this.type = type;
		this.content = content;
	}
	
	public MessageType getType() {
		return type;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this, Message.class);
	}
}

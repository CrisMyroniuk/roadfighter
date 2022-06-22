package roadfighter.server.utils;

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
}

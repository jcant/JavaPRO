package ua.kiev.prog;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {
	@Id
	@GeneratedValue
	private long id;

	@Column(name = "msg_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date date;

	@Column(name = "msg_from")
	private String from;

	@Column(name = "msg_text")
	private String text;

	public MessageDTO toDTO() {
		return new MessageDTO(date, from, text);
	}

	public static Message fromDTO(MessageDTO dto) {
		return new Message(dto.getDate(), dto.getFrom(), dto.getText());
	}

	public Message() {}

	public Message(Date date, String from, String text) {
		this.date = date;
		this.from = from;
		this.text = text;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("[").append(date.toString())
				.append(", From: ").append(from)
				.append("] ").append(text).toString();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}

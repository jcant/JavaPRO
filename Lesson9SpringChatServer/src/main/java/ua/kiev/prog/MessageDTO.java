package ua.kiev.prog;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class MessageDTO {
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;

    private String from;
    private String text;

    public MessageDTO() {}

    public MessageDTO(Date date, String from, String text) {
        this.date = date;
        this.from = from;
        this.text = text;
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
}

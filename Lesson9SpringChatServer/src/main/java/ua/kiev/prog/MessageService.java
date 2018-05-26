package ua.kiev.prog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageDAO messageDAO;

    @Transactional
    public void addMessage(MessageDTO message) {
        Message msg = Message.fromDTO(message);
        messageDAO.save(msg);
    }

    @Transactional(readOnly = true)
    public List<MessageDTO> findByDate(Date from) {
        List<Message> messages = messageDAO.findByDate(from);

        List<MessageDTO> res = new ArrayList<>();
        for (Message m : messages) {
            res.add(m.toDTO());
        }

        return res;
    }
}

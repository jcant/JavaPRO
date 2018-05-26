package ua.kiev.prog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MessageDAO extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m where m.date > :fromDate ORDER BY m.date ASC")
    List<Message> findByDate(@Param("fromDate") Date from);
}

package ua.kiev.prog;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name="Courses")
@NamedQueries({
        @NamedQuery(name="Course.findAll", query = "SELECT c FROM Course c"),
        @NamedQuery(name="Course.findByName", query = "SELECT c FROM Course c WHERE c.name = :name")
})
public class Course {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Transient
    private String note;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    List<Client> clients = new ArrayList<>();

    public Course() {}

    public Course(String name) {
        this.name = name;
    }

    public void addClient(Client client) {
        if ( ! clients.contains(client))
            clients.add(client);
        if ( ! client.courses.contains(this))
            client.courses.add(this);
    }

    public List<Client> getClients() {
        return Collections.unmodifiableList(clients);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

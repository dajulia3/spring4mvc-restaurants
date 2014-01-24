package welp.users;

import javax.persistence.*;

@Entity
public class User {
    @Id @GeneratedValue private long id;
    private String name;

    protected User() {} //Need this for JPA

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, name='%s']",
                id, name);
    }
}

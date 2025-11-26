package treptowkolleg.tests.entity;

import treptowkolleg.model.orm.Column;
import treptowkolleg.model.orm.Entity;
import treptowkolleg.model.orm.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    private Integer id;
    @Column(nullable = true)
    private String username;
    @Column
    private String password;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package ru.lab6.common.user;

import ru.lab6.common.humanbeing.HumanBeing;

import javax.persistence.*;
import java.io.Serializable;

@SequenceGenerator(name = "u_ids", sequenceName = "user_ids")
@Entity
@Table(name = "users")
@NamedQuery(name = "Get_by_login", query = "SELECT u FROM User u WHERE u.login = :login")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "u_ids")
    private int id;
    private String login;
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "humanbeing_id")
    private HumanBeing humanBeing;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package cn.edu.sdut.softlab.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * The persistent class for the admin database table.
 *
 */
@Entity
@Table(name = "admin")
@NamedQueries({
    @NamedQuery(name = "Admin.findAll", query = "SELECT a FROM Admin a")
    ,
                @NamedQuery(name = "Admin.findById", query = "SELECT a FROM Admin a WHERE a.id = :id")
    ,
                @NamedQuery(name = "Admin.findByIdAndPassword", query = "SELECT a FROM Admin a WHERE a.id = :id and a.password = :password")
    ,
                @NamedQuery(name = "Admin.findByNameAndPassword", query = "SELECT a FROM Admin a WHERE a.name = :name and a.password = :password")
})
public class Admin implements Serializable, User {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ADMIN_ID_GENERATOR")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ADMIN_ID_GENERATOR")
    private int id;

    @Size(max = 20)
    private String email;

    @Size(max = 20)
    private String name;

    @Size(max = 16)
    private String password;

    //@Transient
    public String level;

    public Admin() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String getLevel() {
        return this.level;
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", level=" + level + '}';
    }

}

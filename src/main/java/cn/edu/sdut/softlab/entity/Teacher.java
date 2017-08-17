package cn.edu.sdut.softlab.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;
import javax.validation.constraints.Size;

/**
 * The persistent class for the teacher database table.
 *
 */
@Entity
@Table(name = "teacher")
@NamedQueries({
    @NamedQuery(name = "Teacher.findAll", query = "SELECT t FROM Teacher t"),
    @NamedQuery(name = "Teacher.findByTeaNOAndPassword", query = "SELECT t FROM Teacher t WHERE t.teacherNum = :teaNO and t.password = :password"),
    @NamedQuery(name = "Teacher.findByTeaNO", query = "SELECT t FROM Teacher t WHERE t.teacherNum = :teaNO"),
    @NamedQuery(name = "Teacher.findById", query = "SELECT t FROM Teacher t WHERE t.id = :id"),
    @NamedQuery(name = "Teacher.findByName", query = "SELECT t FROM Teacher t WHERE t.name = :name")})
public class Teacher implements Serializable, User {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TEACHER_ID_GENERATOR")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "TEACHER_ID_GENERATOR")
    private int id;

    @Column(name = "id_card")
    @Size(max = 18)
    private String idCard;

    @Size(max = 20)
    private String name;

    @Size(max = 16)
    private String password;

    @Column(name = "teacher_num")
    private BigInteger teacherNum;

    private BigInteger tel;

    //bi-directional many-to-one association to Team
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private Set<Team> teams;

    //@Transient
    public String level;

    public Teacher() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdCard() {
        return this.idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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

    public BigInteger getTeacherNum() {
        return this.teacherNum;
    }

    public void setTeacherNum(BigInteger teacherNum) {
        this.teacherNum = teacherNum;
    }

    public BigInteger getTel() {
        return this.tel;
    }

    public void setTel(BigInteger tel) {
        this.tel = tel;
    }

    public Set<Team> getTeams() {
        return this.teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Team addTeam(Team team) {
        getTeams().add(team);
        team.setTeacher(this);

        return team;
    }

    public Team removeTeam(Team team) {
        getTeams().remove(team);
        team.setTeacher(null);

        return team;
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
    public boolean equals(Object other) {

        if (this == other) { // 先检查是否其自反性，后比较 obj 是否为空。这样效率高
            return true;
        }

        if (other == null) {
            return false;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        final Student s = (Student) other;

        if (teacherNum == null) {

            if (s.getStudentNum() != null) {
                return false;
            }

//		} else if (!studentNum.equals(s.getId())) {
        } else if (!teacherNum.equals(s.getStudentNum())) {
            return false;
        }

        if (name == null) {

            if (s.getName() != null) {
                return false;
            }

        } else if (!name.equals(s.getName())) {
            return false;
        }

        //几项检查都没问题 返回true
        return true;
    }

    @Override
    public int hashCode() {  //hashCode主要是用来提高hash系统的查询效率。当hashCode中不进行任何操作时，可以直接让其返回 一常数，或者不进行重写。
        final int prime = 29;
        int result = 1;
        result = prime * result + ((teacherNum == null) ? 0 : teacherNum.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Teacher{" + "id=" + id + ", idCard=" + idCard + ", name=" + name + ", password="
                + password + ", teacherNum=" + teacherNum + ", tel=" + tel + ", level=" + level + '}';
    }

}

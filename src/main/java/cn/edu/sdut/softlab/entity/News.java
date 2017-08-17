package cn.edu.sdut.softlab.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import javax.validation.constraints.Size;

/**
 * The persistent class for the information database table.
 *
 */
@Entity
@Table(name = "news")
@NamedQueries({
    @NamedQuery(name = "News.findAll", query = "SELECT n FROM News n")
    ,
                @NamedQuery(name = "News.findById", query = "SELECT n FROM News n WHERE n.id = :id")
    ,
                @NamedQuery(name = "News.findByStuId", query = "SELECT n FROM News n WHERE n.student.id = :stu_id")
    ,
                @NamedQuery(name = "News.findByStatus", query = "SELECT n FROM News n WHERE n.status = :status")})
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "INFORMATION_ID_GENERATOR")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "INFORMATION_ID_GENERATOR")
    private int id;

    @Size(max = 200)
    private String content;

    private String level;

    @Size(max = 20)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date starttime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endtime;

    @Size(max = 50)
    private String title;

    //bi-directional many-to-one association to Student
    @ManyToOne
    private Student student;

    public News() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "News{" + "id=" + id + ", content=" + content + ", level=" + level + ", status=" + status + ", starttime=" + starttime + ", endtime=" + endtime + ", title=" + title + ", student=" + student + '}';
    }

}

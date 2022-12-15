package redSocial.model.DataObject;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Post {
    protected User user;
    protected int id;
    protected Date dateCreate;
    protected Date dateUpdate;
    protected String text;
    protected List<Comment> comments;
    protected Set<User> likes;

    public Post() {
        this.likes= new HashSet<>();
    }

    public Post(int id) {
        this.id = id;
        this.likes= new HashSet<>();
    }

    public Post(User user, int id) {
        this.user = user;
        this.id = id;
        this.likes= new HashSet<>();
    }

    public Post(User user) {
        this.user = user;
        this.likes= new HashSet<>();
    }

    public Post(User user, int id, Date dateCreate, Date dateUpdate, String text) {
        this.user = user;
        this.id = id;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
        this.text = text;
        this.likes= new HashSet<>();
    }

    public Post(User user, int id, Date dateCreate, Date dateUpdate, String text, List<Comment> comments, Set<User> likes) {
        this.user = user;
        this.id = id;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
        this.text = text;
        this.comments = comments;
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userName=" + user +
                ", id=" + id +
                ", dateCreate=" + dateCreate +
                ", dateUpdate=" + dateUpdate +
                ", text='" + text + '\'' +
                ", comments=" + comments +
                ", likes=" + likes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

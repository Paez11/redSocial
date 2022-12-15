package redSocial.model.DataObject;

import java.util.Date;
import java.util.Objects;

public class Comment {
    protected int id;
    protected User user;
    protected String textComment;
    protected Post post;
    protected Date date;

    public Comment(int id, User user, String textComment, Post post, Date date) {
        this.id = id;
        this.user = user;
        this.textComment = textComment;
        this.post = post;
        this.date = date;
    }

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextComment() {
        return textComment;
    }

    public void setTextComment(String textComment) {
        this.textComment = textComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", UserComment=" + user +
                ", textComment='" + textComment + '\'' +
                ", post=" + post +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && Objects.equals(user, comment.user) && Objects.equals(post, comment.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, post);
    }
}

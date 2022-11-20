package redSocial.model;

import java.util.Objects;

public class Comment {
    protected int id;
    protected User UserComment;
    protected String textComment;
    protected Post post;

    public Comment(int id, User userComment, String textComment, Post post) {
        this.id = id;
        this.UserComment = userComment;
        this.textComment = textComment;
        this.post = post;
    }

    public Comment() {
    }

    public Comment(int id) {
        this.id = id;
    }

    public Comment(int id, Post post) {
        this.id = id;
        this.post = post;
    }

    public Comment(int id, User userComment, Post post) {
        this.id = id;
        UserComment = userComment;
        this.post = post;
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

    public User getUserComment() {
        return UserComment;
    }

    public void setUserComment(User userComment) {
        UserComment = userComment;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && Objects.equals(UserComment, comment.UserComment) && Objects.equals(post, comment.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, UserComment, post);
    }
}

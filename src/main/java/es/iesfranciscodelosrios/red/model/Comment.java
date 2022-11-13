package es.iesfranciscodelosrios.red.model;

import java.util.Objects;

public class Comment {
    private String UserComment;
    private Post post;

    public String getUserComment() {
        return UserComment;
    }

    public void setUserComment(String userComment) {
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
        return Objects.equals(post, comment.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(post);
    }
}

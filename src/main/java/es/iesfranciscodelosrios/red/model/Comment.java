package es.iesfranciscodelosrios.red.model;

import java.util.Objects;

public class Comment {
	private int id;
	private User userComment;
    private String textComment;
    private Post post;

	public Comment(int id, User userComment, String textComment, Post post) {
		this.id = id;
		this.userComment = userComment;
		this.textComment = textComment;
		this.post = post;
	}
	public Comment(User userComment, String textComment, Post post) {
		this.id = -1;
		this.userComment = userComment;
		this.textComment = textComment;
		this.post = post;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public User getUserComment() {
		return userComment;
	}

	public void setUserComment(User userComment) {
		this.userComment = userComment;
	}

	public String getTextComment() {
		return textComment;
	}

	public void setTextComment(String textComment) {
		this.textComment = textComment;
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

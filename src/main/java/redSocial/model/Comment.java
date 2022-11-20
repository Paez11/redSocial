package redSocial.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comment {
	protected int id;
	protected User userComment;
	protected String textComment;
	protected Post post;
	protected LocalDateTime time;

	public Comment(int id, User userComment, String textComment, Post post,String time) {
		this.id = id;
		this.userComment = userComment;
		this.textComment = textComment;
		this.post = post;
		this.time=LocalDateTime.parse(time);
	}
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
		time = LocalDateTime.now();
	}
	
	

	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
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

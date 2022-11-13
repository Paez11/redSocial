package es.iesfranciscodelosrios.red.model;

import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String password;
    private String avatar;
    private List<Post> posts;
    private List<Comment> comments;
    private List<Post> likes;
    private List<User> followed;
    private List<User> follower;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Post> getLikes() {
        return likes;
    }

    public void setLikes(List<Post> likes) {
        this.likes = likes;
    }

    public List<User> getFollowed() {
        return followed;
    }

    public void setFollowed(List<User> followed) {
        this.followed = followed;
    }

    public List<User> getFollower() {
        return follower;
    }

    public void setFollower(List<User> follower) {
        this.follower = follower;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", posts=" + posts +
                ", comments=" + comments +
                ", likes=" + likes +
                ", followed=" + followed +
                ", follower=" + follower +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

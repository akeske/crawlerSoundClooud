package org.jsoup.Main;

/**
 * Created by akeske
 */
public class UserInfo {

    int id;
    String full_name;
    String description;
    String country;
    String city;
    String username;
    int track_count;
    int public_favorites_count;
    int followers_count;
    int followings_count;
    int likes_count;
    int comments_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTrack_count() {
        return track_count;
    }

    public void setTrack_count(int track_count) {
        this.track_count = track_count;
    }

    public int getPublic_favorites_count() {
        return public_favorites_count;
    }

    public void setPublic_favorites_count(int public_favorites_count) {
        this.public_favorites_count = public_favorites_count;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public int getFollowings_count() {
        return followings_count;
    }

    public void setFollowings_count(int followings_count) {
        this.followings_count = followings_count;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", full_name='" + full_name + '\'' +
                ", description='" + description + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", username='" + username + '\'' +
                ", track_count=" + track_count +
                ", public_favorites_count=" + public_favorites_count +
                ", followers_count=" + followers_count +
                ", followings_count=" + followings_count +
                ", likes_count=" + likes_count +
                ", comments_count=" + comments_count +
                '}';
    }
}

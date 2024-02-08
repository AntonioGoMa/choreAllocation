package util.users;

public class User {

    private final int id;
    private String username;
    private int points;
    private String badge;

    public User(int id, String username, int points, String badge) {
        this.id = id;

        this.setUsername(username);
        this.setPoints(points);
        this.setBadge(badge);
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getPoints() {
        return points;
    }

    public String getBadge() {
        return badge;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }
}

package util.enums;

public enum BadgeEnum {

    BRONZE("BRONZE", 20),
    SILVER("SILVER", 100),
    GOLD("GOLD", 200),
    DIAMOND("DIAMOND", 500);

    private final String badgeName;
    private final int pointsRequired;

    private BadgeEnum(String badgeName, int pointsRequired) {
        this.badgeName = badgeName;
        this.pointsRequired = pointsRequired;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public int getPointsRequired() {
        return pointsRequired;
    }
}

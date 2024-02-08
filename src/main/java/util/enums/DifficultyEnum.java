package util.enums;

import java.awt.*;

public enum DifficultyEnum {

    EASY("EASY", Color.GREEN, 100),
    MEDIUM("MEDIUM", Color.YELLOW, 250),
    HARD("HARD", Color.ORANGE, 500),
    IMPOSSIBLE("IMPOSSIBLE", Color.RED, 1000);

    private final String name;
    private final Color color;
    private final int rewardedPoints;

    private DifficultyEnum(String name, Color color, int rewardedPoints) {
        this.name = name;
        this.color = color;
        this.rewardedPoints = rewardedPoints;
    }


    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getRewardedPoints() {
        return rewardedPoints;
    }
}

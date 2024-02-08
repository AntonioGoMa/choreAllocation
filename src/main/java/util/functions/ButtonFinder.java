package util.functions;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ButtonFinder {

    public static List<JButton> findSpecificColorButton(Component component, Color color) {

        List<JButton> matching = new ArrayList<>();

        if (component instanceof JButton button) {
            if (button.getBackground().equals(color)) {
                matching.add(button);
            }
        }

        if (component instanceof Container container) {
            Component[] components = container.getComponents();

            for (Component c : components) {
                matching.addAll(findSpecificColorButton(c, color));
            }
        }

        return matching;
    }

}

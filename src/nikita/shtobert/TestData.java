package nikita.shtobert;
import java.awt.*;
import java.util.ArrayList;

public class TestData
{
    static final Color color = Color.BLUE;

    static volatile boolean go = true;

    static final double speed = 0.2;
    static final int radius = 20;
    static final int repaint = 20;
    static final double delta = 2.0;

    static long counter = 0;

    static volatile double x = 500;
    static volatile double y = 500;
    static volatile int click_x = 500;
    static volatile int click_y = 500;
    static volatile double dx = 0.0;
    static volatile double dy = 0.0;

    static int width = 1000;
    static int height = 1000;

    static final int min_radius = 20;
    static final int max_radius = 50;

    static volatile int points = 0;
    static volatile int life = 10;
    static volatile int spawn = 300;

    static final double min_speed = 0.1;
    static final double max_speed = 0.25;

    static volatile ArrayList<TestBot> bots = new ArrayList<>();
}

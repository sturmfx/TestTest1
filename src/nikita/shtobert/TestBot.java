package nikita.shtobert;

import java.awt.*;
import java.util.Random;

public class TestBot
{
    private static Color[] colors = {Color.GREEN, Color.RED, Color.YELLOW};
    private static Random random = new Random();

    boolean alive = true;

    final int radius;
    final double speed;
    final Color color;

    double x;
    double y;

    final double dx;
    final double dy;

    private double t_x;
    private double t_y;

    public TestBot(double s, int r)
    {
        int a = random.nextInt(4);

        radius = r;
        speed = s;
        color = colors[random.nextInt(colors.length)];

        switch(a)
        {
            case 0:
                x = random.nextInt(TestData.width + 1);
                y = 0;

                t_x = random.nextInt(TestData.width + 1);
                t_y = TestData.height * 0.5 + random.nextInt((int) (TestData.height * 0.5));
                break;
            case 1:
                x = TestData.width;
                y = random.nextInt(TestData.height + 1);

                t_x = random.nextInt((int) (TestData.width * 0.5));
                t_y = random.nextInt(TestData.height + 1);
                break;
            case 2:
                x = random.nextInt(TestData.width + 1);
                y = TestData.height;

                t_x = random.nextInt(TestData.width + 1);
                t_y = random.nextInt((int) (TestData.height * 0.5));
                break;
            case 3:
                x = 0;
                y = random.nextInt(TestData.height);

                t_x = TestData.width * 0.5 + random.nextInt((int) (TestData.width * 0.5));
                t_y = random.nextInt(TestData.height);
                break;
        }

        double temp_dx = t_x - x;
        double temp_dy = t_y - y;

        double temp_c = Math.sqrt(temp_dx * temp_dx + temp_dy * temp_dy);
        double k = temp_c / speed;

        dx = temp_dx / k;
        dy = temp_dy / k;
    }

    public void update()
    {
        boolean b1 = x + dx > 0;
        boolean b2 = x + dx < TestData.width + 1;
        boolean b3 = y + dy > 0;
        boolean b4 = y + dy < TestData.height + 1;

        boolean b5 = Math.sqrt((x - TestData.x) * (x - TestData.x) + (y - TestData.y) * (y - TestData.y)) < radius + TestData.radius;

        if(b1 && b2 && b3 && b4)
        {
            x += dx;
            y += dy;

            if(b5)
            {
                alive = false;
                TestData.life --;

                if(TestData.life < 1)
                {
                    TestData.go = false;
                }
            }
        }
        else
        {
            alive  = false;
            TestData.points ++;
        }
    }
}

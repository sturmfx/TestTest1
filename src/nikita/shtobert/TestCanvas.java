package nikita.shtobert;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class TestCanvas extends JPanel implements MouseMotionListener
{
    int width;
    int height;
    public TestCanvas(int width, int height)
    {
        super();
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(this.width, this.height));
    }

    private void doDraw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(TestData.color);
        g2d.fillOval((int)(TestData.x - TestData.radius), (int)(TestData.y - TestData.radius), 2 * TestData.radius, 2 * TestData.radius);

        synchronized(TestData.bots)
        {
            for (int i = 0; i < TestData.bots.size(); i++)
            {
                g2d.setColor(TestData.bots.get(i).color);
                g2d.fillOval((int)(TestData.bots.get(i).x - TestData.bots.get(i).radius), (int)(TestData.bots.get(i).y - TestData.bots.get(i).radius), 2 * TestData.bots.get(i).radius, 2 * TestData.bots.get(i).radius);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        doDraw(g);
    }

    @Override
    public void mouseDragged(MouseEvent a)
    {

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        TestData.click_x = x;
        TestData.click_y = y;
        double temp_dx = x - TestData.x;
        double temp_dy = y - TestData.y;
        double temp_c = Math.sqrt(temp_dx * temp_dx + temp_dy * temp_dy);

        double k = temp_c / TestData.speed;

        TestData.dx = temp_dx / k;
        TestData.dy = temp_dy / k;
    }


}

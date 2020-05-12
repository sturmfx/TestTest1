package nikita.shtobert;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.TimerTask;

public class TestClient extends JFrame implements KeyListener
{
    TestCanvas canvas;
    java.util.Timer timer;
    Random random = new Random();
    public TestClient(int width, int height)
    {
        super();
        TestData.width = width;
        TestData.height = height;
        TestData.x = width / 2;
        TestData.y = height / 2;
        initUI();
        initAC();
    }

    private void initUI()
    {
        canvas = new TestCanvas(TestData.width, TestData.height);
        canvas.setDoubleBuffered(true);
        add(canvas);
        pack();
        setVisible(true);
        setTitle("TEST EXAMPLE GAME");
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
    }

    private void initAC()
    {
        timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                if(TestData.go)
                {
                    TestData.counter++;

                    update();
                    remove();

                    if (TestData.counter % TestData.repaint == 0)
                    {
                        repaint();
                    }

                    if (TestData.counter % TestData.spawn == 0)
                    {
                        synchronized (TestData.bots)
                        {
                            TestData.bots.add(new TestBot(TestData.min_speed + (TestData.max_speed - TestData.min_speed) * random.nextDouble(), TestData.min_radius + random.nextInt(TestData.max_radius)));
                        }
                    }

                    setTitle("LIVES LEFT: " + TestData.life + "     POINTS: " + TestData.points);
                }
                else
                {
                    repaint();
                }

            }
        }, 0, 1);

        canvas.addMouseMotionListener(canvas);
    }

    private void update()
    {
        boolean b1 = TestData.x + TestData.dx > 0;
        boolean b2 = TestData.x + TestData.dx < TestData.width;
        boolean b3 = TestData.y + TestData.dy > 0;
        boolean b4 = TestData.y + TestData.dy < TestData.height;

        if(b1 && b2 && b3 && b4)
        {
            if(Math.sqrt((TestData.click_x - TestData.x) * (TestData.click_x - TestData.x) + (TestData.click_y - TestData.y) * (TestData.click_y - TestData.y)) > TestData.delta)
            {
                TestData.x += TestData.dx;
                TestData.y += TestData.dy;
            }
        }

        synchronized(TestData.bots)
        {
            for (int i = 0; i < TestData.bots.size(); i++)
            {
                TestData.bots.get(i).update();
            }
        }

    }

    private void remove()
    {
        for(int i = 0; i < TestData.bots.size(); i++)
        {
            synchronized(TestData.bots)
            {
                if(!TestData.bots.get(i).alive)
                {
                    TestData.bots.remove(i);
                }
            }
        }
    }

    public static void main(String[] args)
    {
        TestClient client = new TestClient(1000, 1000);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

        if(e.getKeyChar() == 'r')
        {
            TestData.go = false;
            TestData.bots.clear();
            TestData.life = 10;
            TestData.points = 0;
            TestData.x = TestData.width / 2;
            TestData.y = TestData.height / 2;
            TestData.dx = 0.0;
            TestData.dy = 0.0;
            TestData.go = true;
            e.consume();
        }

        if(e.getKeyChar() == 't')
        {
            if(TestData.go == false)
            {
                if(TestData.life > 0)
                {
                    TestData.go = true;
                }
            }
            else
            {
                TestData.go = false;
            }
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

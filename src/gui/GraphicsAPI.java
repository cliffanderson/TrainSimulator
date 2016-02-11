package gui;

/**
 * Created by andersonc12 on 2/10/2016.
 */

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicsAPI {

    private BufferStrategy strategy;
    Canvas canvas = new Canvas();

    public GraphicsAPI(int x, int y, String title)
    {

        JFrame frame = new JFrame(title);
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(x, y));
        panel.setLayout(null);

        canvas.setBounds(0, 0, x, y);
        panel.add(canvas);

        canvas.setIgnoreRepaint(true);

        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.requestFocus();

        canvas.createBufferStrategy(2);
        strategy = canvas.getBufferStrategy();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		JFrame frame = new JFrame(title);
		canvas = new Canvas();

		canvas.setPreferredSize(new Dimension(x, y));

		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);

		canvas.setIgnoreRepaint(true);

		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		canvas.requestFocus();

		canvas.createBufferStrategy(3);
		strategy = canvas.getBufferStrategy();
		*/
    }


    public Graphics getGraphics() {
        return strategy.getDrawGraphics();
    }

    public void draw()
    {
        getGraphics().dispose();
        canvas.getBufferStrategy().show();
    }
	/*
	public static void main(String[] args) throws Exception
	{
		GraphicsAPI api = new GraphicsAPI(500,500, "Test");

		while(true)
		{
			Graphics g = api.getGraphics();

			for(int i = 0; i < 500; i += 40)
			{
				g.drawRect(i, i, 20, 20);
				g.fillRect(i + 20, i + 20, 20, 20);
			}


			api.draw();

			Thread.sleep(10);
		}
	}*/
}

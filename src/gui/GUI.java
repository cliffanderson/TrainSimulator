package gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

/**
 * Created by andersonc12 on 2/10/2016.
 */
public class GUI
{
    public static final int WIDTH = 1400;
    public static final int HEIGHT = 400;

    public static void main(String[] args) throws Exception{
        GraphicsAPI api = new GraphicsAPI(1400, 400, "Traim Simulator");

        Graphics g = api.getGraphics();
        Image station = ImageIO.read(new File("station.png"));
        g.drawImage(station, 0, 0, null);
        api.draw();


        Thread.sleep(10000);
    }

    public GUI(int stations) throws Exception
    {
        Image station = ImageIO.read(new File("station.png"));

        while(true)
        {
            //draw stations
            for(int i = 0; i < stations; i++)
            {

            }
            //draw train

            //draw any passengers

        }
    }
}

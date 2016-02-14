package src.gui;

import javax.imageio.ImageIO;



import src.Direction;
import src.Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by andersonc12 on 2/10/2016.
 */
public class GUI
{
    public static final int WIDTH = 1400;
    public static final int HEIGHT = 400;
    
    //boolean to keep track of when the gui is "busy" doing animations
    public static boolean ready = true;
    
    public static int trainAOffset = 0;
    public static int trainBOffset = 0;
    
    public static int lastTrainALocation = Main.trainA.getLocation();
    public static int lastTrainBLocation = Main.trainB.getLocation();

    public GUI(int stations) throws Exception
    {
        //load grass image and draw stations and tracks on it
        final Image background = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);

        final Image grassImage = ImageIO.read(new File("grass.jpg")).getScaledInstance(WIDTH, HEIGHT, Image.SCALE_FAST);
        final Image stationImage = ImageIO.read(new File("station.png"));
        final Image railImage = ImageIO.read(new File("rails.png"));
        final Image trainImage = ImageIO.read(new File("train.png")).getScaledInstance(100, 100, Image.SCALE_FAST);


        //draw grass on background
        background.getGraphics().drawImage(grassImage.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_FAST), 0, 0, null);

        //draw stations on background
        int pixelsBetweenStations = WIDTH / stations;
        for(int i = 0; i < stations; i++)
        {
            background.getGraphics().drawImage(stationImage.getScaledInstance(200, 100, Image.SCALE_FAST), pixelsBetweenStations * i + 50, 150, null);
        }

        //draw rails
        //track 1
        for(int i = 0; i < 10; i++)
        {
            background.getGraphics().drawImage(railImage.getScaledInstance(250, 50, Image.SCALE_FAST), i * 250, 50, null);
        }

        //track 2
        for(int i = 0; i < 10; i++)
        {
             background.getGraphics().drawImage(railImage.getScaledInstance(250, 50, Image.SCALE_FAST), i * 250, 300, null);
        }

        final GraphicsAPI api = new GraphicsAPI(WIDTH, HEIGHT, "Train Simulator 2016");

        //state variables
        boolean loadingPassengers = false;


        new Thread(){

            public void run()
            {
                while(true)
                {
                    Graphics g = api.getGraphics();

                    //draw background
                    g.drawImage(background, 0, 0, null);
                    
                    
                    //check if trains have moved. If they have, slowly increase offset and make GUI busy
                    if(lastTrainALocation != Main.trainA.getLocation())
                    {
                    	//train A has moved
                    	GUI.ready = false;
                    	trainAOffset += Main.trainA.getDirection() == Direction.LEFT ? -1 : 1;
                    }                   
                    
                    if(lastTrainBLocation != Main.trainB.getLocation())
                    {
                    	//train B has moved
                    	GUI.ready = false;
                    	trainBOffset += Main.trainB.getDirection() == Direction.LEFT ? -1 : 1;

                    }
                    
                    
                    
                    
                    //but if offset == LENGTH/stationCount, stop moving train
                    if(trainAOffset == WIDTH/stations)
                    {
                    	lastTrainALocation = Main.trainA.getLocation();
                    }
                    
                    if(trainBOffset == WIDTH/stations)
                    {
                    	lastTrainBLocation = Main.trainB.getLocation();
                    }
                    
                    
                    
                    
                    //if both trains haven't moved then set GUI to ready
                    if(lastTrainALocation == Main.trainA.getLocation() &&
                       lastTrainBLocation == Main.trainB.getLocation())
                    {
                    	GUI.ready = true;
                    }
                    	
                    
                    


                    //draw train
                    g.drawImage(trainImage, Main.trainA.getLocation() * (WIDTH/stations) + trainAOffset, 20, null);
                    //g.drawImage(trainImage, Main.trainB.getLocation() * (WIDTH/stations) + trainBOffset, 220, null);

                    //draw any passengers

                    //draw
                    api.draw();

                    //sleep
                    try {
                        Thread.sleep(20);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            }

        }.start();

    }
}

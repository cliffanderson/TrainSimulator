package main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import train.Direction;
import train.Passenger;
import train.Train;

import javax.swing.*;

public class Main
{
	//specify size of route
	static final int ROUTE_LENGTH = 5;
	static TrainRoute route;
   static Train trainA = new Train(0, 100);
    static Train trainB = new Train(ROUTE_LENGTH-1, 100);

    static Train[] trains = {trainA, trainB};
	static long moveSpeed=1000;
	public static void main(String[] args) throws Exception
	{
		System.out.println("Train Simulator 5000");

		//create two trains, passing the start location and capacity
		Train trainA = new Train(0, 100);
		Train trainB = new Train(ROUTE_LENGTH-1, 100);

		//Array to hold the trains
		//trains = {trainA, trainB};


		//create route
		route = new TrainRoute(ROUTE_LENGTH);		

		createPassengerGenerationThread();

        makeFrame();


		//primary loop
		while (true)
		{
			//loop through all trains
			for(int i = 0; i < 2; i++)
			{
				//get a train from array
				Train trainGeneric = trains[i];
                trainGeneric.stopped();
                Thread.sleep(500);
				//remove passengers who are at their stop
				removePassengersFromTrain(trainGeneric);
                Thread.sleep(500);
				//load people onto the train
				loadPassengersOnToTrain(trainGeneric);
                Thread.sleep(500);
                trainGeneric.move();
				//move trains along their tracks
				moveTrain(trainGeneric);
			}

			try
			{
				Thread.sleep(moveSpeed);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private static void moveTrain(Train trainGeneric)
	{
		// if headed right
		if (trainGeneric.getDirection().equals(Direction.RIGHT))
		{
			if (trainGeneric.getLocation()==(ROUTE_LENGTH-1))
			{
				trainGeneric.setDirection(Direction.LEFT);
				trainGeneric.setLocation(trainGeneric.getLocation()-1);
			}
			else
			{
				trainGeneric.setLocation(trainGeneric.getLocation()+1);
			}
		}
		else //headed left
		{
			//if at end of track
			if (trainGeneric.getLocation()==(0))
			{
				trainGeneric.setDirection(Direction.RIGHT);
				trainGeneric.setLocation(trainGeneric.getLocation()+1);
			}
			else
			{
				trainGeneric.setLocation(trainGeneric.getLocation()-1);
			}
		}
	}

	private static void loadPassengersOnToTrain(Train train)
	{
		while(train.getSize() < train.getCapacity())
		{
			//while train has space board people onto train
			if(route.getStation(train.getLocation()).lineNotEmpty())
			{
				train.boardTrain(route.getStation(train.getLocation()).removeFirstInLine());
			}
			else
			{
				//no one left in line
				break;
			}
		}
	}

	private static void removePassengersFromTrain(Train train)
	{
		//take people off who are at their destination
		List<Passenger> toGetOff = new ArrayList<Passenger>();
		for(Passenger passenger : train.getPassengers())
		{
			if(passenger.getDestination() == train.getLocation())
			{
				//passenger is at destination so remove them from train
				toGetOff.add(passenger);
				//System.out.println("Passenger got off train " + i + " at station " + passenger.getDestination());
			}
		}

		//remove passengers from train
		for(Passenger p : toGetOff)
		{
			train.getPassengers().remove(p);
		}
	}

	private static void createPassengerGenerationThread()
	{
		//generate people to fill stations (different thread)
		//every 200 milliseconds (5 times per second) a person will
		//be placed in the queue at a random station
		new Thread()
		{
			public void run()
			{
				while(true)
				{
					//put someone in a queue at a random station
					int station = (int) (Math.random() * ROUTE_LENGTH);
					Station s = route.getStation(station);
					s.enqueuePassenger((new Passenger(route)));
					//System.out.println("Placed passenger at station: " + station);
					try
					{
						Thread.sleep(50);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

   // @Override
    public static void makeFrame() {
        Canvas canvas = new Canvas();
        JFrame frame = new JFrame("Train Simulator 5000");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas,BorderLayout.CENTER);

        frame.setSize(1500,500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas.setIgnoreRepaint(true);

        new Thread()
        {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    render(canvas, frame);
                }
            }
        }.start();
    }
    public static void render(Canvas canvas,JFrame frame) {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0,frame.getWidth(),frame.getHeight());

        g.setColor(Color.GREEN);
        //top line
        g.fillRect(95,210,1200,20);
        //bottom line
        g.fillRect(95,260,1200,20);




        for(int x=0;x<route.getNumStations();x++)
        {
            //the stations
            int xPos = (x*300)+50;
            int yPos=200;
            Station station = route.getStation(x);
            g.setColor(station.getColor());
            g.fillRect(xPos,yPos,90,90);


            //the people
            g.setColor(Color.MAGENTA);
            int mod=0;
            for(int y=0;y<station.queSize();y++)
            {
                if(y%15==0)
                {
                    mod++;
                }
                g.fillRect((xPos+((y-(mod*15))*6))+90,(yPos-5)-(mod*6),5,5);
            }

        }

        g.setColor(Color.RED);
       // Train t1=trains[0];
        int spot=0;
        for(Train t:trains) {

            if (t.isMoving()) {
                long currentTime = System.currentTimeMillis();
				t.changePos(3000/moveSpeed);
               // int xPos=((t.getLastLocation()*300)+80)-((int)(t.getDepartureTime()/currentTime))*300;
                //int xPos = ((int)(t.getDepartureTime()-currentTime))*300;
               // System.out.println(xPos);
				if(t.getDirection()==Direction.RIGHT)
				{
					g.fillRect(((int)t.getPos())+80+(t.getLastLocation()*300),210+spot,30,20);
				}
				else
				{
					g.fillRect(((int)t.getPos())+80+(t.getLocation()*300),210+spot,30,20);
				}

            } else {
                g.fillRect((t.getLocation() * 300) + 80, 210+spot, 30, 20);
            }
            spot=50;
        }

      //  g.fillRect((trains[1].getLocation()*300)+50,260,30,20);

        g.dispose();
        bs.show();
    }
}
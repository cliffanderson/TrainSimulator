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

    static Train[] trains = {new Train(0, 100), new Train(ROUTE_LENGTH-1, 100)};
	static long moveSpeed=1000*5;
	public static void main(String[] args) throws Exception
	{
		System.out.println("Train Simulator 5000");

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

                Thread.sleep(500);
				//remove passengers who are at their stop
				removePassengersFromTrain(trainGeneric);
                Thread.sleep(500);
				//load people onto the train
				loadPassengersOnToTrain(trainGeneric);
                Thread.sleep(500);
                trainGeneric.move();
                try
                {
                    Thread.sleep(moveSpeed);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                trainGeneric.stopped();
                //move trains along their tracks
				moveTrain(trainGeneric);
			}


		}
	}

	private static void moveTrain(Train trainGeneric)
	{
		// if headed right
		if (trainGeneric.getDirection().equals(Direction.OUTBOUND))
		{
			trainGeneric.setLocation(trainGeneric.getLocation()+1);

			if (trainGeneric.getLocation()==(ROUTE_LENGTH-1))
			{
				trainGeneric.setDirection(Direction.INBOUND);
			}
		}
		else //headed left
		{
			trainGeneric.setLocation(trainGeneric.getLocation()-1);

			//if at end of track
			if (trainGeneric.getLocation()==(0))
			{
				trainGeneric.setDirection(Direction.OUTBOUND);
			}
		}
	}

	private static void loadPassengersOnToTrain(Train train)
	{
		while(train.getSize() < train.getCapacity())
		{
			//while train has space board people onto train
			if(train.getDirection() == Direction.INBOUND) {
				if (!route.getStation(train.getLocation()).inboundEmpty()) {
					train.boardTrain(route.getStation(train.getLocation()).getFromInboundLine());
				} else {
					//no one left in line
					break;
				}
			}
			else
			{
				if (!route.getStation(train.getLocation()).outboundEmpty()) {
					train.boardTrain(route.getStation(train.getLocation()).getFromOutboundLine());
				} else {
					//no one left in line
					break;
				}
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
					s.enqueuePassenger((new Passenger(route, s)));
					//System.out.println("Placed passenger at station: " + station);
					try
					{
						Thread.sleep(90);
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
       final Canvas canvas = new Canvas();
        final JFrame frame = new JFrame("Train Simulator 5000");
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
            for(int y=0;y<station.inQueSize();y++)
            {
                if(y%6==0)
                {
                    mod++;
                }
				g.fillRect((xPos+((y-(mod*6))*6))+37,(yPos-5)-(mod*6),5,5);
            }
			mod=0;
			for(int y=0;y<station.outQueSize();y++)
			{
				if(y%6==0)
				{
					mod++;
				}
				g.fillRect((xPos+((y-(mod*6))*6))+90,(yPos-5)-(mod*6),5,5);
			}
        }

        //the trains
        g.setColor(Color.RED);
		int yOffset=0;
        int speed = (int)(300/(moveSpeed/100));
        for(int x=0;x<trains.length;x++)
        {
			if(x==1)
			{
				yOffset=50;
			}
            Train t = trains[x];
            if (t.isMoving()) {

                t.changePos(speed);
            }
            else
            {
                t.resetPos();
            }
            g.fillRect((t.getLocation() * 300) + 80+t.getXPosition(), 210+yOffset, 30, 20);
        }

        g.dispose();
        bs.show();
    }
}
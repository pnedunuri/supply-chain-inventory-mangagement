package edu.sjsu.CMPE281.Nodes;

import java.util.Random;

import edu.sjsu.CMPE281.Constants;

public class ControlNode
{
	private SensorNode[] sensorNodes = null;
	private int noOfSensorNodes = Constants.NO_OF_SENSOR_NODES / Constants.NO_OF_CONTROL_NODES;
	private long controlNodeId = -1;
	private String truckId = null;
	
	public void init()
	{
		controlNodeId = System.currentTimeMillis();
		sensorNodes = new SensorNode[noOfSensorNodes];

		for (int sIndex = 0; sIndex < noOfSensorNodes; sIndex++)
		{
			sensorNodes[sIndex] = new SensorNode();
			sensorNodes[sIndex].init();
		}
		
		// random truck-id
		Random r = new Random();
		truckId =  Constants.US_STATE_CODES[r.nextInt(Constants.US_STATE_CODES.length)] + r.nextInt(999) + " " + r.nextInt(999);   
	}
	
	public long getContolNodeId()
	{
		return controlNodeId;
	}
	
	public String getTruckId()
	{
		return truckId;
	}

	public SensorNode[] getSensorNodes()
	{
		return sensorNodes;
	}
	
	public Sensor getSensorById(long sensorId)
	{
		Sensor sensor = null;
		for (int sIndex = 0; sIndex < noOfSensorNodes; sIndex++)
		{
			if ((sensor = sensorNodes[sIndex].getSensorById(sensorId)) != null)
			{
				break;
			}
		}
		return sensor;
	}

	public void update(long time)
	{
		for (int sIndex = 0; sIndex < noOfSensorNodes; sIndex++)
		{
			sensorNodes[sIndex].update(time);
		}
	}
}
package edu.sjsu.CMPE281.Nodes;

import edu.sjsu.CMPE281.Constants;

public class SensorNode
{
	private Sensor[] sensors;
	private int noOfSensors = Constants.NO_OF_SENSORS / Constants.NO_OF_SENSOR_NODES;
	private long sensoreNodeId = -1;
	
	public void init()
	{
		sensoreNodeId = System.currentTimeMillis();
		sensors = new Sensor[noOfSensors];
		for (int index = 0; index < noOfSensors; index++)
		{
			sensors[index] = new Sensor();
			sensors[index].init();
		}
	}
	
	public long getSensorNodeId()
	{
		return sensoreNodeId;
	}
	
	public Sensor[] getSensors()
	{
		return sensors;
	}

	public Sensor getSensorById(long sensorId)
	{
		for (int index = 0; index < noOfSensors; index++)
		{
			if (sensors[index].getSensorId() == sensorId)
			{
				return sensors[index];
			}
		}

		return null;
	}

	public void update(long time)
	{
		for (int index = 0; index < noOfSensors; index++)
		{
			sensors[index].update(time);
		}
	}
}
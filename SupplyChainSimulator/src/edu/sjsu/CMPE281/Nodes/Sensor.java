package edu.sjsu.CMPE281.Nodes;

import java.sql.Timestamp;
import java.util.Random;

import edu.sjsu.CMPE281.Constants;

public class Sensor
{
	// sensor info
	private int sensorType = -1;
	private long sensorId = -1;
	private String sensorInfo = null;

	// latitude & longitude of truck
	private float truckLocationLatitude = 0.0f;
	private float truckLocationLongitude = 0.0f;

	// per second
	private float truckSpeed = 0.1f;

	// expected sensor data
	private String expectedSensorInfo = null;
	private int threshold = -1;

	// switch
	private boolean correctInfoSwitch = true;

	private int sensorInfoUpdateInterval = 1000;
	// timer
	private long timer = 0;

	private Random r = new Random();
	
	public void init()
	{
		sensorType = r.nextInt(Constants.TOTAL_NO_OF_SENSORS);
		sensorId = System.currentTimeMillis();

		// truck location init
		// the below magic numbers are California's Latitude and Longitude
		truckLocationLatitude = r.nextFloat() * 30;
		truckLocationLongitude = r.nextFloat() * 100;

		truckSpeed = ((truckSpeed * sensorInfoUpdateInterval ) / Constants.SECOND_TO_MILLI);
	}

	public long getSensorId()
	{
		return sensorId;
	}

	public int getSensorType()
	{
		return sensorType;
	}
	
	public void setUpdateInterval(int time)
	{
		sensorInfoUpdateInterval = time;
	}

	public void setExpectedValue(int value)
	{
		expectedSensorInfo = value + "";
	}

	public void setThreshold(int value)
	{
		threshold = value;
	}

	public void setCorrectInfoSwitch(boolean value)
	{
		correctInfoSwitch = value;
	}

	public String getSensorData()
	{
		return sensorInfo;
	}

	public void update(long time)
	{
		timer += time;

		if ((timer - sensorInfoUpdateInterval) >= 0)
		{
			timer = time;

			switch (sensorType)
			{
				case Constants.TEMPERATURE_SENSOR:
				{
					int currTemp = Integer.parseInt(expectedSensorInfo) + r.nextInt(threshold);
					sensorInfo = correctInfoSwitch ? currTemp + "" : threshold + currTemp + "";
				}
					break;
				case Constants.WEIGHT_SENSOR:
				{
					int currWeight = Integer.parseInt(expectedSensorInfo) + r.nextInt(threshold);
					sensorInfo = correctInfoSwitch ? currWeight + "" : currWeight + threshold + "";
				}
					break;
				case Constants.GPS_SENSOR:
				{
					sensorInfo = truckLocationLatitude + truckSpeed + ", " + truckLocationLongitude + truckSpeed;
				}
					break;
			}
		}
	}
}
package edu.sjsu.CMPE281.ServerComponent;

import org.json.JSONObject;
import edu.sjsu.CMPE281.SupplyChainComponent;
import edu.sjsu.CMPE281.Nodes.ControlNode;
import edu.sjsu.CMPE281.Nodes.Sensor;
import edu.sjsu.CMPE281.Nodes.SensorNode;

public class Transmitter
{
	public void init()
	{

	}

	public void update(long delta)
	{
		JSONObject sensorData = generateJSONData();
		
	}

	private JSONObject generateJSONData()
	{
		ControlNode[] controlNodes = SupplyChainComponent.getComponent().getControlNodes();
		JSONObject sensorData = new JSONObject();

		for (int cIndex = 0; cIndex < controlNodes.length; cIndex++)
		{
			ControlNode controlNode = controlNodes[cIndex];
			SensorNode[] sensorNodes = controlNode.getSensorNodes();
			for (int sIndex = 0; sIndex < sensorNodes.length; sIndex++)
			{
				SensorNode sensorNode = sensorNodes[sIndex];
				Sensor[] sensors = sensorNodes[sIndex].getSensors();
				for (int index = 0; index < sensors.length; index++)
				{
					Sensor sensor = sensors[index];

					JSONObject obj = new JSONObject();
					try
					{
						// control node info
						obj.put("ControlNodeId", controlNode.getContolNodeId());
						obj.put("truckId", controlNode.getTruckId());

						// sensor node info
						obj.put("SensorNodeId", sensorNode.getSensorNodeId());

						// sensor info
						obj.put("SensorId", sensor.getSensorId());
						obj.put("SensorType", sensor.getSensorType());
						obj.put("SensorData", sensor.getSensorData());

						sensorData.put("Sensor-" + index, obj);
					} catch (Exception e)
					{
					}
				}
			}
		}

		return sensorData;
	}
}

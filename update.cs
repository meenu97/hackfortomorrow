using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;


public class update : MonoBehaviour {
    public Text distance;
	public float lat1,lat2;
	public float lon1,lon2;

	// Use this for initialization
	void Start () {
		
	}
	

    //prints the distance on the console
    


	// Update is called once per frame
	void Update () {
		lat1=gps.Instance.latitude;
		lon1=gps.Instance.longitude;
        lat2=10.0134447f;
		lon2=76.2930334f;
	    Calculate_Distance(lon1,lat1,lon2,lat2);
	}

      private void Calculate_Distance(float long_a, float lat_a, float long_b, float lat_b)
      {
    float a_long_r, a_lat_r, p_long_r, p_lat_r, dist_x, dist_y, total_dist;
    a_long_r = DegToRad(long_a);
    a_lat_r = DegToRad(lat_a);
    p_long_r = DegToRad(long_b);
    p_lat_r = DegToRad(lat_b);
    dist_x = Distance_x(a_long_r, p_long_r, a_lat_r, p_lat_r);
    dist_y = Distance_y(a_lat_r, p_lat_r);
    total_dist = Final_distance(dist_x, dist_y);
	distance.text="Distance  "+total_dist.ToString();
	  }
    
    float DegToRad(float deg)
{
    float temp;
    temp = (deg * 3.14159265359f ) / 180.0f;
    temp = Mathf.Tan(temp);
    return temp;
}

float Distance_x(float lon_a, float lon_b, float lat_a, float lat_b)
{
    float temp;
    float c;
    temp = (lat_b - lat_a);
    c = Mathf.Abs(temp * Mathf.Cos((lat_a + lat_b)) / 2);
    return c;
}

private float Distance_y(float lat_a, float lat_b)
{
    float c;
    c = (lat_b - lat_a);
    return c;
}

float Final_distance(float x, float y)
{
    float c;
    c = Mathf.Abs(Mathf.Sqrt(Mathf.Pow(x, 2f) + Mathf.Pow(y, 2f))) * 6371;
    return c;
}

}


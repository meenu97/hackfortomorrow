using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class gps : MonoBehaviour {

    public static gps Instance {set;get;}
    public float latitude;
	public float longitude;
	
	// Use this for initialization
	private void Start () {
		Instance=this;
		DontDestroyOnLoad(gameObject);
		StartCoroutine(StartLocationService());
	}
	
	private IEnumerator StartLocationService()
	{
		if(!Input.location.isEnabledByUser)
		{
			Debug.Log("user has not enabled gps");
			yield break;
		}
		Input.location.Start();
		int maxwait=20;
        while(Input.location.status==LocationServiceStatus.Initializing && maxwait>0 )
		{
			yield return new WaitForSeconds(1);
			maxwait--;

		} 
		if(maxwait<=0)
		{
			Debug.Log("timeout");
			yield break;
		}
		if(Input.location.status==LocationServiceStatus.Failed)
		{
			Debug.Log("unable to determine location");
			yield break;

		}
		
		yield break;
	}
	// Update is called once per frame
	void Update () {
		latitude=Input.location.lastData.latitude;
		longitude=Input.location.lastData.longitude;
		
	}
}

using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Image : MonoBehaviour {

	// Use this for initialization
	Transform mytrans;
		public float latitude;
 public float longitude;
 float n1=10.0134447f,n2=76.2930334f;
 public Vector3 t,n;
	void Start () {
	       mytrans=GetComponent<Transform>();
	}
	
	// Update is called once per frame
	void Update () {
		latitude=gps.Instance.latitude;
	longitude=gps.Instance.longitude;
 transform.position=Quaternion.AngleAxis(n2, -Vector3.up) * Quaternion.AngleAxis(n1, -Vector3.right) * new Vector3(0,0,1);
n=transform.position;
transform.position=Quaternion.AngleAxis(longitude, -Vector3.up) * Quaternion.AngleAxis(latitude, -Vector3.right) * new Vector3(0,0,1);
t=transform.position;
	  Vector3 targetDir = n-t;
        float angle = Vector3.Angle(targetDir, transform.forward);
	mytrans.transform.rotation=Quaternion.Euler(0,0,angle);
           
		
	}
}



      
    


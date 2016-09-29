using UnityEngine;
using System.Collections;

public class collectibleBehavior : MonoBehaviour {

    public int speed;
	public float rotspeed;
	public Material[] colors;
	public Mesh[] shapes;
	public int scoreBoth;
	public int scoreColor;
	public int scoreShape;

	SpawnStuff gamecontrol;
    Rigidbody collectRB;
	Material color;
	Mesh shape;
	bool primed;
	bool primeColor;
	bool primeShape;
	bool primeBoth;
	GameObject pair;


	// Use this for initialization
	void Start () {
		GameObject gameControllerObject = GameObject.FindWithTag ("GameController");
		if (gameControllerObject != null) {
			gamecontrol = gameControllerObject.GetComponent<SpawnStuff> ();
		} else {
			Debug.Log ("Cannot find 'SpawnStuff' script");
		}
        collectRB = GetComponent<Rigidbody>();
		color = colors [Random.Range (0, colors.Length)];
		MeshRenderer rend = GetComponent<MeshRenderer> ();
		rend.material = color;
		MeshFilter filter = GetComponent<MeshFilter> ();
		shape = shapes [Random.Range (0, shapes.Length)];
		filter.mesh = shape;
	}

    // Update is called once per frame
    void Update()
    {
		//transform.Rotate (new Vector3 (90, 0, 0) * Time.deltaTime, Space.World);
		if (!gameObject.CompareTag ("Stopped")) {
			transform.Translate (new Vector3 (-speed, 0, 0) * Time.deltaTime, Space.World);
		}
		
			
    }

	void OnTriggerEnter(Collider other){
		if (!other.gameObject.CompareTag ("Player")) {
			if(!other.gameObject.CompareTag("EndZone")){
			if (gameObject.CompareTag ("Collected")) {
				primeColor = other.gameObject.GetComponent<MeshRenderer> ().material.name == gameObject.GetComponent<MeshRenderer> ().material.name;
				primeShape = (other.gameObject.GetComponent<MeshFilter> ().mesh.name == gameObject.GetComponent<MeshFilter> ().mesh.name && !other.gameObject.CompareTag ("Boundary"));
				primeBoth = primeColor && primeShape;
				primed = primeColor || primeShape;
				if (primed) {
					pair = other.gameObject;
				}
				gameObject.tag = "Stopped";
				GetComponent<Rigidbody> ().velocity = new Vector3 (0, 0, 0);
			} else if (primed) {
				bool tempprimeColor = other.gameObject.GetComponent<MeshRenderer> ().material.name == gameObject.GetComponent<MeshRenderer> ().material.name;
				bool tempprimeShape = other.gameObject.GetComponent<MeshFilter> ().mesh.name == gameObject.GetComponent<MeshFilter> ().mesh.name;
				bool tempprimeBoth = primeColor && primeShape;
				if (tempprimeBoth && primeBoth) {
					gamecontrol.updateScore (scoreBoth);
					DestroyTrio (other);
					return;
				} else if (tempprimeColor && primeColor) {
					gamecontrol.updateScore (scoreColor);
					DestroyTrio (other);
					return;
				} else if (tempprimeShape && primeShape) {
					gamecontrol.updateScore (scoreShape);
					DestroyTrio (other);
					return;
				}
				
			}
			}
		}
	}
		
	void OnTriggerExit(Collider other){
		if (gameObject.CompareTag ("Stopped")) {
			gameObject.tag = "Collected";
		}
			
	}

	void DestroyTrio(Collider other){
		Destroy (other.gameObject);
		Destroy (pair.gameObject);
		Destroy (gameObject);
	}


    
    
}

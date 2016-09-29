using UnityEngine;
using System.Collections;

public class GameOverScript : MonoBehaviour {

	SpawnStuff gameControl;
	GameObject player;

	public GUIText gameOverText;

	// Use this for initialization
	void Start () {
		gameControl = GameObject.FindWithTag ("GameController").GetComponent<SpawnStuff> ();
		player = GameObject.FindWithTag ("Player");
		gameOverText.text = "";
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	void OnTriggerStay(Collider other){
		if(other.gameObject.CompareTag("Stopped")){
			gameControl.gameOver = true;
			Destroy(player);
			gameOverText.text = "Game Over";
		}
	}
}

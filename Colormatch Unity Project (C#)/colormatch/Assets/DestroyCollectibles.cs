﻿using UnityEngine;
using System.Collections;

public class DestroyCollectibles : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

    void OnTriggerExit(Collider other)
    {
		if (other.gameObject.CompareTag ("Collectible")) {
			Destroy (other.gameObject);
		}
    }
}

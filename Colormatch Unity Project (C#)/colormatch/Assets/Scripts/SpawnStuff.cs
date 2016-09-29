using UnityEngine;
using System.Collections;

public class SpawnStuff : MonoBehaviour {

    public int CollectCount;
    public Vector3 spawnValues;
    public int spawnWait;
    public int startWait;
    public int waveWait;
    public GameObject collectible;

	public GUIText scoreText;
	public bool gameOver;
	int score;

    int[] spawnpoints;


	// Use this for initialization
	void Start () {
		gameOver = false;
		score = 0;
		updateScore (0);
		spawnpoints = new int[] {-4, 0, 4, 8 };
        StartCoroutine(SpawnWaves());

	}
	
	// Update is called once per frame
	void Update () {
	
	}

    IEnumerator SpawnWaves()
    {
        yield return new WaitForSeconds(startWait);
		while (!gameOver)
        {
            for (int i = 0; i < CollectCount; i++)
            {
                int x = Random.Range(0, 4);
                int spawnY = spawnpoints[x];
                Vector3 spawnPosition = new Vector3(spawnValues.x, spawnY, spawnValues.z);
                Quaternion spawnRotation = Quaternion.identity;
				Instantiate(collectible, spawnPosition, spawnRotation);
                yield return new WaitForSeconds(spawnWait);
            }
            yield return new WaitForSeconds(waveWait);
        }
    }

	public void updateScore(int amount){
		score += amount;
		scoreText.text = "Score: " + score;

	}
}

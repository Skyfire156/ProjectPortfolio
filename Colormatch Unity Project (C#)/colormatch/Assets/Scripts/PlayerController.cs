using UnityEngine;
using System.Collections;

public class PlayerController : MonoBehaviour {

    public float speed;
    public float jumpspeed;
    public bool OnGround;

    Vector3 movement;
    Animator anim;
    Rigidbody playerRB;
	bool flipped;
    

    void Awake()
    {
        anim = GetComponent<Animator>();
        playerRB = GetComponent<Rigidbody>();
		flipped = false;

    }
	
	void FixedUpdate () {
        float h = Input.GetAxisRaw("Horizontal");
        float v = Input.GetAxisRaw("Vertical");
        float js = 0;

        if(v > 0)
        {
            js = 1;
        }

        Move(h, js);
        
        Animate(h, js);

	}

    void Move(float h, float v)
    {
        float vmove = v * jumpspeed * Time.deltaTime;
        float hmove = h * speed * Time.deltaTime;
        movement.Set(hmove, vmove, 0);
		Quaternion playRotation = gameObject.GetComponent<Transform> ().rotation;
		if (h < 0 && !flipped) {
			flip ();
		}
		if (h >= 0 && flipped) {
			flip ();
		}
        playerRB.MovePosition(transform.position + movement);
    }
    
    void Animate(float h, float v)
    {
        bool moving = h != 0f;
        bool jumping = v > 0f;

        anim.SetBool("IsMoving", moving);
        if (jumping&& OnGround)
        {
            anim.SetTrigger("Jump");
            OnGround = false;
        }
    }

    void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.CompareTag("Platform"))
        {
            OnGround = true;
        }
        if (other.gameObject.CompareTag("Collectible"))
        {
            Vector3 collectPos = new Vector3(14, -8, -1);
			other.gameObject.transform.position = collectPos;
			other.gameObject.tag = "Collected";
        }
    }

	void flip(){
		flipped = !flipped;
		Quaternion rote = gameObject.GetComponent<Transform>().localRotation;
		rote.y *= -1;
		gameObject.GetComponent<Transform>().localRotation = rote;
	}
}

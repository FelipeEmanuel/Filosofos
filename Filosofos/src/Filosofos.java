import java.util.concurrent.Semaphore;

public class Filosofos {
	
	private int[] state; 
	private int num_filosofos = 5; 
	private int i;
	private int LEFT = (i + (num_filosofos-1)); 
	private int RIGHT = (i+1); 
	private int THINKING = 0; 
	private int HUNGRY = 1;
	private int EATING = 2;
	private Semaphore mutex;
	private Semaphore[] s;
	
	public static void main(String[] args) {
	
	}

	public Filosofos(Semaphore mutex, int[] state, int num_filosofos) {
		this.mutex = new Semaphore (1);
		this.s = new Semaphore[this.num_filosofos];
		this.state = new int[this.num_filosofos];
	}
	
	
	public void philosopher(int i) throws InterruptedException {
		while (true) { 
			think(); 
			take_forks(i); 
			eat(); 
			put_forks(i); 
		}
	}
	
	
	public void take_forks(int i) throws InterruptedException {
		this.mutex.acquire();
		state[i] = HUNGRY; 
		test(i); 
		this.mutex.release(); 
		this.s[i].acquire();
	}
	
	public void put_forks(int i) throws InterruptedException {
		this.mutex.acquire(); 
		state[i] = THINKING; 
		test(LEFT); 
		test(RIGHT); 
		this.mutex.release(); 
	}
	
	
	public void test(int i) {
		if (state[i] == HUNGRY && state[LEFT] != EATING && state[RIGHT] != EATING) {
		state[i] = EATING;
		this.s[i].release();
		}
	}
	
	public void think() {
		System.out.println("O filósofo está pensando.");
	}
	
	public void eat() {
		System.out.println("O filósofo está comendo.");
	}

}

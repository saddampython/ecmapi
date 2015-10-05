//insert some new comments
class TreadPractice implements Runnable {
  Thread t;
  
  TreadPractice(String s) {
    t = new Thread(this,s);
    t.start();//Ready to run
  }
	 
  public void run() {//Running state
    for(int i=0;i<5;i++) {
	System.out.println("Thread Name :"+Thread.currentThread().getName());
	try{
	  Thread.sleep(1000);//Blocked
	}catch(Exception e){}
    }
  }
}
public class Thread_1 {
  public static void main(String args[]) {
    System.out.println("Thread Name :" + Thread.currentThread().getName());
    TreadPractice m1 = new TreadPractice("My Thread 1");
    TreadPractice m2 = new TreadPractice("My Thread 2");
  }
}

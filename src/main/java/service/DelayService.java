package service;

public class DelayService {

    public void introduceDelay() {
        try {
            Thread.sleep(2000);
        }
        catch( InterruptedException e ) {
            e.printStackTrace();
        }
    }
}

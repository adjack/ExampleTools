package activity.example.yuan.cn.exampletools.designpatterns.method1;

/**
 * Created by 123 on 2018/9/10.
 */

public class ConsumerThread extends Thread{
    private Resource resource;

    public ConsumerThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.remove();
        }
    }
}

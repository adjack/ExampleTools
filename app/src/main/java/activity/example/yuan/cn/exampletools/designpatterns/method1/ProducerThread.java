package activity.example.yuan.cn.exampletools.designpatterns.method1;

/**
 * Created by 123 on 2018/9/10.
 */

public class ProducerThread extends Thread {
    private Resource resource;

    public ProducerThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        //不断地生产资源
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.add();
        }
    }
}
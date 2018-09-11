package activity.example.yuan.cn.exampletools.designpatterns;

/**
 * Created by 123 on 2018/9/10.
 * 消费者
 */

public class Cosumer extends Thread{
    private String consumer;
    private StorageUseBlockingQueue storage;

    public Cosumer(StorageUseBlockingQueue storage)
    {
        this.storage = storage;
    }

    @Override
    public void run()
    {
        consume(consumer);
    }

    private void consume(String consumer)
    {
        storage.consume(consumer);
    }

    public StorageUseBlockingQueue getStorage()
    {
        return storage;
    }

    public void setStorage(StorageUseBlockingQueue storage)
    {
        this.storage = storage;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }
}
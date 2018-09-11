package activity.example.yuan.cn.exampletools.designpatterns;

/**
 * Created by 123 on 2018/9/10.
 * 生产者
 */

public class Producer extends Thread{
    private String producer;
    private StorageUseBlockingQueue storage;

    public Producer(StorageUseBlockingQueue storage)
    {
        this.storage = storage;
    }

    @Override
    public void run()
    {
        produce(producer);
    }

    private void produce(String producer)
    {
        storage.produce(producer);
    }

    public String getProducer()
    {
        return producer;
    }

    public void setProducer(String producer)
    {
        this.producer = producer;
    }

    public StorageUseBlockingQueue getStorage()
    {
        return storage;
    }

    public void setStorage(StorageUseBlockingQueue storage)
    {
        this.storage = storage;
    }
}

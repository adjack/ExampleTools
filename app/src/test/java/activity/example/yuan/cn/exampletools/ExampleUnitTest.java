package activity.example.yuan.cn.exampletools;

import org.junit.Test;

import activity.example.yuan.cn.exampletools.designpatterns.Cosumer;
import activity.example.yuan.cn.exampletools.designpatterns.Producer;
import activity.example.yuan.cn.exampletools.designpatterns.StorageType;
import activity.example.yuan.cn.exampletools.designpatterns.StorageUseAwait;
import activity.example.yuan.cn.exampletools.designpatterns.StorageUseBlockingQueue;
import activity.example.yuan.cn.exampletools.designpatterns.StorageUseWait;
import activity.example.yuan.cn.exampletools.designpatterns.method1.ConsumerThread;
import activity.example.yuan.cn.exampletools.designpatterns.method1.ProducerThread;
import activity.example.yuan.cn.exampletools.designpatterns.method1.Resource;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
//        LinkedList<Object> ob = new LinkedList<>();
        //第一种方式
//        StorageType storage = new StorageUseWait();
        //第二种方式
//      StorageType  storage = new StorageUseAwait();
        //第三种方式
        StorageUseBlockingQueue storage = new StorageUseBlockingQueue();

        for (int i = 0; i < 10; i++) {
            Producer producer = new Producer(storage);
            producer.setProducer("张三"+i);
            producer.start();
        }
        for (int i = 0; i < 10; i++) {
            Cosumer cosumer = new Cosumer(storage);
            cosumer.setConsumer("张三" + i);
            cosumer.start();
        }
    }

    @Test
    public void TestProduct_consumer1() {
        Resource resource = new Resource();

        ProducerThread p1 = new ProducerThread(resource);
        ProducerThread p2 = new ProducerThread(resource);
        ProducerThread p3 = new ProducerThread(resource);

        ConsumerThread c1 = new ConsumerThread(resource);
        p1.start();
        p2.start();
        p3.start();
        c1.start();
    }
}
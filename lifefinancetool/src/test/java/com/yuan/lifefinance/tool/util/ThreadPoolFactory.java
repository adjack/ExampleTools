package com.yuan.lifefinance.tool.util;

import org.junit.Test;

/**
 * @author huangwz
 * @des 线程任务的工厂类
 */
public class ThreadPoolFactory {

	@Test
	public void runThread(){

		new Thread(new MyRunnable(1)).start();
		new Thread(new MyRunnable(2)).start();
		new Thread(new MyRunnable(3)).start();
		new Thread(new MyRunnable(4)).start();
		new Thread(new MyRunnable(5)).start();
		new Thread(new MyRunnable(6)).start();
		new Thread(new MyRunnable(7)).start();

	}

	class MyRunnable implements Runnable{
		int index;
		public MyRunnable(int index){
			this.index = index;
		}
		@Override
		public void run() {
			for(int i=0;i<10000;i++){
				System.out.println(index+"输出："+i);
			}
		}
	}


	static ThreadPoolProxy mNormalPool;

	/**
	 * 线程池
	 */
	public static ThreadPoolProxy getNormalPool() {
		if (mNormalPool == null) {
			synchronized (ThreadPoolProxy.class) {
				mNormalPool = new ThreadPoolProxy(10, 10, 3000);
			}
		}
		return mNormalPool;
	}

	public interface ThreadDealContent{
		void doThing(Object object);
		void finishProcess();
	}

}

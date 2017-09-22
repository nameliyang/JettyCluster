package distrlock;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

public class DistrLock {

	private static final String ZK_ADDRESS = "192.168.199.90:2181";

	private static final String ZK_LOCK_PATH = "/zktest";

	CuratorFramework client;

	public DistrLock() {
		client = CuratorFrameworkFactory.newClient(ZK_ADDRESS, new RetryNTimes(10, 5000));
		client.start();
		org.eclipse.jetty.util.Loader l ;
	}

	public void lock() {
		InterProcessMutex lock = new InterProcessMutex(client, ZK_LOCK_PATH);
		try {
			if (lock.acquire(10 * 1000, TimeUnit.SECONDS)) {
				System.out.println(Thread.currentThread().getName() + " hold lock");
				Thread.sleep(5000L);
				System.out.println(Thread.currentThread().getName() + " release lock");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				lock.release();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

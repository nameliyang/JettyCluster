package com.ly;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest {
	
	public static void main(String[] args) {
			ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
			service.scheduleWithFixedDelay(new Runnable() {
				@Override
				public void run() {
					System.out.println(getDateTime()+" "+slowly());
				}
			}, 0, 2, TimeUnit.SECONDS);
	}
	
	public static String  slowly(){
		int time = 10;
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.valueOf(time);
	}
	public static String getDateTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
}

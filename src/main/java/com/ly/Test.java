package com.ly;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Test {
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		org.eclipse.jetty.webapp.WebAppClassLoader tl;
		
		
		byte[] bytes = new byte[]{49, 48, 53, 104, 98, 108, 114, 54, 55, 53, 100, 57, 104, 56, 53, 52, 56, 51, 106, 55, 48, 112, 118, 121, 55};
		System.out.println(new String(bytes));
//		MemcachedClient client = new MemcachedClient(new InetSocketAddress("192.168.199.90", 11211));
//		Transcoder<byte[]> _transcoder = new NullTranscoder();
//		GetFuture<byte[]> future = client.asyncGet("session:5fdvnp3vwug5wri1irsa4bhb", _transcoder);
//		byte[] bs = future.get();
//		if(bs!=null){
//			for(byte b:bs){
//				System.out.println((byte)b);
//			}
//		}
	}
	
	public static String bytesToHexString(byte[] src){   
	    StringBuilder stringBuilder = new StringBuilder("");   
	    if (src == null || src.length <= 0) {   
	        return null;   
	    }   
	    for (int i = 0; i < src.length; i++) {   
	        int v = src[i] & 0xFF;   
	        String hv = Integer.toHexString(v);   
	        if (hv.length() < 2) {   
	            stringBuilder.append(0);   
	        }   
	        stringBuilder.append(hv);   
	    }   
	    return stringBuilder.toString();   
	}   
}

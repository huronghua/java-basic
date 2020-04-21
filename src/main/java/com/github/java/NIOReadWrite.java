package com.github.java;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description: NIO对文件进行读写
 * @Auther:Eric https://github.com/huronghua
 * @Date:2018/3/31 21 11
 */
public class NIOReadWrite {

	public static void main(String[] args) throws IOException {
		File file = new File(InputStreamToString.class.getClassLoader().getResource("test.txt").getPath());
		FileInputStream fileInputStream = new FileInputStream(file);

		FileChannel channel = fileInputStream.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(48);

		int bytesRead = channel.read(buffer);
		while(bytesRead !=-1)

		{
			System.out.println("Read " + bytesRead);
			buffer.flip();
			while (buffer.hasRemaining()) {
				System.out.print((char) buffer.get());
			}
			buffer.clear();
			bytesRead = channel.read(buffer);
		}
		System.out.println("------"+bytesRead);
		fileInputStream.close();
	}

}

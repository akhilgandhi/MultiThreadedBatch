package com.batch;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BatchReader {

	private BlockingQueue<Runnable> studentDataQueue = new ArrayBlockingQueue<>(10);

	private static final String INPUT_BATCH = "c:\\Dev\\inputData.csv";

	private static final String OUTPUT_BATCH = "c:\\Dev\\output.csv";

	public void readingBatch() {

		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(4, 4, 0L, TimeUnit.MILLISECONDS, studentDataQueue);

		threadPool.setRejectedExecutionHandler(new RejectedExecutionHandler() {

			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				try {
					executor.getQueue().put(r);
				} catch (InterruptedException e) {
					throw new RejectedExecutionException("Unexpected exception occured", e);
				}
			}
		});

		try {
			long start = System.currentTimeMillis();
			BufferedReader reader = Files.newBufferedReader(Paths.get(INPUT_BATCH));
			String line = null;
			while ((line = reader.readLine()) != null) {
				threadPool.submit(new StudentReader(line));
			}
			threadPool.shutdown();
			threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
			System.out.println("END_TIME" + (System.currentTimeMillis() - start));
		} catch (IOException e) {
			System.err.println("IO Exception occured" + e);
		} catch (InterruptedException e) {
			System.err.println("Unexpected exception occured" + e);
		}
	}

	private class StudentReader implements Runnable {

		private final String line;

		public StudentReader(String line) {
			this.line = line;
		}

		@Override
		public void run() {

			String[] lineSplit = line.split(",");

			Student studentData = new Student(lineSplit[0], lineSplit[1], lineSplit[2].charAt(0), lineSplit[3],
					lineSplit[4], lineSplit[5].equals("T") ? true : false, Integer.parseInt(lineSplit[6]));
			
			// System.out.println(Thread.currentThread().getName() +
			// "Creating student object..." + student.getName());
			try {
				Files.write(Paths.get(OUTPUT_BATCH), studentData.toString().getBytes(), StandardOpenOption.CREATE,
						StandardOpenOption.APPEND);
			} catch (IOException e) {
				System.err.println("IO Exception occured ");
			}

		}

	}

}

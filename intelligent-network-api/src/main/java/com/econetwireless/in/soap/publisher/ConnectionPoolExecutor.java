package com.econetwireless.in.soap.publisher;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPoolExecutor extends ThreadPoolExecutor {
	private static final int poolSize = 10;
	private boolean isPaused;
	private ReentrantLock pauseLock = new ReentrantLock();
	private Condition unPaused = pauseLock.newCondition();

	public ConnectionPoolExecutor() {
		super(poolSize, poolSize, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(poolSize));
	}

	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		pauseLock.lock();
		try {
			while (isPaused)
				unPaused.wait();
		} catch (InterruptedException e) {
			t.interrupt();
		} finally {
			pauseLock.unlock();
		}
	}

	public void pause() {
		pauseLock.lock();
		try {
			isPaused = true;
		} finally {
			pauseLock.unlock();
		}
	}

	public void resume() {
		pauseLock.lock();
		try {
			isPaused = false;
			unPaused.signalAll();
		} finally {
			pauseLock.unlock();
		}
	}
}
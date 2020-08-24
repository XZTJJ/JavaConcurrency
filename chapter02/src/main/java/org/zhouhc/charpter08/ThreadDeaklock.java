package org.zhouhc.charpter08;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

public class ThreadDeaklock {
    ExecutorService exec = Executors.newSingleThreadExecutor();

    public class LoadFileTask implements Callable<String> {
        private final String fileName;

        public LoadFileTask(String fileName) {
            this.fileName = fileName;
        }

        public String call() throws Exception {
            // Here's where we would actually read the file
            return "";
        }
    }

    public class RenderPageTask implements Callable<String> {
        public String call() throws Exception {
            Future<String> header, footer;
            header = exec.submit(new LoadFileTask("header.html"));
            footer = exec.submit(new LoadFileTask("footer.html"));
            String page = renderBody();
            // Will deadlock -- task waiting for result of subtask
            return header.get() + page + footer.get();
        }

        private String renderBody() {
            // Here's where we would actually render the page
            return "";
        }
    }

    public static void main(String[] args) {
        ThreadDeaklock threadDeaklock = new ThreadDeaklock();
        threadDeaklock.exec.submit(threadDeaklock.new RenderPageTask());
        threadDeaklock.exec.shutdown();
        ThreadPoolExecutor threadPoolExecutor = null;


//        ExecutorService exec = Executors.newSingleThreadExecutor();
//        exec.submit(threadDeaklock.new RenderPageTask());
//        threadDeaklock.exec.shutdown();
//        exec.shutdown();
        

        Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(10);
//        Executors.newScheduledThreadPool(10);
        Executors.newSingleThreadExecutor();

    }
}

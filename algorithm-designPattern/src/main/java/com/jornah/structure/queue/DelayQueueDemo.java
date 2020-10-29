package com.jornah.structure.queue;

import com.sun.istack.internal.NotNull;
import org.apache.commons.lang3.time.StopWatch;

import java.time.Instant;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo {
    private static class DelayObj implements Delayed {
        private final Instant expiredAt;

        public DelayObj(Instant delaySeconds) {
            this.expiredAt = delaySeconds;
        }

        @Override
        public long getDelay(@NotNull TimeUnit unit) {
            return Instant.now().isAfter(expiredAt) ? -1 : 1;
        }

        @Override
        public int compareTo(@NotNull Delayed o) {

            return ((DelayObj) o).expiredAt.isAfter(this.expiredAt) ? 1 : 0;

        }
    }

    public static void main(String[] args) throws InterruptedException {

        DelayQueue<DelayObj> delayQueue = new DelayQueue<>();
        delayQueue.offer(new DelayObj(Instant.now().plusSeconds(1)), 1, TimeUnit.MINUTES);
        StopWatch stopWatch = new StopWatch();
        while (true) {
            System.out.println("while true");
            DelayObj delayObj = delayQueue.take();
            System.out.println(delayObj);
            if (stopWatch.isStarted()) {
                stopWatch.stop();
                System.out.println(stopWatch.getTime(TimeUnit.MILLISECONDS));
            }
            stopWatch.reset();
            stopWatch.start();
            delayQueue.offer(new DelayObj(Instant.now().plusSeconds(3)), 1, TimeUnit.MINUTES);

        }
    }
}

/*
 * TJVD License (TJ ValentineÃ¢â‚¬â„¢s Discretionary License) Ã¢â‚¬â€ Version 1.0 (2025)
 *
 * Copyright (c) 2025 Taheesh Valentine
 *
 * This source code is protected under the TJVD License.
 * SEE LICENSE.TXT
 */

package org.tavall.scheduler;

import org.tavall.scheduler.interfaces.ICustomScheduler;

import java.util.Set;
import java.util.concurrent.*;

/**
 * Default {@link ICustomScheduler} implementation backed by separate serialized and concurrent
 * scheduled executors.
 *
 * <p>Non-async methods use a single daemon thread, providing a sync-like lane where scheduled
 * tasks do not overlap each other. Async methods use a daemon scheduled thread pool sized from the
 * available processors. Every returned future is tracked so it can later be cancelled or removed
 * from scheduler bookkeeping.</p>
 *
 * @author TJ
 * @since 11/15/2025
 */
public class CustomScheduler implements ICustomScheduler {

    private final Set<ScheduledFuture<?>> activeTasks =
            ConcurrentHashMap.newKeySet();
    private final int MULTI_THREADS =
            Math.max(2, Runtime.getRuntime().availableProcessors());

    // Single-thread executor (sync-like)
    private final ScheduledExecutorService SINGLE =
            Executors.newSingleThreadScheduledExecutor(r -> {
                Thread t = new Thread(r, "CustomTaskRunner-Single");
                t.setDaemon(true);
                return t;
            });

    // Multi-thread executor (async)
    private final ScheduledThreadPoolExecutor MULTI =
            new ScheduledThreadPoolExecutor(MULTI_THREADS, r -> {
                Thread t = new Thread(r, "CustomTaskRunner-Multi");
                t.setDaemon(true);
                return t;
            });

    {
        MULTI.setRemoveOnCancelPolicy(true);
    }

    @Override
    public ScheduledFuture<?> runTaskLaterAsync(Runnable task, long delayMs) {
        ScheduledFuture<?> scheduledFuture = MULTI.schedule(task, delayMs, TimeUnit.MILLISECONDS);
        activeTasks.add(scheduledFuture);
        return scheduledFuture;
    }

    @Override
    public ScheduledFuture<?> runTaskLater(Runnable task, long delayMs) {
        ScheduledFuture<?> scheduledFuture = SINGLE.schedule(task, delayMs, TimeUnit.MILLISECONDS);
        activeTasks.add(scheduledFuture);
        return scheduledFuture;
    }

    @Override
    public ScheduledFuture<?> runTaskLaterAsync(Callable<?> task, long delayMs) {
        ScheduledFuture<?> scheduledFuture = MULTI.schedule(task, delayMs, TimeUnit.MILLISECONDS);
        activeTasks.add(scheduledFuture);
        return scheduledFuture;
    }

    @Override
    public ScheduledFuture<?> runTaskLater(Callable<?> task, long delayMs) {
        ScheduledFuture<?> scheduledFuture = SINGLE.schedule(task, delayMs, TimeUnit.MILLISECONDS);
        activeTasks.add(scheduledFuture);
        return scheduledFuture;
    }

    @Override
    public ScheduledFuture<?> runTaskRepeatingAsync(
            Runnable task, long delayMs, long periodMs) {
        ScheduledFuture<?> scheduledFuture = MULTI.scheduleAtFixedRate(task, delayMs, periodMs, TimeUnit.MILLISECONDS);
        activeTasks.add(scheduledFuture);
        return scheduledFuture;
    }

    @Override
    public ScheduledFuture<?> runTaskRepeating(
            Runnable task, long delayMs, long periodMs) {
        ScheduledFuture<?> scheduledFuture = SINGLE.scheduleAtFixedRate(task, delayMs, periodMs, TimeUnit.MILLISECONDS);
        activeTasks.add(scheduledFuture);
        return scheduledFuture;
    }

    @Override
    public void shutdown() {
        SINGLE.shutdownNow();
        MULTI.shutdownNow();
        activeTasks.clear();
    }

    @Override
    public void shutdownGracefully(long timeoutMs) {
        SINGLE.shutdown();
        MULTI.shutdown();
        try {
            if (!SINGLE.awaitTermination(timeoutMs, TimeUnit.MILLISECONDS)) {
                SINGLE.shutdownNow();
            }
            if (!MULTI.awaitTermination(timeoutMs, TimeUnit.MILLISECONDS)) {
                MULTI.shutdownNow();
            }
        } catch (InterruptedException e) {
            SINGLE.shutdownNow();
            MULTI.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public boolean cancelTask(ScheduledFuture<?> task) {
        if (task == null) return false;
        boolean cancelled = task.cancel(false);
        activeTasks.remove(task);
        return cancelled;
    }

    @Override
    public void cancelAllTasks() {
        for (ScheduledFuture<?> task : activeTasks) {
            task.cancel(false);
        }
        activeTasks.clear();
    }

    @Override
    public void removeTask(ScheduledFuture<?> task) {
        activeTasks.remove(task);
    }
}

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

import java.util.concurrent.ScheduledFuture;

/**
 * Runnable base with convenience methods for scheduling and cancelling itself.
 *
 * <p>Each instance owns a scheduler and remembers the most recently returned task handle. Calling
 * another scheduling method replaces that remembered handle, so {@link #cancel()} targets the most
 * recently scheduled execution for this runnable.</p>
 *
 * @author TJ
 * @since 11/15/2025
 */
public abstract class CustomRunnable implements Runnable {

    private ScheduledFuture<?> scheduledFuture;
    private ICustomScheduler customScheduler = new CustomScheduler();

    /**
     * Cancels the most recently scheduled execution without interrupting it if already running.
     *
     * <p>The task is also removed from the scheduler's active-task bookkeeping.</p>
     *
     * @return {@code true} when the remembered future accepted cancellation, or {@code false} when
     *         this runnable has not been scheduled or cancellation could not be performed
     */
    public boolean cancel(){
        if(scheduledFuture != null){
             boolean isTaskCancelled = scheduledFuture.cancel(false);
             customScheduler.removeTask(scheduledFuture);
             return isTaskCancelled;
        }
        return false;
    }

    /**
     * Schedules this runnable once on the serialized scheduler lane.
     *
     * @param delayMs delay before execution, in milliseconds
     */
    public void runTaskLater(long delayMs) {
        this.scheduledFuture = customScheduler.runTaskLater(this, delayMs);
    }

    /**
     * Schedules this runnable once on the concurrent scheduler lane.
     *
     * @param delayMs delay before execution, in milliseconds
     */
    public void runTaskLaterAsync(long delayMs) {
        this.scheduledFuture = customScheduler.runTaskLaterAsync(this, delayMs);
    }

    /**
     * Schedules this runnable at a fixed rate on the serialized scheduler lane.
     *
     * @param delayMs delay before the first execution, in milliseconds
     * @param periodMs interval between scheduled executions, in milliseconds
     */
    public void runTaskTimer(long delayMs, long periodMs) {
        this.scheduledFuture = customScheduler.runTaskRepeating(this, delayMs, periodMs);
    }

    /**
     * Schedules this runnable at a fixed rate on the concurrent scheduler lane.
     *
     * @param delayMs delay before the first execution, in milliseconds
     * @param periodMs interval between scheduled executions, in milliseconds
     */
    public void runTaskTimerAsync(long delayMs, long periodMs) {
        this.scheduledFuture = customScheduler.runTaskRepeatingAsync(this, delayMs, periodMs);
    }
}

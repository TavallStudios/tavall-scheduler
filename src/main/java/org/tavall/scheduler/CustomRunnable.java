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
 * CustomRunnable Ã¢â‚¬â€œ TODO: implement class functionality
 * Auto-generated skeleton by MondayGPT-style template
 *
 * @author TJ
 * @since 11/15/2025
 */
public abstract class CustomRunnable implements Runnable {

    private ScheduledFuture<?> scheduledFuture;
    private ICustomScheduler customScheduler = new CustomScheduler();


    public boolean cancel(){
        if(scheduledFuture != null){
             boolean isTaskCancelled = scheduledFuture.cancel(false);
             customScheduler.removeTask(scheduledFuture);
             return isTaskCancelled;
        }
        return false;
    }
    public void runTaskLater(long delayMs) {
        this.scheduledFuture = customScheduler.runTaskLater(this, delayMs);
    }

    public void runTaskLaterAsync(long delayMs) {
        this.scheduledFuture = customScheduler.runTaskLaterAsync(this, delayMs);
    }

    public void runTaskTimer(long delayMs, long periodMs) {
        this.scheduledFuture = customScheduler.runTaskRepeating(this, delayMs, periodMs);
    }

    public void runTaskTimerAsync(long delayMs, long periodMs) {
        this.scheduledFuture = customScheduler.runTaskRepeatingAsync(this, delayMs, periodMs);
    }
}
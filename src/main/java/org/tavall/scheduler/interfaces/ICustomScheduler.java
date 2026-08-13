/*
 * TJVD License (TJ ValentineÃ¢â‚¬â„¢s Discretionary License) Ã¢â‚¬â€ Version 1.0 (2025)
 *
 * Copyright (c) 2025 Taheesh Valentine
 *
 * This source code is protected under the TJVD License.
 * SEE LICENSE.TXT
 */

package org.tavall.scheduler.interfaces;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;

/**
 * Scheduling contract for delayed and repeating Tavall tasks.
 *
 * <p>"Async" operations may execute concurrently with other scheduled work. Non-async operations
 * are expected to execute through a serialized scheduler lane so tasks submitted through that lane
 * do not overlap each other. Delays and periods are expressed in milliseconds.</p>
 */
public interface ICustomScheduler extends org.tavall.dependency.IDependencyInjectableInterface {

    /**
     * Schedules a runnable for delayed execution on the concurrent scheduler lane.
     *
     * @param task work to execute
     * @param delayMs delay before execution, in milliseconds
     * @return handle representing the scheduled execution
     */
    default ScheduledFuture<?> runTaskLaterAsync(Runnable task, long delayMs) {
        return null;
    }

    /**
     * Schedules a runnable for delayed execution on the serialized scheduler lane.
     *
     * @param task work to execute
     * @param delayMs delay before execution, in milliseconds
     * @return handle representing the scheduled execution
     */
    default ScheduledFuture<?> runTaskLater(Runnable task, long delayMs) {
        return null;
    }

    /**
     * Schedules a callable for delayed execution on the concurrent scheduler lane.
     *
     * @param task value-producing work to execute
     * @param delayMs delay before execution, in milliseconds
     * @return handle representing the scheduled execution and its result
     */
    default ScheduledFuture<?> runTaskLaterAsync(Callable<?> task, long delayMs) {
        return null;
    }

    /**
     * Schedules a callable for delayed execution on the serialized scheduler lane.
     *
     * @param task value-producing work to execute
     * @param delayMs delay before execution, in milliseconds
     * @return handle representing the scheduled execution and its result
     */
    default ScheduledFuture<?> runTaskLater(Callable<?> task, long delayMs) {
        return null;
    }

    /**
     * Schedules a runnable for fixed-rate execution on the concurrent scheduler lane.
     *
     * @param task work to execute repeatedly
     * @param delayMs delay before the first execution, in milliseconds
     * @param periodMs interval between scheduled executions, in milliseconds
     * @return handle representing the repeating task
     */
    default ScheduledFuture<?> runTaskRepeatingAsync(Runnable task, long delayMs, long periodMs) {
        return null;
    }

    /**
     * Schedules a runnable for fixed-rate execution on the serialized scheduler lane.
     *
     * @param task work to execute repeatedly
     * @param delayMs delay before the first execution, in milliseconds
     * @param periodMs interval between scheduled executions, in milliseconds
     * @return handle representing the repeating task
     */
    default ScheduledFuture<?> runTaskRepeating(Runnable task, long delayMs, long periodMs) {
        return null;
    }

    /**
     * Immediately requests scheduler shutdown and cancellation of queued work.
     */
    default void shutdown() {
    }

    /**
     * Requests orderly shutdown, waiting up to the supplied timeout for each scheduler lane before
     * forcing remaining work to stop.
     *
     * @param timeoutMs maximum graceful wait per scheduler lane, in milliseconds
     */
    default void shutdownGracefully(long timeoutMs) {
    }

    /**
     * Cancels one scheduled task without interrupting it if it is already running.
     *
     * @param task scheduled task to cancel
     * @return {@code true} when the future accepted cancellation; {@code false} for a null task or
     *         when cancellation could not be performed
     */
    default boolean cancelTask(ScheduledFuture<?> task) {
        return false;
    }

    /**
     * Requests cancellation of every task currently tracked by the scheduler.
     */
    default void cancelAllTasks() {
    }

    /**
     * Removes a task from scheduler bookkeeping without cancelling the underlying future.
     *
     * @param task task handle to stop tracking
     */
    default void removeTask(ScheduledFuture<?> task){
    }
}

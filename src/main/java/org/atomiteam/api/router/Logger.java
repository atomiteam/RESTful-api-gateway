package org.atomiteam.api.router;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * Provides logging utility for use in AWS Lambda functions.
 *
 * <p>This class wraps the AWS Lambda {@link Context} logger to allow easy
 * and formatted logging within Lambda execution environments. It ensures
 * that logging operations are safe even when the context or logger is null.
 */
public class Logger {
    private Context ctx;

    /**
     * Constructs a Logger with the specified AWS Lambda context.
     *
     * @param ctx the AWS Lambda context.
     */
    public Logger(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Logs a formatted message using the Lambda context logger.
     *
     * @param log  the log message format string.
     * @param args the arguments referenced by the format specifiers in the format string.
     */
    public void log(String log, Object... args) {
        if (ctx == null || ctx.getLogger() == null) {
            return;
        }
        ctx.getLogger().log(String.format(log, args));
    }
}

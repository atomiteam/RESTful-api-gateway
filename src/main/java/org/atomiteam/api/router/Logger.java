package org.atomiteam.api.router;


import com.amazonaws.services.lambda.runtime.Context;

public class Logger {
    private Context ctx;

    public Logger(Context ctx) {
        this.ctx = ctx;
    }

    public void log(String log, Object... args) {
        if (ctx == null || ctx.getLogger() == null) {
            return;
        }
        ctx.getLogger().log(String.format(log, args));
    }
}

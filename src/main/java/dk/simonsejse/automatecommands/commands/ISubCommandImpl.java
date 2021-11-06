package dk.simonsejse.automatecommands.commands;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ISubCommandImpl {
    Class<? extends AbstractCommandPerformImpl> superCommand();
    String parameterName();
}

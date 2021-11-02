package dk.simonsejse.testplugin.commands;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ICommandImpl {
    String cmdName();
    String permission() default "bukkit.command.delete";
    String info();
    String usage();
    String version() default "1.0.0";
    int cooldown() default 0;
}

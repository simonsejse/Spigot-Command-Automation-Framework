package dk.simonsejse.automatecommands.cooldown;

import dk.simonsejse.automatecommands.commands.ICommandImpl;

import java.time.LocalDateTime;

public class CooldownCommand {
    public ICommandImpl iCommandImpl;
    public LocalDateTime whenExecuted;

    public CooldownCommand(ICommandImpl iCommandImpl, LocalDateTime whenExecuted) {
        this.iCommandImpl = iCommandImpl;
        this.whenExecuted = whenExecuted;
    }

    public ICommandImpl getICommandImpl() {
        return iCommandImpl;
    }

    public LocalDateTime getWhenExecuted() {
        return whenExecuted;
    }
}

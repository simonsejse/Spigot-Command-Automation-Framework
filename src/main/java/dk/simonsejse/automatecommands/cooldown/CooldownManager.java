package dk.simonsejse.automatecommands.cooldown;

import dk.simonsejse.automatecommands.Base;
import dk.simonsejse.automatecommands.commands.ICommandImpl;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class CooldownManager {

    final Map<UUID, List<CooldownCommand>> commandsCooldown;
    final Base base;

    public CooldownManager(final Base base){
        this.base = base;
        this.commandsCooldown = new HashMap<>();
    }

    public void addCooldown(Player player, ICommandImpl command) {
        if (this.commandsCooldown.containsKey(player.getUniqueId())){
            this.commandsCooldown.computeIfPresent(
                    player.getUniqueId(),
                    (uuid, oldCooldownCommands) -> {
                        final List<CooldownCommand> cooldownCommands = new LinkedList<>(oldCooldownCommands);
                        cooldownCommands.add(new CooldownCommand(command, LocalDateTime.now().plusSeconds(command.cooldown())));
                        return cooldownCommands;
                    }
            );
        }else {
            final List<CooldownCommand> cooldownCommands = new ArrayList<>();
            cooldownCommands.add(new CooldownCommand(command, LocalDateTime.now().plusSeconds(command.cooldown())));
            this.commandsCooldown.put(player.getUniqueId(), cooldownCommands);
        }
    }
    public boolean hasCooldownExpired(Player player, ICommandImpl iCommandImpl2){
        if (!commandsCooldown.containsKey(player.getUniqueId())) return true;
        final List<CooldownCommand> commands = this.commandsCooldown.get(player.getUniqueId());
        return commands.stream().noneMatch(command -> command.iCommandImpl.equals(iCommandImpl2)) || commands.removeIf(command -> command.iCommandImpl.equals(iCommandImpl2) && LocalDateTime.now().isAfter(command.whenExecuted));
    }

    public long getCooldown(Player player, ICommandImpl iCommandImpl){
       if (this.commandsCooldown.containsKey(player.getUniqueId())){
           final List<CooldownCommand> cooldownCommands = this.commandsCooldown.get(player.getUniqueId());
           final LocalDateTime localDateTime = cooldownCommands.stream().filter(cooldownCommand -> cooldownCommand.iCommandImpl.equals(iCommandImpl))
                   .map(CooldownCommand::getWhenExecuted)
                   .findFirst()
                   .orElse(LocalDateTime.now());
           return Duration.between(LocalDateTime.now(), localDateTime).getSeconds();
       }
       return 0L;
    }
}

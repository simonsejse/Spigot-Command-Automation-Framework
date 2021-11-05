package dk.simonsejse.automatecommands.commands;

import dk.simonsejse.automatecommands.Base;
import dk.simonsejse.automatecommands.exceptions.CommandCooldownException;
import dk.simonsejse.automatecommands.exceptions.CommandNotFoundException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class CommandManager implements CommandExecutor {

    private final Base base;
    private final Map<ICommandImpl, ICommandPerformImpl> commands;

    public CommandManager(final Base base){
        this.base = base;
        this.commands = new HashMap<>();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        Optional<Map.Entry<ICommandImpl, ICommandPerformImpl>> iCommandImplICommandPerformImplEntryOptional = this.commands
                .entrySet()
                .stream()
                .filter(iCommandImplICommandPerformImplEntry -> {
            return iCommandImplICommandPerformImplEntry.getKey().cmdName().equalsIgnoreCase(cmd);
        }).findFirst();

        try{
            Map.Entry<ICommandImpl, ICommandPerformImpl> iCommandImplICommandPerformImplEntry =
                    iCommandImplICommandPerformImplEntryOptional.orElseThrow(() -> new CommandNotFoundException("Kommandoen findes ikke!"));

            final ICommandPerformImpl iCommandPerformImpl = iCommandImplICommandPerformImplEntry.getValue();
            final ICommandImpl iCommandImpl = iCommandImplICommandPerformImplEntry.getKey();

            if (!this.base.cooldownManager.hasCooldownExpired(player, iCommandImpl)) throw new CommandCooldownException(String.format("Du skal vente %s sek med at bruge kommandoen igen!", this.base.cooldownManager.getCooldown(player, iCommandImpl)));
            iCommandPerformImpl.performPreArguments(player, args);
            this.base.cooldownManager.addCooldown(player, iCommandImpl);
        }catch(CommandNotFoundException | CommandCooldownException e){
            player.sendMessage(e.getMessage());
        }





        return false;
    }

    public void registerCommands(){
        Reflections reflections = new Reflections("dk.simonsejse.testplugin", Scanners.TypesAnnotated);

        final Set<Class<?>> commands = reflections.getTypesAnnotatedWith(ICommandImpl.class);

        for(Class<?> command : commands){
            try {
                ICommandPerformImpl iCommandPerformImplInstance = (ICommandPerformImpl) command.getConstructor().newInstance();
                final ICommandImpl iCommandImpl = iCommandPerformImplInstance.getClass().getAnnotation(ICommandImpl.class);

                this.commands.put(iCommandImpl, iCommandPerformImplInstance);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }

            this.commands.forEach((iCommand, iCommandPerform) -> this.base.getCommand(iCommand.cmdName()).setExecutor(this));
        }

        System.out.println(commands.getClass().getName());

    }

}

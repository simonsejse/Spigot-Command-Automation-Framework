package dk.simonsejse.automatecommands.commands;

import dk.simonsejse.automatecommands.exceptions.CommandNoArgumentFoundException;
import dk.simonsejse.automatecommands.exceptions.WrongCommandUsage;
import org.bukkit.entity.Player;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public abstract class AbstractCommandPerformImpl {

    private final Map<ISubCommandImpl, ISubCommandPerformImpl> subcommands;

    public AbstractCommandPerformImpl(){
        this.subcommands = new HashMap<>();
        this.registerSubCommands();
    }

    private void registerSubCommands() {
        Reflections reflections = new Reflections("dk.simonsejse.automatecommands", Scanners.TypesAnnotated);

        final Set<Class<?>> typesAnnotatedWithSubcommand = reflections.getTypesAnnotatedWith(ISubCommandImpl.class);
        for(Class<?> type : typesAnnotatedWithSubcommand){
            try {
                ISubCommandPerformImpl iSubCommandPerform = (ISubCommandPerformImpl) type.getConstructor().newInstance();
                ISubCommandImpl iSubCommand = iSubCommandPerform.getClass().getAnnotation(ISubCommandImpl.class);
                if (iSubCommand.superCommand().equals(this.getClass())){
                    this.subcommands.put(iSubCommand, iSubCommandPerform);
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * Ran first, and invokes our method this#performPostArguments
     *
     * @param player Player who executed command
     * @param args String array containing arguments
    */
    public void performPreArguments(Player player, String[] args){
        try{
            if (args.length > 0 && this.subcommands.size() > 0){
                final Optional<ISubCommandPerformImpl> subCommandOptional = this.subcommands.entrySet().stream()
                        .filter(iSubCommandImplISubCommandPerformEntry -> {
                            return args[0].equalsIgnoreCase(iSubCommandImplISubCommandPerformEntry.getKey().parameterName());
                        })
                        .map(
                                Map.Entry::getValue
                        ).findFirst();

                ISubCommandPerformImpl iSubCommandPerformImpl = subCommandOptional.orElseThrow(CommandNoArgumentFoundException::new);
                iSubCommandPerformImpl.performArgument(player ,args);
            } else throw new CommandNoArgumentFoundException();
        }catch(CommandNoArgumentFoundException noArgumentsFoundException){
            /* Run abstract method that is different from each class */
            this.performPostExecutionWhenArgumentsNotFound(player, args);
        }

    }


    /**
     * Invoked if no arguments is found in method this#performPreArguments
     *
     * @param player Player who executed command
     * @param args String array containing arguments
     * @throws WrongCommandUsage
     */
    public abstract void performPostExecutionWhenArgumentsNotFound(Player player, String[] args) throws WrongCommandUsage;

}

package dk.simonsejse.automatecommands.commands;

import dk.simonsejse.automatecommands.exceptions.CommandNoArgumentFoundException;
import dk.simonsejse.automatecommands.exceptions.WrongCommandUsage;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public interface ICommandPerformImpl {

    @Nullable
    List<ISubCommand> subcommands();

    /**
     * Ran first, and invokes our method this#performPostArguments
     *
     * @param player Player who executed command
     * @param args String array containing arguments
     */
    default void performPreArguments(Player player, String[] args){
        try{
            if (args.length > 0 && this.subcommands() != null){
                final Optional<ISubCommand> subCommandOptional = this.subcommands().stream()
                        .filter(iSubCommand -> args[0].equalsIgnoreCase(iSubCommand.name()))
                        .findFirst();
                ISubCommand iSubCommand = subCommandOptional.orElseThrow(CommandNoArgumentFoundException::new);
                iSubCommand.performArgument(player ,args);
            } else throw new CommandNoArgumentFoundException();
        }catch(CommandNoArgumentFoundException noArgumentsFoundException){
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
    void performPostExecutionWhenArgumentsNotFound(Player player, String[] args) throws WrongCommandUsage;

}

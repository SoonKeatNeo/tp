package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAN;

import java.util.stream.Stream;

import seedu.address.logic.commands.ViewPlanCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewPlanCommand object
 */
public class ViewPlanCommandParser implements Parser<ViewPlanCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the plan Command
     * and returns a ViewPlanCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewPlanCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PLAN);

        if (!arePrefixesPresent(argMultimap, PREFIX_PLAN)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewPlanCommand.MESSAGE_USAGE));
        }

        int index = 0;
        if (argMultimap.getValue(PREFIX_PLAN).isPresent()) {
            index = ParserUtil.parsePlan(argMultimap.getValue(PREFIX_PLAN).get());
        }

        return new ViewPlanCommand(index);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

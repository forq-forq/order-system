package command;

public interface Command {
    void execute();
    default void undo() {}
}

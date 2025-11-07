package command;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandInvoker {
    private final Deque<Command> history = new ArrayDeque<>();
    private final Deque<Command> undone = new ArrayDeque<>();

    public void run(Command c) {
        c.execute();
        history.push(c);
        undone.clear();
    }

    public boolean undo() {
        if (history.isEmpty()) return false;
        Command c = history.pop();
        c.undo();
        undone.push(c);
        return true;
    }

    public boolean redo() {
        if (undone.isEmpty()) return false;
        Command c = undone.pop();
        c.execute();
        history.push(c);
        return true;
    }
}

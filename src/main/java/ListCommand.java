public class ListCommand extends Command{

    @Override
    public void execute(TaskList tasks) {
        tasks.printList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

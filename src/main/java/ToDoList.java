import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> taskList;

    public ToDoList() {
       this.taskList = new ArrayList<>();
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public Task getTask(int taskNo) {
        return taskList.get(taskNo - 1);
    }

    public void markTask(int taskNo) {
        Task task = this.getTask(taskNo);
        task.mark();
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public void unMarkTask(int taskNo) {
        Task task = this.getTask(taskNo);
        task.unMark();
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    public void printList() {
        int ListLength = taskList.size();
        for (int i = 0; i < ListLength; i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }


}

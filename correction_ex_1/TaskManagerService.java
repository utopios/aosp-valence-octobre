//Ajout des imports,....

public class TaskManagerService extends Service implements ITaskManager {
    private List<Task> tasks = new ArrayList<>();

    @Override
    public IBinder onBind(Intent intent) {
        return new ITaskManager.Stub() {
            @Override
            public void addTask(Task task) {
                tasks.add(task);
            }

            @Override
            public void removeTask(Task task) {
                tasks.remove(task);
            }

            @Override
            public Task getTask(int index) {
                return tasks.get(index);
            }

            @Override
            public List<Task> getAllTasks() {
                return tasks;
            }
        };
    }
}
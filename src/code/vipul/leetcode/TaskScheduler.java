package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 17/08/23
 * https://leetcode.com/problems/task-scheduler/
 */
public class TaskScheduler {

    public static void solve() {
        System.out.println(new TaskScheduler().leastInterval(
                new char[]{'B','C','D','E','F','G','A','A','A','A','A','A'}, 2
        ));
    }

    public int leastInterval(char[] _tasks, int n) {
        Map<Character, Integer> counts = new HashMap<>();
        Map<Character, Task> tasks = new HashMap<>();

        for (char task : _tasks) {
            counts.merge(task, 1, (v1,v2) -> v1+v2);
        }
        for (var entry : counts.entrySet()) {
            char ch = entry.getKey();
            tasks.put(ch, new Task(ch, 0, entry.getValue()));
        }

        int time = 1;
        while(!tasks.isEmpty()) {
            Task task = getTask(tasks, time);
            task.executionsLeft--;
            if (task.executionsLeft == 0) {
                tasks.remove(task.ch);
            }
            if (task.nextExecution <= time) {
                System.out.println("Scheduling: " + task.ch);
                System.out.println("Time: " + time);
                System.out.println();
                task.nextExecution = time + n + 1;
                if (!tasks.isEmpty()) time++;
            } else {
                time = task.nextExecution;
                System.out.println("Scheduling: " + task.ch);
                System.out.println("Time: " + time);
                System.out.println();
                task.nextExecution = time + n + 1;
            }
        }
        return time;
    }

    private Task getTask(Map<Character, Task> tasks, int time) {
        Task less = null;
        Task more = null;

        for (Task task : tasks.values()) {
            if (task.nextExecution <= time) {
                if (less == null || task.executionsLeft >= less.executionsLeft) {
                    less = task;
                }
            } else {
                if (more == null
                        || task.nextExecution < more.nextExecution
                        || (task.nextExecution == more.nextExecution && task.executionsLeft >= more.executionsLeft)) {
                    more = task;
                }
            }
        }
        return less == null ? more : less;
    }

    private Task getImmediateSchedule(Map<Character, Task> tasks, int time) {
        return tasks.values().stream()
                .filter(t -> t.nextExecution <= time)
                .min(Task.EXECUTIONS_LEFT_FIRST).orElse(null);
    }

    private Task getNextSchedule(Map<Character, Task> tasks, int time) {
        return tasks.values().stream()
                .filter(t -> t.nextExecution > time)
                .min(Task.NEXT_EXECUTION_FIRST).orElse(null);
    }

    private class Task {
        private char ch;
        private int nextExecution;
        private int executionsLeft;

        private static final Comparator<Task> EXECUTIONS_LEFT_FIRST = (t1, t2) -> t2.executionsLeft - t1.executionsLeft;
        private static final Comparator<Task> NEXT_EXECUTION_FIRST = (t1, t2) -> {
            if (t1.nextExecution == t2.nextExecution) {
                return t2.executionsLeft - t1.executionsLeft;
            }
            return t1.nextExecution - t2.nextExecution;
        };

        public Task(char c, int ne, int el) {
            this.ch = c;
            this.nextExecution = ne;
            this.executionsLeft = el;
        }
    }
}

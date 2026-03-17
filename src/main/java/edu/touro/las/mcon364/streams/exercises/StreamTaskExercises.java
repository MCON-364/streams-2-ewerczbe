package edu.touro.las.mcon364.streams.exercises;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTaskExercises {

    public List<String> highPriorityDescriptions(List<Task> tasks) {
        return tasks.stream()
                .filter(t -> t.priority() == Priority.HIGH)
                .map(Task::description)
                .collect(Collectors.toList());
    }

    public Map<Status, Long> countByStatus(List<Task> tasks) {
        return tasks.stream()
                .collect(Collectors.groupingBy(Task::status, Collectors.counting()));
    }

    public Map<Priority, List<String>> descriptionsByPriority(List<Task> tasks) {
        return tasks.stream()
                .collect(Collectors.groupingBy(
                        Task::priority,
                        Collectors.mapping(Task::description, Collectors.toList())
                ));
    }

    public Map<Boolean, List<Task>> partitionByDone(List<Task> tasks) {
        return tasks.stream()
                .collect(Collectors.partitioningBy(t -> t.status() == Status.DONE));
    }

    public Map<Boolean, Long> countDonePartition(List<Task> tasks) {
        return tasks.stream()
                .collect(Collectors.partitioningBy(
                        t -> t.status() == Status.DONE,
                        Collectors.counting()
                ));
    }

    public Map<Status, Map<Priority, List<Task>>> groupByStatusThenPriority(List<Task> tasks) {
        return tasks.stream()
                .collect(Collectors.groupingBy(
                        Task::status,
                        Collectors.groupingBy(Task::priority)
                ));
    }

    public Map<Status, List<String>> sortedDescriptionsByStatus(List<Task> tasks) {
        return tasks.stream()
                .collect(Collectors.groupingBy(
                        Task::status,
                        Collectors.collectingAndThen(
                                Collectors.mapping(Task::description, Collectors.toList()),
                                list -> list.stream().sorted().collect(Collectors.toList())
                        )
                ));
    }

    public String doneTaskSummary(List<Task> tasks) {
        return tasks.stream()
                .filter(t -> t.status() == Status.DONE)
                .map(Task::description)
                .collect(Collectors.joining(", "));
    }

    public List<String> allTags(List<WorkItem> items) {
        return items.stream()
                .flatMap(item -> item.tags().stream())
                .collect(Collectors.toList());
    }

    public List<String> distinctDoneAssignees(List<WorkItem> items) {
        return items.stream()
                .filter(item -> item.status() == Status.DONE)
                .flatMap(item -> item.assignees().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public Map<String, Status> idToStatus(List<WorkItem> items) {
        return items.stream()
                .collect(Collectors.toMap(
                        WorkItem::id,
                        WorkItem::status
                ));
    }

    public Map<Priority, List<String>> titlesByPriorityUsingMapping(List<WorkItem> items) {
        return items.stream()
                .collect(Collectors.groupingBy(
                        WorkItem::priority,
                        Collectors.mapping(WorkItem::title, Collectors.toList())
                ));
    }
}
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGetIdSimpleTask() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        int expected = 5;
        int actual = simpleTask.getId();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldGetIdEpic() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        int expected = 55;
        int actual = epic.getId();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldGetIdMeeting() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        int expected = 555;
        int actual = meeting.getId();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldGetTittleInSimpleTask() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String expected = simpleTask.title;
        String actual = simpleTask.getTitle();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldGetSubtasksInEpic() {
        String[] subtasks = {"Штаны", "Футболка", "Очки"};
        Epic epic = new Epic(33, subtasks);

        epic.getSubtasks();

        Assertions.assertArrayEquals(subtasks, epic.getSubtasks());
    }

    @Test
    public void shouldGetTopicInMeeting() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        String expected = meeting.topic;
        String actual = meeting.getTopic();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldGetProjectInMeeting() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        String expected = meeting.project;
        String actual = meeting.getProject();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldGetStartInMeeting() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        String expected = meeting.start;
        String actual = meeting.getStart();

        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void shouldQueryTrueInMeeting() {
        Meeting meeting = new Meeting(
                444,
                "Выписать таблетки",
                "Приложение телемедицины",
                "После дождика в четверг"
        );
        Assertions.assertTrue(meeting.matches("таблетки"));
    }

    @Test
    public void shouldQueryFalseInMeetingTopic() {
        Meeting meeting = new Meeting(
                444,
                "Выписать таблетки",
                "Приложение телемедицины",
                "После дождика в четверг"
        );
        Assertions.assertFalse(meeting.matches("небо"));
    }

    @Test
    public void shouldQueryFalseInMeetingStart() {
        Meeting meeting = new Meeting(
                444,
                "Выписать таблетки",
                "Приложение телемедицины",
                "После дождика в четверг"
        );
        Assertions.assertFalse(meeting.matches("дождика"));
    }

    @Test
    public void shouldQueryTrueMeetingProject() {
        Meeting meeting = new Meeting(
                444,
                "Выписать таблетки",
                "Приложение телемедицины",
                "После дождика в четверг"
        );
        Assertions.assertTrue(meeting.matches("телемедицины"));
    }

    @Test
    public void shouldQueryFalseInMeetingProject() {
        Meeting meeting = new Meeting(
                444,
                "Выписать таблетки",
                "Приложение телемедицины",
                "После дождика в четверг"
        );
        Assertions.assertTrue(meeting.matches("таблетки"));
    }

    @Test
    public void shouldQueryTrueInEpic() {
        String[] subtasks = {"Купить таблетки", "Выкинуть мусор", "Погладить рубашку"};
        Epic epic = new Epic(55, subtasks);

        Assertions.assertTrue(epic.matches("таблетки"));
    }

    @Test
    public void shouldQueryFalseInEpic() {
        String[] subtasks = {"Купить таблетки", "Выкинуть мусор", "Погладить рубашку"};
        Epic epic = new Epic(55, subtasks);

        Assertions.assertFalse(epic.matches("небо"));
    }

    @Test
    public void shouldQueryTrueInSimpleTusk() {
        SimpleTask simpleTusk = new SimpleTask(18, "Выпить таблетки");

        Assertions.assertTrue(simpleTusk.matches("таблетки"));
    }

    @Test
    public void shouldQueryFalseInSimpleTusk() {
        SimpleTask simpleTusk = new SimpleTask(18, "Выпить таблетки");

        Assertions.assertFalse(simpleTusk.matches("небо"));
    }

    @Test
    public void shouldSearch() {
        SimpleTask simpleTask = new SimpleTask(6, "Выпить таблетки");

        String[] subtasks = {"Купить таблетки", "Выкинуть мусор", "Погладить рубашку"};
        Epic epic = new Epic(65, subtasks);

        Meeting meeting = new Meeting(
                333,
                "Выписать таблетки",
                "Приложение телемедицины",
                "После дождика в четверг"
        );
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] result = todos.search("таблетки");

        Task[] expected = {simpleTask, epic, meeting};

        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void shouldSearchNothing() {
        SimpleTask simpleTask = new SimpleTask(6, "Выпить таблетки");

        String[] subtasks = {"Купить таблетки", "Выкинуть мусор", "Погладить рубашку"};
        Epic epic = new Epic(65, subtasks);

        Meeting meeting = new Meeting(
                333,
                "Выписать таблетки",
                "Приложение телемедицины",
                "После дождика в четверг"
        );
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] result = todos.search("небо");

        Task[] expected = {};

        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void shouldSearchProjectMeeting() {
        SimpleTask simpleTask = new SimpleTask(6, "Выпить таблетки");

        String[] subtasks = {"Купить таблетки", "Выкинуть мусор", "Погладить рубашку"};
        Epic epic = new Epic(65, subtasks);

        Meeting meeting = new Meeting(
                333,
                "Выписать таблетки",
                "Приложение телемедицины",
                "После дождика в четверг"
        );
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] result = todos.search("Приложение");

        Task[] expected = {meeting};

        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void shouldSearchEpic() {
        SimpleTask simpleTask = new SimpleTask(6, "Выпить таблетки");

        String[] subtasks = {"Купить таблетки", "Выкинуть мусор", "Погладить рубашку"};
        Epic epic = new Epic(65, subtasks);

        Meeting meeting = new Meeting(
                333,
                "Выписать таблетки",
                "Приложение телемедицины",
                "После дождика в четверг"
        );
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] result = todos.search("мусор");

        Task[] expected = {epic};

        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void shouldEqualsTrueTask() {
        Task task1 = new SimpleTask(1, "Заголовок");
        Task task2 = new SimpleTask(1, "Заголовок 2");
//        task1.equals(task2);
        Assertions.assertEquals(task1, task2);
//        Assertions.assertTrue(task1.equals(task2));
    }

    @Test
    public void shouldEqualsFalseTask() {
        Task task1 = new SimpleTask(1, "Заголовок");
        Task task2 = new SimpleTask(2, "Заголовок 2");

        Assertions.assertNotEquals(task1, task2);
    }

    @Test
    public void shouldHashCodeTask() {
        Task task1 = new SimpleTask(1, "Заголовок");
        Task task2 = new SimpleTask(1, "Заголовок 2");

        Assertions.assertEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    public void testSimpleTask() {
        SimpleTask task = new SimpleTask(1, "");
        Assertions.assertTrue(task.matches(""));
    }

    @Test
    public void testMatchesTask() {
        Task task = new Task(1);
        Assertions.assertFalse(task.matches(""));
    }

    @Test
    public void testEqualsTask() {
        Task task = new Task(0);

        Assertions.assertEquals(task, task);
    }

    @Test
    public void testEqualsTaskFalse() {
        Task task = new Task(0);

        Assertions.assertNotEquals(task, null);
    }

    @Test
    public void testEqualsTaskGetClassFalse() {
        Task task = new Task(1);
        Task task2 = new SimpleTask(2, "test");
        Assertions.assertNotEquals(task, task2);
    }
}







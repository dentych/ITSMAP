package me.tychsen.l6_allurdbarebelongtouse;

public class Task {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "task_name";
    public static final String COLUMN_PLACE = "task_place";

    private long id;
    private String name;
    private String place;

    public Task(String name, String place) {
        this.name = name;
        this.place = place;
    }

    public Task(long id, String name, String place) {
        this(name, place);
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }
}

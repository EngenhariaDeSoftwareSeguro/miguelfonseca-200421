package cncs.academy.ess.model;

public class Music {
    /** The id of the music item */
    private int id;
    /** The description of the music item */
    private String description;
    /** The completion status of the music item */
    private boolean completed;
    /** The id of the list that the music item belongs to */
    private int listId;

    public Music(int id, String description, boolean completed, int listId) {
        this.id = id;
        this.description = description;
        this.completed = completed;
        this.listId = listId;
    }
    public Music(String description, int listId) {
        this.description = description;
        this.completed = false;
        this.listId = listId;
    }
    public void setIsCompleted(boolean completed) {
        this.completed = completed;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getListId() {
        return listId;
    }
}

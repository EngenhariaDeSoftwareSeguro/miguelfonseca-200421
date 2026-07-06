package cncs.academy.ess.model;

public class MusicList {
    /** The id of the music list */
    private int id;
    /** The name of the music list */
    private String name;
    /** The id of the user that the music list belongs to */
    private int ownerId;
    // constructor, getters, setters
    public MusicList(int id, String name, int ownerId) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
    }
    public MusicList(String name, int ownerId) {
        this.name = name;
        this.ownerId = ownerId;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getListId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOwnerId() {
        return ownerId;
    }
}



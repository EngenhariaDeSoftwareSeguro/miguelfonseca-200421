package cncs.academy.ess.model;

public class Music {
    /** The id of the music item */
    private int id;
    /** The description of the music item */
    private String album;
    private String artist;
    /** The id of the list that the music item belongs to */
    private int listId;

    public Music(int id, String album, String artist,  int listId) {
        this.id = id;
        this.album = album;
        this.artist = artist;
        this.listId = listId;
    }
    public Music(String album, String artist, int listId) {
        this.album = album;
        this.artist = artist;
        this.listId = listId;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public int getListId() {
        return listId;
    }
}

package cncs.academy.ess.controller.messages;

public class MusicListAddResponse {
    public String name;
    public int userId;
    public int listId;
    public MusicListAddResponse(int listId, String name, int userId) {
        this.listId = listId;
        this.name = name;
        this.userId = userId;
    }
}

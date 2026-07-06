package cncs.academy.ess.controller.messages;

public class MusicListGetResponse {
    public int listId;
    public String name;
    public int userId;
    public MusicListGetResponse(int listId, String name, int userId) {
        this.listId = listId;
        this.name = name;
        this.userId = userId;
    }
}

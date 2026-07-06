package cncs.academy.ess.controller;

import cncs.academy.ess.controller.messages.ErrorMessage;
import cncs.academy.ess.controller.messages.MusicListAddRequest;
import cncs.academy.ess.controller.messages.MusicListAddResponse;
import cncs.academy.ess.controller.messages.MusicListGetResponse;
import cncs.academy.ess.model.MusicList;
import cncs.academy.ess.service.MusicListsService;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MusicListController {
    private static final Logger logger = LoggerFactory.getLogger(MusicListController.class);
    private final MusicListsService musicListService;

    public MusicListController(MusicListsService musicListService) {
        this.musicListService = musicListService;
    }

    public void createMusicList(Context ctx) {
        logger.info("Create music list item");
        MusicListAddRequest request = ctx.bodyAsClass(MusicListAddRequest.class);
        MusicList list = musicListService.createMusicListItem(request.listName, ctx.attribute("userId"));
        MusicListAddResponse response = new MusicListAddResponse(
            list.getListId(),
            list.getName(),
            list.getOwnerId()
        );
        ctx.status(201).json(response);
    }

    public void getMusicList(Context ctx) {
        logger.info("Get music list");
        int listId = Integer.parseInt(ctx.pathParam("listId"));
        int userId = ctx.attribute("userId");
        MusicList list = musicListService.getMusicList(listId);
        if (list == null) {
            ctx.status(404).json(new ErrorMessage("List not found"));
            return;
        }
        if (list.getOwnerId() != userId) {
            ctx.status(403).json(new ErrorMessage("Owner of list does not match user"));
            return;
        }
        MusicListGetResponse response = new MusicListGetResponse(
            list.getListId(),
            list.getName(),
            list.getOwnerId()
        );
        ctx.status(200).json(response);
    }

    public void getAllMusicLists(Context ctx) {
        logger.info("Get all music lists");
        int userId = ctx.attribute("userId");
        ctx.status(200).json(musicListService.getAllMusicLists(userId));
    }
}

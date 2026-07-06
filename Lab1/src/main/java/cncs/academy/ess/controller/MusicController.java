package cncs.academy.ess.controller;

import cncs.academy.ess.controller.messages.ErrorMessage;
import cncs.academy.ess.controller.messages.MusicAddRequest;
import cncs.academy.ess.model.Music;
import cncs.academy.ess.model.MusicList;
import cncs.academy.ess.service.MusicListsService;
import cncs.academy.ess.service.MusicService;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MusicController {
    private static final Logger log = LoggerFactory.getLogger(MusicController.class);

    private final MusicListsService musicListService;
    private final MusicService musicService;

    public MusicController(
            MusicService musicService,
            MusicListsService musicListService)
    {
        this.musicService = musicService;
        this.musicListService = musicListService;
    }

    public void createMusicItem(Context ctx) {
        MusicAddRequest request = ctx.bodyAsClass(MusicAddRequest.class);
        if (!checkOwnershipOfList(ctx, request.listId)) {
            return;
        }
        Music music = musicService.createMusicItem(request.album, request.artist, request.listId);
        ctx.status(200).json(music);
    }

    public void getMusicItem(Context ctx) {
        int musicId = Integer.parseInt(ctx.pathParam("musicId"));
        int listId = Integer.parseInt(ctx.pathParam("listId"));
        if (!checkOwnershipOfList(ctx, listId)) {
            return;
        }
        Music music = musicService.getMusicItem(musicId);
        if (music != null) {
            ctx.status(200).json(music);
        } else {
            ctx.status(404).result("Music not found");
        }
    }

    public void getAllMusicItems(Context ctx) {
        int listId = Integer.parseInt(ctx.pathParam("listId"));
        log.info("Getting all music items for list {}", listId);
        if (!checkOwnershipOfList(ctx, listId)) {
            return;
        }
        ctx.status(200).json(musicService.getAllMusicItemsByListId(listId));
    }

    private boolean checkOwnershipOfList(Context ctx, int listId) {
        int userId = ctx.attribute("userId");
        log.info("Checking ownership of list {} by user {}", listId, userId);
        MusicList list = musicListService.getMusicList(listId);
        if (list == null || list.getOwnerId() != userId) {
            log.error("User not owner of list");
            ctx.status(403).json(new ErrorMessage("User not owner of list"));
            return false;
        }
        return true;
    }

    public void deleteMusicItem(Context context) {
        int musicId = Integer.parseInt(context.pathParam("musicId"));
        int listId = Integer.parseInt(context.pathParam("listId"));
        if (!checkOwnershipOfList(context, listId)) {
            return;
        }
        if (musicService.deleteMusicItem(musicId)) {
            context.status(203);
        } else {
            context.status(404).json(new ErrorMessage("Music not found"));
        }
    }
}

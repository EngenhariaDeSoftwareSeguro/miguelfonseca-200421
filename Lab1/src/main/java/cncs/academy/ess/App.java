package cncs.academy.ess;

import cncs.academy.ess.controller.AuthorizationMiddleware;
import cncs.academy.ess.controller.MusicController;
import cncs.academy.ess.controller.MusicListController;
import cncs.academy.ess.controller.UserController;
import cncs.academy.ess.repository.memory.InMemoryMusicRepository;
import cncs.academy.ess.repository.memory.InMemoryMusicListsRepository;
import cncs.academy.ess.repository.memory.InMemoryUserRepository;
import cncs.academy.ess.service.MusicListsService;
import cncs.academy.ess.service.MusicUserService;
import cncs.academy.ess.service.MusicService;
import io.javalin.Javalin;

import java.security.NoSuchAlgorithmException;

public class App {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(it -> {
                    it.anyHost();
                });
            });
        }).start(7100);

        // Initialize routes for user management
        InMemoryUserRepository userRepository = new InMemoryUserRepository();
        MusicUserService userService = new MusicUserService(userRepository);
        UserController userController = new UserController(userService);

        InMemoryMusicListsRepository listsRepository = new InMemoryMusicListsRepository();
        MusicListsService muSicListService = new MusicListsService(listsRepository);
        MusicListController musicListController = new MusicListController(muSicListService);

        InMemoryMusicRepository musicRepository = new InMemoryMusicRepository();
        MusicService musicService = new MusicService(musicRepository, listsRepository);
        MusicController musicController = new MusicController(musicService, muSicListService);

        AuthorizationMiddleware authMiddleware = new AuthorizationMiddleware(userRepository);

        // CORS
        app.before(ctx -> {
            ctx.header("Access-Control-Allow-Origin", "*");
            ctx.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            ctx.header("Access-Control-Allow-Headers", "*");
        });
        // Authorization middleware
        app.before(authMiddleware::handle);

        // User management
        app.post("/user", userController::createUser);
        app.get("/user/{userId}", userController::getUser);
        app.delete("/user/{userId}", userController::deleteUser);
        app.post("/login", userController::loginUser);

        // "To do" lists management
        /* POST /musiclist
          {
              "listName": "Shopping list"
          }
         */
        app.post("/musiclist", musicListController::createMusicList);
        app.get("/musiclist", musicListController::getAllMusicLists);
        app.get("/musiclist/{listId}", musicListController::getMusicList);

        // "To do" list items management
        /* POST /music/item
          {
              "description": "Buy milk",
              "listId": 1
          }
         */
        app.post("/music/item", musicController::createMusicItem);

        /* GET /music/1/items */
        app.get("/music/{listId}/items", musicController::getAllMusicItems);
        /* GET /music/1/tasks/1 */
        app.get("/music/{listId}/items/{musicId}", musicController::getMusicItem);
        /* DELETE /music/1/tasks/1 */
        app.delete("/music/{listId}/items/{musicId}", musicController::deleteMusicItem);

        fillDummyData(userService, muSicListService, musicService);
    }

    private static void fillDummyData(
            MusicUserService userService,
            MusicListsService muSicListService,
            MusicService musicService) throws NoSuchAlgorithmException {
        userService.addUser("user1", "password1");
        userService.addUser("user2", "password2");
        muSicListService.createMusicListItem("Favorites", 1);
        muSicListService.createMusicListItem("New", 1);
        musicService.createMusicItem("Epica", "Aspiria",1);
        musicService.createMusicItem("Korn", "Follow The Leader",1);
        musicService.createMusicItem("Queen", "Inuendo",1);
    }
}

package cncs.academy.ess.repository;
import cncs.academy.ess.model.MusicList;

import java.util.List;

public interface MusicListsRepository {
    MusicList findById(int listId);
    List<MusicList> findAll();
    List<MusicList> findAllByUserId(int userId);
    int save(MusicList musicList);
    void update(MusicList musicList);
    boolean deleteById(int listId);
}

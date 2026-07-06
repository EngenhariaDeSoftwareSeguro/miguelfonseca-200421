package cncs.academy.ess.repository;

import cncs.academy.ess.model.Music;

import java.util.List;

public interface MusicRepository {
    Music findById(int musicId);
    List<Music> findAll();
    List<Music> findAllByListId(int listId);
    int save(Music music);
    void update(Music music);
    boolean deleteById(int musicId);
}

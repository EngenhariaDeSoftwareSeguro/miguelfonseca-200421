package cncs.academy.ess.repository.memory;

import cncs.academy.ess.model.Music;
import cncs.academy.ess.repository.MusicRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMusicRepository implements MusicRepository {
    private Map<Integer, Music> list = new HashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public Music findById(int musicId) {
        return list.get(musicId);
    }

    @Override
    public List<Music> findAll() {
        return list.values().stream().toList();
    }

    @Override
    public List<Music> findAllByListId(int listId) {
        return list.values().stream().filter(music -> music.getListId() == listId).toList();
    }

    public int save(Music music) {
        int id = music.getId();
        if (id == 0) {
            music.setId(id = currentId.incrementAndGet());
        }
        list.put(id, music);
        return id;
    }

    @Override
    public void update(Music music) {
        list.put(music.getId(), music);
    }

    @Override
    public boolean deleteById(int musicId) {
        return list.remove(musicId) != null;
    }

    public List<Music> findAll(int listId) {
        return list.values().stream().filter(music -> music.getListId() == listId).toList();
    }

}

package cncs.academy.ess.repository.memory;

import cncs.academy.ess.model.MusicList;
import cncs.academy.ess.repository.MusicListsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMusicListsRepository implements MusicListsRepository {
    private final ConcurrentHashMap<Integer, MusicList> allLists = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public MusicList findById(int listId) {
        return allLists.get(listId);
    }

    @Override
    public List<MusicList> findAll() {
        return new ArrayList<>(allLists.values());
    }

    @Override
    public List<MusicList> findAllByUserId(int userId) {
        return allLists.values().stream()
                .filter(musicList -> musicList.getOwnerId() == userId)
                .toList();
    }

    @Override
    public int save(MusicList musicList) {
        int id = musicList.getListId();
        if (id == 0) {
            musicList.setId(id=currentId.incrementAndGet());
        }
        allLists.put(id, musicList);
        return id;
    }

    @Override
    public void update(MusicList musicList) {
        allLists.put(musicList.getListId(), musicList);
    }

    @Override
    public boolean deleteById(int listId) {
        return allLists.remove(listId) != null;
    }
}
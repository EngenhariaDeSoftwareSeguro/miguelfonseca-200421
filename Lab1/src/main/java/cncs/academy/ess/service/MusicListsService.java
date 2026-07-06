package cncs.academy.ess.service;

import cncs.academy.ess.model.MusicList;
import cncs.academy.ess.repository.MusicListsRepository;

import java.util.Collection;

public class MusicListsService {
    MusicListsRepository musicListsRepository;

    public MusicListsService(MusicListsRepository musicListsRepository) {
        this.musicListsRepository = musicListsRepository;
    }

    public MusicList createMusicListItem(String listName, int ownerId) {
        MusicList list = new MusicList(listName, ownerId);
        int listId = musicListsRepository.save(list);
        list.setId(listId);
        return list;
    }
    public MusicList getMusicList(int listId) {
        return musicListsRepository.findById(listId);
    }
    public Collection<MusicList> getAllMusicLists(int userId) {
        return musicListsRepository.findAllByUserId(userId);
    }
}

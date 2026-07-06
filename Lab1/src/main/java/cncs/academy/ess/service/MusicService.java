package cncs.academy.ess.service;

import cncs.academy.ess.model.Music;
import cncs.academy.ess.repository.MusicListsRepository;
import cncs.academy.ess.repository.MusicRepository;

import java.util.List;

public class MusicService {
    private MusicRepository repoMusicRepository;
    private MusicListsRepository repoMusicListRepository;

    public MusicService(
            MusicRepository musicRepository,
            MusicListsRepository repoMusicListRepository)
    {
        this.repoMusicRepository = musicRepository;
        this.repoMusicListRepository = repoMusicListRepository;
    }

    public Music createMusicItem(String album, String artists, int listId) {
        if (repoMusicListRepository.findById(listId) == null) {
            throw new IllegalArgumentException("List not found");
        }
        Music music = new Music(album, artists, listId);
        int id = repoMusicRepository.save(music);
        music.setId(id);
        return music;
    }

    public Music getMusicItem(int musicId) {
        return repoMusicRepository.findById(musicId);
    }

    public List<Music> getAllMusicItemsByListId(int listId) {
        return repoMusicRepository.findAllByListId(listId);
    }

    public boolean deleteMusicItem(int musicId) {
        return repoMusicRepository.deleteById(musicId);
    }
}

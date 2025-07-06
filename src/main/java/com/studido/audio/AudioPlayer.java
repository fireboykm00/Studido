package com.studido.audio;

import com.studido.exception.Exceptions;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AudioPlayer {

    // Cache map: noteName -> audio byte[]
    private static final Map<String, byte[]> audioCache = new HashMap<>();

    private AudioPlayer() {} // Prevent instantiation

    /**
     * Preload all audio files into memory
     */
    public static void preloadAllNotes() {
        System.out.println("Preloading audio notes...");

        for (String noteName : FileManager.getAllNotesNames()) {
            String audioPath = FileManager.getPath(noteName);
            File file = new File(audioPath);

            if (!file.exists()) {
                System.err.printf("Missing audio file for note: %s\n", noteName);
                continue;
            }

            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] audioData = fis.readAllBytes();
                audioCache.put(noteName, audioData);
            } catch (IOException e) {
                System.err.printf("Failed to load note %s: %s\n", noteName, e.getMessage());
            }
        }

        System.out.printf("Loaded %d audio files into memory.\n", audioCache.size());
    }

    /**
     * Play audio from cache
     */
    public static void play(String noteName) throws Exceptions.NoteFileNotFoundExeption {
        byte[] audioBytes = audioCache.get(noteName);

        if (audioBytes == null) {
            throw new Exceptions.NoteFileNotFoundExeption("Note " + noteName + " is not cached or missing.");
        }

        System.out.printf("Playing note %s from cache.\n", noteName);

        try (BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(audioBytes))) {
            Player player = new Player(bis);
            player.play();
        } catch (JavaLayerException | IOException e) {
            throw new RuntimeException("Error playing note " + noteName, e);
        }
    }

}

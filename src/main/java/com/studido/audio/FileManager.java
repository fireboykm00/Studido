package com.studido.audio;

import com.studido.exception.Exceptions;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {


    // Get current working directory
    public static String BasePath = System.getProperty("user.dir");
    // Get resources directory
    public static final String ResourcesBasePath = Paths.get(BasePath, "src", "main", "resources").toString();
    // Get piano notes audio dir
    public static final String PianoNotesAudioDir = Paths.get(ResourcesBasePath, "audio", "piano", "notes").toString();

    public static final String NotesMapStoreFilename = "audio-names-store.std";

    public static final String MapsStoreDir = Paths.get(ResourcesBasePath, "maps").toString();

    /**
     * {audioFileName} should not contain extension
     * */
    static String getPath(String audioFileName) throws Exceptions.NoteFileNotFoundExeption {
        // simply getting file path
        // by explicitly adding file extension
        Path path = Paths.get(PianoNotesAudioDir, audioFileName + ".mp3");
        String pathToString = path.toString();
        // Throw an error file does not exist
        if (!Files.exists(path)) throw new Exceptions.NoteFileNotFoundExeption("Note file not found, Path: " + pathToString);
        return pathToString;
    }

    /**
     * Specify audio name and extension in case necessary
     * */
    static String getPath(String audioFileName, AudioTypes ext) throws Exceptions.NoteFileNotFoundExeption {
        // also get the audio by specifying the name and extension
        Path path = Paths.get(PianoNotesAudioDir, audioFileName + "." + ext);
        String pathToString = path.toString();

        if (!Files.exists(path)) throw new Exceptions.NoteFileNotFoundExeption("Note file not found, Path: " + pathToString);
        return pathToString;

    }

    /**
     * Maps all notes to store audio-names-store.txt
     * By iterating through the audio dir
     * */
    static void mapNotesToStore() {

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(PianoNotesAudioDir))) {

            Path mapPath = Paths.get(MapsStoreDir);
            // Ensure dir exists
            File audioNamesStore = ensureDir(mapPath);

            try (FileWriter fw = new FileWriter(audioNamesStore)) {
                writeNotesToStore(stream, fw);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    private static void writeNotesToStore(DirectoryStream<Path> stream, FileWriter fw) throws IOException {
        Iterator<Path> iterator = stream.iterator();

        try {
            while (iterator.hasNext()) {
                fw.write(iterator.next().getFileName().toString().trim().split("\\.")[0] + (iterator.hasNext() ? "\n" : ""));
            }
            System.out.println("Notes mapping success.");
        } catch (IOException e) {
            System.err.println("Notes mapping failed!");
            throw new RuntimeException(e);
        }
    }

    private static File ensureDir(Path mapPath) {
        // Create base directory if it does not exist
        if (!mapPath.toFile().exists()) if (mapPath.toFile().mkdir()) System.out.println("Directory Created");

        try {

            File audioNamesStore = new File(mapPath.toFile(), NotesMapStoreFilename);

            if (!audioNamesStore.exists()) {
                if (audioNamesStore.createNewFile()) {
                    System.out.println("New map store created!");
                }
            }
            return audioNamesStore;
        } catch (IOException e) {
            System.out.println("Map file creation failed!");
            throw new RuntimeException(e);
        }
    }

    public static Set<String> getAllNotesNames() {

        Path noteStorePath = Paths.get(MapsStoreDir, NotesMapStoreFilename);

        if (!Files.exists(noteStorePath)) throw new RuntimeException("Map store file not found!");

        Set<String> validNotes;

        try (Stream<String> lines = Files.lines(noteStorePath)) {
            validNotes = lines.map(String::trim).collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return validNotes;
    }
}
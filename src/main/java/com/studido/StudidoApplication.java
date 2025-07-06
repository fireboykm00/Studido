package com.studido;

import com.studido.audio.AudioPlayer;
import com.studido.audio.FileManager;
import com.studido.exception.Exceptions;

import java.util.*;
import javax.sound.midi.

public class StudidoApplication {

    public static class MusicThreadGroup extends ThreadGroup {

        public MusicThreadGroup(String name) {
            super(name);
        }

    }

    public static class MusicThread extends Thread {

        String noteName;

        MusicThread(String noteName) {
            this.noteName = noteName;
        }

        MusicThread(String noteName, MusicThreadGroup musicThreadGroup) {
            super(musicThreadGroup, musicThreadGroup.getName());
            this.noteName = noteName;
        }

        @Override
        public void run() {
            try {
                AudioPlayer.play(noteName); // blocking per note
            } catch (Exception e) {
                System.err.println("Error playing note " + noteName + ": " + e.getMessage());
            }

        }
    }

    private static final String welcomeMessage = """
            Welcome to STUDIDO!
            Make your own beats in terminal! 😂""";

    static Set<String> validNotes = FileManager.getAllNotesNames();
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        AudioPlayer.preloadAllNotes();

        loaderAnimations(20, 200);
        sayHello();
        loaderAnimations(20, 50);
        getNotesAndPlay();
    }

    static void loaderAnimations(int length, int delay) {
        String rect = "▬";
        try {
            for (int i = 0; i < length; i++) {
                System.out.print(rect);
                Thread.sleep(delay);
            }
            System.out.println();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static void playNotes(List<String> noteSteps) {

        // Get step duration time based on bpm
        final int bpm = 120;
        final int stepDurationMs = 60000 / bpm;

        // e.g., "C4+E4+G4"
        for (String step : noteSteps) {

            String[] notesInStep = step.split("\\+");

            Thread[] chordThreads = new Thread[notesInStep.length];

            for (int j = 0; j < notesInStep.length; j++) {
                final String note = notesInStep[j];
                chordThreads[j] = new MusicThread(note);
                chordThreads[j].start();
            }


            // ✅ Pause the thread stepDuration time for the tempo
            try {
                Thread.sleep(stepDurationMs);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static void getNotesAndPlay() {
        String inputNotes = getNotesInput();
        List<String> inputNotesLines = List.of(inputNotes.trim().split("\n"));

        List<List<String>> noteSteps = new ArrayList<>();

        try {
            for (String noteLines : inputNotesLines) {
                noteSteps.add(validatedAndExtractSteps(noteLines));
            }

        } catch (Exceptions.InvalidNoteException e) {
            System.err.println(e.getMessage() + " Try again!");
            getNotesAndPlay();
        }
        for (List<String> noteStep : noteSteps) {
            playNotes(noteStep);

        }
    }

    static String getNotesInput() {

        System.out.println("""
                Warning ⚠️: Notes A-G, Input space seperated, case sensitive, range 1-7, 0 for only A, Bb, B, ❌No Ab0 (F, C) doesn't have #/b notes>)
                             Format: name.number,  name.b.number, A0, G7, etc\s
                             Example: C3 E3 G3 C4+E4+G4 F3 D3
                \s""");

        System.out.println("Enter notes you would like to play: ");

        return scanner.nextLine();
    }

    static List<String> validatedAndExtractSteps(String notes) throws Exceptions.InvalidNoteException {


        List<String> extractedSteps = List.of(notes.trim().split(" "));

        for (String step : extractedSteps) {

            for (String note : step.split("\\+")) {
                if (!validNotes.contains(note)) {
                    throw new Exceptions.InvalidNoteException("Invalid note: " + note);
                }
            }
        }
        System.out.println();
        System.out.println(extractedSteps);
        System.out.println();
        return extractedSteps;
    }

    static void sayHello() {
        System.out.println(welcomeMessage);
    }
}
package com.studido.exception;

public class Exceptions {
    public static class InvalidNoteException extends Exception {
        public InvalidNoteException(String message) {
            super(message);
        }
    }

    public static class NoteFileNotFoundExeption extends RuntimeException {
        public NoteFileNotFoundExeption(String message) {
            super(message);
        }
    }

}

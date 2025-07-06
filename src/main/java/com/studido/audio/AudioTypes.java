package com.studido.audio;

import javax.sound.sampled.AudioSystem;

public enum AudioTypes {
    // all audio types
    MP3("mp3"), WAV("wav"), OOB("oob");

    final String type;

    AudioTypes(String type) {
      this.type = type;
    }

    public String getType() {
        return type;
    }
}

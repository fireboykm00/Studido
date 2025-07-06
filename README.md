# 🎹 Studido — A Terminal-Based Music Studio

**Studido** is a minimalist **CLI-based music sequencer** that lets you compose and play music by entering piano notes directly into your terminal. Play single notes, chords, or entire melodies using a simple text-based format.

> Think of it as your own mini GarageBand... in the terminal!

---

## 🚀 Features

- 🎵 Play piano notes using your own audio files
- ⌨️ Write music using text (e.g. `C4 E4 G4` or `C4+E4+G4`)
- 🧠 Supports chords (using `+`) and sequences (using spaces)
- 🧠 Tempo-based playback using BPM
- ⚡ Preloads audio into memory for smooth playback
- 🎼 Comes with example melodies (like Happy Birthday, Für Elise, etc.)

---

## 🛠️ Getting Started

### 📦 Requirements

- Java 17+ installed
- MP3 files of piano notes placed in your `resources/audio/` directory  
  (e.g. `C4.mp3`, `Eb3.mp3`, etc.)
- [JLayer](http://www.javazoom.net/javalayer/javalayer.html) library (for MP3 playback)

---

### ▶️ Running the Program

From terminal:
```bash
java -jar studido.jar
```

Then follow the prompt to enter notes like:
```
C4 E4 G4 C5+E5+G5 F4 D4
```


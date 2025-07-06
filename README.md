# 🎹 Studido — A Terminal-Based Music Studio

**Studido** is a minimalist **CLI-based music sequencer** that lets you compose and play music by entering piano notes directly into your terminal. Play single notes, chords, or entire melodies using a simple text-based format.

> Think of it as your own mini GarageBand... in the terminal!


⚠️ Contributions are allowed! Maybe make it even more real.
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



### 📁 Project Structure

```

src/
├── com/studido/
│   ├── StudidoApplication.java   # Main class
│   ├── audio/
│   │   ├── AudioPlayer.java
│   │   └── FileManager.java
│   └── exception/
│       └── Exceptions.java
resources/
├── audio/                        # Place your MP3 files here
├── maps/                         # Automatically created note mappings

````


## 🧠 Note Syntax

| Type     | Example          | Meaning                          |
| -------- | ---------------- | -------------------------------- |
| Single   | `C4`             | Play a single note               |
| Chord    | `C4+E4+G4`       | Play multiple notes **together** |
| Sequence | `C4 E4 G4`       | Play notes one after another     |
| Combined | `C4 E4 G4 C5+E5` | Sequence and chord mixed         |

---

## 🧪 Sample Songs (Try These)

### 🎵 Für Elise

```
E5 Db5 E5 Db5 E5 B4 D5 C5 A4
C4 E4 A4 B4
E4 Gb4 B4 C5
E4 E5 Db5 E5 Db5 E5 B4 D5 C5 A4
```

### 🎉 Happy Birthday

```
C4 C4 D4 C4 F4 E4
C4 C4 D4 C4 G4 F4
C4 C4 C5 A4 F4 E4 D4
Bb4 Bb4 A4 F4 G4 F4
```


## 📌 TODOs & Ideas

* [ ] Add support for rests (`_`)
* [ ] Allow setting custom BPM from input
* [ ] Note duration syntax (e.g. `C4:250`)
* [ ] Save and load song files (`.std`)
* [ ] Loop patterns for beat making

---

## 💡 Credits

* Developed with love by [Kwizera Mugisha Olivier](https://github.com/your-username)
* MP3 playback powered by [JLayer](http://www.javazoom.net/javalayer/javalayer.html)

---

## 📜 License

MIT License. Use it, hack it, remix it. Just don’t use it to annoy your neighbors with terminal techno. 😄


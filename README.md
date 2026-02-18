> [!CAUTION]
> ## Archived
> This project is archived and no longer maintained.
>
> Java-based neural network libraries have been superseded by modern Python ML frameworks.

<div align="center">
  <img src="logo.png" alt="neuralj" width="512"/>

  [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
  [![Language: Java](https://img.shields.io/badge/Language-Java-orange.svg)](https://www.java.com)
  [![Status: Archived](https://img.shields.io/badge/Status-Archived-red.svg)](https://github.com/tsilva/neuralj)

  **🧠 Feedforward neural networks for Java — train, generalize, and serialize with ease 📦**

</div>

---

## Overview

neuralj is a Java library for building and training feedforward neural networks. It supports multiple learning algorithms, a clean pattern dataset API, real-time training watchers, and a Swing-based GUI trainer — all in a single self-contained library.

**The Pain:** Building neural networks in Java meant either wrestling with bloated frameworks or writing everything from scratch.

**The Solution:** neuralj wraps the full training pipeline — network construction, dataset management, learning algorithms, and monitoring — in a clean, minimal API.

**The Result:** Train a network on XOR in under 15 lines of code.

## ✨ Features

- ⚡ **Multiple learning algorithms** — BackPropagation, Resilient BackPropagation (RProp), Genetic
- 🎯 **Two learning strategies** — Memorize (overfit) and Generalize (train/validation/test splits)
- 📊 **Pattern dataset API** — load from CSV, auto-generate train/validation/test splits
- 👁️ **Watcher system** — monitor training via console, file, or custom watchers
- 🖥️ **GUI trainer** — Swing-based interface for interactive training
- 💾 **Serialization** — save and load trained networks
- 🔧 **Pluggable architecture** — extend with custom condition checkers, watchers, and testers

## 🚀 Quick Start

### Train XOR with BackPropagation

```java
// Create the pattern set
PatternSet patternSet = new PatternSet();
Pattern.split_token = ";";
patternSet.addPattern(new Pattern("0;0", "0"));
patternSet.addPattern(new Pattern("1;0", "1"));
patternSet.addPattern(new Pattern("0;1", "1"));
patternSet.addPattern(new Pattern("1;1", "0"));

// Build a 2-3-1 network and train with BackPropagation
FeedForwardNeuralNetwork net = new FeedForwardNeuralNetwork(2, new int[]{3}, 1);
BackPropagation bp = new BackPropagation(net);
bp.pattern_set = patternSet;
bp.learning_strategy = LearningStrategy.Memorize;
bp.desired_error = 0.001;
bp.watcher = new ConsoleWatcher(bp);
bp.start();
```

### Generalize from CSV with RProp

```java
PatternSet patternSet = new PatternSet();
Pattern.split_token = ",";
patternSet.loadPatterns("data.csv", 4);
patternSet.generateSets(); // shuffles into train/validation/test

FeedForwardNeuralNetwork net = new FeedForwardNeuralNetwork(4, new int[]{4, 2}, 2);
ResilientBackPropagation rp = new ResilientBackPropagation(net);
rp.pattern_set = patternSet;
rp.learning_strategy = LearningStrategy.Generalization;
rp.desired_error = 0.001;
rp.watcher = new ConsoleWatcher(rp);
rp.start();
```

## 🏗️ Architecture

| Component | Description |
|-----------|-------------|
| `neuralj-lib` | Core library — networks, datasets, learning algorithms |
| `neuralj-gui` | Swing-based GUI trainer |
| `neuralj-samples` | Example programs (XOR, generalization) |

### Package Structure

```
neuralj/
├── networks/feedforward/          # FeedForwardNeuralNetwork
│   └── learning/
│       ├── bprop/                 # BackPropagation
│       ├── rprop/                 # ResilientBackPropagation
│       └── genetic/               # Genetic algorithm
├── datasets/                      # Pattern, PatternSet
├── watchers/                      # ConsoleWatcher, FileWatcher, BlindWatcher
├── testers/                       # ConsoleTester, StringTester
├── conditioncheckers/             # Training stop conditions
└── Serializer.java                # Network save/load
```

## 📦 Building

This project uses Apache Ant. Build from the project root:

```bash
# Build the library
cd neuralj-lib && ant jar

# Build the GUI
cd neuralj-gui && ant jar
```

A pre-built Windows executable is also included: `neuralj-0.0.4.exe`

## 📖 Samples

| Sample | Algorithm | Strategy |
|--------|-----------|---------|
| `MemorizeXORBackProp` | BackPropagation | Memorize |
| `GeneralizeMostPopularRProp` | Resilient BackPropagation | Generalization |
| `GeneralizeMostPopularGenetic` | Genetic | Generalization |

Run a sample after building:

```bash
cd neuralj-samples
java -cp .:../neuralj-lib/dist/neuralj.jar samples.MemorizeXORBackProp
```

## 📄 License

MIT — see [LICENSE](LICENSE) for details.

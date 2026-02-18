> [!CAUTION]
> ## Archived
> This project is archived and no longer maintained.
>
> Java-based neural network libraries have been superseded by modern Python ML frameworks.
> This repository is archived and no longer maintained.

<div align="center">

<img src="logo.png" alt="neuralj" width="512"/>

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Language-Java-orange.svg)](https://www.java.com/)
[![Status: Archived](https://img.shields.io/badge/Status-Archived-red.svg)](https://github.com/tsilva/neuralj)

**🧠 Open-source neural network library for Java — archived college project 📚**

</div>

---

## Overview

**neuralj** is a Java neural network library built from scratch during college. It implements feedforward networks with multiple learning algorithms — backpropagation, RProp, and genetic algorithms — along with dataset management, serialization, training watchers, and a Swing-based GUI trainer.

> **Note:** This project was discontinued and is only made available for reference purposes. It was started back in college, and skills and tooling have come a long way since then.

## Structure

| Module | Description |
|--------|-------------|
| `neuralj-lib` | Core library — networks, learning algorithms, datasets, math utilities |
| `neuralj-gui` | Swing GUI for interactive network training |
| `neuralj-samples` | Runnable examples (XOR, generalization, genetic algorithm) |

## Features

- **Feedforward neural networks** — configurable input, hidden, and output layer sizes
- **BackPropagation** — gradient descent training with configurable error threshold and epochs
- **RProp** — resilient propagation for faster convergence
- **Genetic Algorithm** — evolutionary training with roulette wheel selection, crossover, and random mutation
- **Dataset management** — `Pattern` and `PatternSet` with memorize / generalization strategies
- **Serialization** — save and reload trained networks to disk
- **Watchers** — `ConsoleWatcher`, `FileWatcher`, `BlindWatcher` for training monitoring
- **Testers** — `ConsoleTester` and `StringTester` for evaluating trained models

## Quick Example

Train a feedforward network to learn XOR using backpropagation:

```java
// Build the pattern set
PatternSet patterns = new PatternSet();
Pattern.split_token = ";";
patterns.addPattern(new Pattern("0;0", "0"));
patterns.addPattern(new Pattern("1;0", "1"));
patterns.addPattern(new Pattern("0;1", "1"));
patterns.addPattern(new Pattern("1;1", "0"));

// Create a 2-3-1 network
FeedForwardNeuralNetwork net = new FeedForwardNeuralNetwork(2, new int[] { 3 }, 1);

// Configure and start BackPropagation
BackPropagation bp = new BackPropagation(net);
bp.pattern_set = patterns;
bp.learning_strategy = LearningStrategy.Memorize;
bp.desired_error = 0.001;
bp.maximum_epochs = 1000000000;
bp.watcher = new ConsoleWatcher(bp);
bp.is_running = true;
bp.start();

// Wait for training to finish
while (bp.is_running) Thread.sleep(1000);

// Evaluate
new ConsoleTester(bp.network).test(patterns);
```

## License

MIT

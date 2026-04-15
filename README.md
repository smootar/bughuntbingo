# Bug Hunt Bingo

A team activity where engineers hunt for bugs in a React calculator app. Find bugs, fix them, and mark off bingo squares to win!

## Overview

- A **buggy calculator app** with 10 intentionally planted bugs (3 easy, 4 medium, 3 hard)
- A **fixed version** with passing tests for reference
- **Bingo cards** that challenge teams to find bugs using different techniques
- An **answer key** for the facilitator

## Quick Start

### For Participants

```bash
npm install
npm run dev
```

This starts the **buggy** calculator at `http://localhost:5173`. Your job: find and fix the bugs!

### For Facilitators

```bash
# Start the fixed version (to verify expected behavior)
npm run dev:fixed

# Run the test suite (against the fixed version)
npm run test

# Open bingo cards for printing
open facilitator/bingo-cards.html

# Review the answer key
open facilitator/answer-key.md
```

## Project Structure

```
bughuntbingo/
├── buggy/              # The buggy calculator (participants work here)
│   ├── components/     # React components
│   └── utils/          # Utility functions (pure math + formatting)
├── fixed/              # The working calculator (facilitator reference)
│   ├── components/
│   ├── utils/
│   └── __tests__/      # Vitest test suite
├── facilitator/
│   ├── answer-key.md   # All 10 bugs documented with fixes
│   └── bingo-cards.html # Printable 3x3 bingo cards (6 variations)
└── README.md
```

## Activity Setup (5 minutes)

1. Clone the repo and run `npm install`
2. Print bingo cards: open `facilitator/bingo-cards.html` and print (2 cards per page, 6 variations)
3. Assign teams (2-4 people each) and distribute one card per team
4. Verify the buggy app runs: `npm run dev`

## Activity Flow

### Round 1: Hunt (15 minutes)
- Teams run the buggy calculator and explore the code
- Use any tools: Claude Code, grep, browser DevTools, reading code, etc.
- When you fix a bug, mark off a matching bingo square
- Each bug can only count for ONE square
- First team to get BINGO (row, column, or diagonal) wins!

### Round 2: Share (5-10 minutes)
- Winning team explains their approach
- Other teams share interesting bugs they found
- Facilitator highlights different techniques used (see bingo squares)

## Calculator Features

The calculator includes:
- Basic arithmetic: +, -, x, /
- Equals, Clear (C), Backspace
- Decimal point
- Percentage (%)
- Memory: M+, M-, MR, MC
- History panel showing recent calculations

## Bug Difficulty Distribution

| Difficulty | Count | Hint |
|-----------|-------|------|
| Easy | 3 | Obvious from basic usage or quick code scan |
| Medium | 4 | Requires testing specific features or edge cases |
| Hard | 3 | Requires careful reasoning about state or input handling |

## NPM Scripts

| Command | Description |
|---------|-------------|
| `npm run dev` | Start buggy version (default for participants) |
| `npm run dev:fixed` | Start fixed version (facilitator reference) |
| `npm run test` | Run test suite against fixed version |
| `npm run test:watch` | Run tests in watch mode |
| `npm run build` | Build buggy version for production |
| `npm run build:fixed` | Build fixed version for production |

## Variations

- **Easier:** Tell teams how many bugs are in each file
- **Harder:** Don't tell teams how many total bugs there are
- **Collaborative:** One shared bingo card, all teams contribute
- **Speed run:** First team to find any 5 bugs wins (no bingo card needed)

## Tech Stack

- [Vite](https://vite.dev/) + [React](https://react.dev/)
- [Vitest](https://vitest.dev/) + [React Testing Library](https://testing-library.com/react)

package ch.juventus.solver;

import ch.juventus.domain.Puzzle;

public interface PuzzleSolver<T extends Puzzle> {

  boolean solve(T puzzle);
}

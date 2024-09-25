package gui;

import math.CompositeLabeledDouble;

/**
 * Interface for subjects that provide grade histories. Implementing classes must provide a method
 * to return a CompositeLabeledDouble representing the history of grades.
 */
public interface CompositeGradeSubject
{
  /**
   * Returns a CompositeLabeledDouble that contains the history of grades.
   * 
   * @return CompositeLabeledDouble object representing grade history.
   */
  CompositeLabeledDouble getGradeHistory();
}

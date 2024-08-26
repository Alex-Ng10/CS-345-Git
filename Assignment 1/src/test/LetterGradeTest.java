package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import grading.LetterGrade;
import math.LabeledDouble;

class LetterGradeTest {

	@Test
	void testFromCode() {
		assertAll(() -> assertEquals(LetterGrade.A, LetterGrade.fromCode("A")),
				() -> assertEquals(LetterGrade.AMINUS, LetterGrade.fromCode("A-")),
				() -> assertEquals(LetterGrade.BPLUS, LetterGrade.fromCode("B+")),
				() -> assertEquals(LetterGrade.B, LetterGrade.fromCode("B")),
				() -> assertEquals(LetterGrade.BMINUS, LetterGrade.fromCode("B-")),
				() -> assertEquals(LetterGrade.CPLUS, LetterGrade.fromCode("C+")),
				() -> assertEquals(LetterGrade.C, LetterGrade.fromCode("C")),
				() -> assertEquals(LetterGrade.CMINUS, LetterGrade.fromCode("C-")),
				() -> assertEquals(LetterGrade.DPLUS, LetterGrade.fromCode("D+")),
				() -> assertEquals(LetterGrade.D, LetterGrade.fromCode("D")),
				() -> assertEquals(LetterGrade.DMINUS, LetterGrade.fromCode("D-")),
				() -> assertEquals(LetterGrade.F, LetterGrade.fromCode("F")),
				() -> assertEquals(null, LetterGrade.fromCode(null)));
	}

	@Test
	void testGetLabel() {
		assertAll(() -> assertEquals("A", LetterGrade.A.getLabel()),
				() -> assertEquals("A-", LetterGrade.AMINUS.getLabel()),
				() -> assertEquals("B+", LetterGrade.BPLUS.getLabel()),
				() -> assertEquals("B", LetterGrade.B.getLabel()),
				() -> assertEquals("B-", LetterGrade.BMINUS.getLabel()),
				() -> assertEquals("C+", LetterGrade.CPLUS.getLabel()),
				() -> assertEquals("C", LetterGrade.C.getLabel()),
				() -> assertEquals("C-", LetterGrade.CMINUS.getLabel()),
				() -> assertEquals("D+", LetterGrade.DPLUS.getLabel()),
				() -> assertEquals("D", LetterGrade.D.getLabel()),
				() -> assertEquals("D-", LetterGrade.DMINUS.getLabel()),
				() -> assertEquals("F", LetterGrade.F.getLabel()));
	}

	@Test
	void testGetValue() {
		assertAll(() -> assertEquals(4.0, LetterGrade.A.getValue()),
				() -> assertEquals(3.7, LetterGrade.AMINUS.getValue()),
				() -> assertEquals(3.3, LetterGrade.BPLUS.getValue()),
				() -> assertEquals(3.0, LetterGrade.B.getValue()),
				() -> assertEquals(2.7, LetterGrade.BMINUS.getValue()),
				() -> assertEquals(2.3, LetterGrade.CPLUS.getValue()),
				() -> assertEquals(2.0, LetterGrade.C.getValue()),
				() -> assertEquals(1.7, LetterGrade.CMINUS.getValue()),
				() -> assertEquals(1.3, LetterGrade.DPLUS.getValue()),
				() -> assertEquals(1.0, LetterGrade.D.getValue()),
				() -> assertEquals(0.7, LetterGrade.DMINUS.getValue()),
				() -> assertEquals(0.0, LetterGrade.F.getValue()));
	}

	@Test
	void testToString() {
//		assertAll(() -> assertEquals("F (0.0)", LetterGrade.F.toString()),
//	    () -> assertEquals("D- (0.7)", LetterGrade.DMINUS.toString()),
//	    () -> assertEquals("D (1.0)", LetterGrade.D.toString()),
//	    () -> assertEquals("D+ (1.3)", LetterGrade.DPLUS.toString()),
//	    () -> assertEquals("C- (1.7)", LetterGrade.CMINUS.toString()),
//	    () -> assertEquals("C (2.0)", LetterGrade.C.toString()),
//	    () -> assertEquals("C+ (2.3)", LetterGrade.CPLUS.toString()),
//	    () -> assertEquals("B- (2.7)", LetterGrade.BMINUS.toString()),
//	    () -> assertEquals("B (3.0)", LetterGrade.B.toString()),
//	    () -> assertEquals("B+ (3.3)", LetterGrade.BPLUS.toString()),
//	    () -> assertEquals("A- (3.7)", LetterGrade.AMINUS.toString()),
//	    () -> assertEquals("A (4.0)", LetterGrade.A.toString()));
		assertEquals("F  (0,0)", LetterGrade.F.toString());
	    assertEquals("D- (0.7)", LetterGrade.DMINUS.toString());
	    assertEquals("D  (1.0)", LetterGrade.D.toString());
	    assertEquals("D+ (1.3)", LetterGrade.DPLUS.toString());
	    assertEquals("C- (1.7)", LetterGrade.CMINUS.toString());
	    assertEquals("C  (2.0)", LetterGrade.C.toString());
	    assertEquals("C+ (2.3)", LetterGrade.CPLUS.toString());
	    assertEquals("B- (2.7)", LetterGrade.BMINUS.toString());
	    assertEquals("B  (3.0)", LetterGrade.B.toString());
	    assertEquals("B+ (3.3)", LetterGrade.BPLUS.toString());
	    assertEquals("A- (3.7)", LetterGrade.AMINUS.toString());
	    assertEquals("A  (4.0)", LetterGrade.A.toString());
	}

}
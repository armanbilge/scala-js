/*                     __                                               *\
**     ________ ___   / /  ___      __ ____  Scala.js Test Suite        **
**    / __/ __// _ | / /  / _ | __ / // __/  (c) 2013, LAMP/EPFL        **
**  __\ \/ /__/ __ |/ /__/ __ |/_// /_\ \    http://scala-js.org/       **
** /____/\___/_/ |_/____/_/ | |__/ /____/                               **
**                          |/____/                                     **
\*                                                                      */
package org.scalajs.testsuite.scalalib

import org.junit.Test
import org.junit.Assert._
import org.junit.Assume._

import scala.collection.immutable.NumericRange

import org.scalajs.testsuite.utils.Platform._

class RangesTest {

  @Test def Iterable_range_should_not_emit_dce_warnings_issue_650(): Unit = {
    Iterable.range(1, 10)
  }

  @Test def Iterable_range_and_simple_range_should_be_equal(): Unit = {
    // Mostly to exercise more methods of ranges for dce warnings
    assertEquals((0 until 10).toList, Iterable.range(0, 10).toList)
  }

  @Test def Iterable_range_bug_on_floating_points_issue_1974(): Unit = {
    val range = 0.0 to 6.283 by 1.0

    assertEquals(0.0, range.start, 0.0)
    assertEquals(6.283, range.end, 0.0)
    assertEquals(1.0, range.step, 0.0)
    assertTrue(range.isInclusive)

    assertEquals(0.0, range.head, 0.0)
    assertEquals(6.0, range.last, 0.0)
    assertEquals(7, range.length)

    assertEquals(List(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0), range.toList)
  }

  @Test def NumericRange_overflow_issue_2407(): Unit = {
    val nr = NumericRange(Int.MinValue, Int.MaxValue, 1 << 23)
    assertEquals(Int.MinValue, nr.sum)
  }

  @Test def Range_foreach_issue_2409(): Unit = {
    assumeFalse(executingInJVM && scalaVersion.startsWith("2.10."))
    val r = Int.MinValue to Int.MaxValue by (1 << 23)
    var i = 0
    r.foreach(_ => i += 1)
    assertEquals(512, i)
    assertEquals(512, r.length)
    assertEquals(Int.MinValue, r.sum)
  }
}

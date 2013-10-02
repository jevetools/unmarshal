/*
 * Copyright (c) 2013, jEVETools
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the author nor the names of the contributors
 *       may be used to endorse or promote products derived from this software
 *       without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.jevetools.unmarshal.python.api.impl.test; //NOPMD

import static com.jevetools.unmarshal.python.api.impl.test.OrderingComparison.comparesEqualTo;
import static com.jevetools.unmarshal.python.api.impl.test.OrderingComparison.greaterThan;
import static com.jevetools.unmarshal.python.api.impl.test.OrderingComparison.lessThan;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.jevetools.unmarshal.python.api.PyBase;
import com.jevetools.unmarshal.python.api.PyString;
import com.jevetools.unmarshal.python.api.PyFactory;
import com.jevetools.unmarshal.python.api.PyNone;
import com.jevetools.unmarshal.python.api.PyVisitor;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public final class PyStringTest
    extends AbstractPyBaseTest
{
  /**
   * Test method for {@link PyString#value()}.
   */
  @Test
  public void testDefault()
  {
    final PyFactory.PyStringBuilder builder = getPyFactory()
        .getPyStringBuilder();

    final PyString testSubject = builder.build();

    assertThat(testSubject, not(nullValue()));
    assertThat(testSubject.value(), equalTo(""));
    assertThat(testSubject, equalTo(getPyFactory().getPyString()));
    assertThat(testSubject.toString(), equalTo(""));
  }

  /**
   * Test method for {@link PyString#value()}.
   */
  @Test
  public void testValueOne()
  {
    final PyFactory.PyStringBuilder builder = getPyFactory()
        .getPyStringBuilder();

    builder.value("a");

    final PyString testSubject = builder.build();

    assertThat(testSubject, not(nullValue()));
    assertThat(testSubject.value(), equalTo("a"));
    assertThat(testSubject.toString(), equalTo("a"));
  }

  /**
   * Test method for {@link PyString#value()}.
   */
  @Test
  public void testValueTwo()
  {
    final PyFactory.PyStringBuilder builder = getPyFactory()
        .getPyStringBuilder();

    builder.value("ab");

    final PyString testSubject = builder.build();

    assertThat(testSubject, not(nullValue()));
    assertThat(testSubject.value(), equalTo("ab"));
    assertThat(testSubject.toString(), equalTo("ab"));
  }

  /**
   * Test method for {@link PyString#hashCode()}.
   */
  @Test
  public void testHashCode()
  {
    final PyFactory.PyStringBuilder builder = getPyFactory()
        .getPyStringBuilder();

    builder.value("ab");

    final PyString testSubject11 = builder.build();
    final PyString testSubject12 = builder.build();

    builder.value("ba");

    final PyString testSubject21 = builder.build();
    final PyString testSubject22 = builder.build();

    assertThat(testSubject11.hashCode(), equalTo(testSubject12.hashCode()));
    assertThat(testSubject21.hashCode(), equalTo(testSubject22.hashCode()));

    assertThat(testSubject11.hashCode(), not(equalTo(testSubject22.hashCode())));
  }

  /**
   * Test method for {@link PyString#getPyType()}.
   */
  @Test
  public void testGetPyType()
  {
    final PyFactory.PyStringBuilder builder = getPyFactory()
        .getPyStringBuilder();

    final PyString testSubject = builder.build();

    assertThat(testSubject, not(nullValue()));
    assertThat(testSubject.getPyType(), equalTo(PyBase.PyType.STRING));
    assertThat(testSubject.isString(), not(false));
  }

  /**
   * Test method for {@link PyString#equals()}.
   */
  @Test
  public void testEquals()
  {
    final PyFactory.PyStringBuilder builder = getPyFactory()
        .getPyStringBuilder();
    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    builder.value("ab");

    final PyNone none = builderNone.build();

    final PyString testSubject11 = builder.build();
    final PyString testSubject12 = builder.build();

    builder.value("ba");

    final PyString testSubject21 = builder.build();
    final PyString testSubject22 = builder.build();

    assertThat(testSubject11, equalTo(testSubject12));
    assertThat(testSubject21, equalTo(testSubject22));

    assertThat(testSubject11, not(equalTo(testSubject22)));

    assertThat(((PyBase) testSubject11), not(equalTo(((PyBase) none))));
  }

  /**
   * Test method for {@link PyString#accept(PyVisitor)}.
   */
  @Test
  public void testAccept()
  {
    final PyFactory.PyStringBuilder builder = getPyFactory()
        .getPyStringBuilder();

    final PyString testSubject = builder.build();

    assertThat(testSubject, not(nullValue()));

    final PyVisitor visitor = new TestVisitor(testSubject);

    testSubject.accept(visitor);
  }

  /**
   * Test method for {@link PyString#compareTo(PyBase)}.
   */
  @Test
  public void testCompareTo()
  {
    final PyFactory.PyStringBuilder builder = getPyFactory()
        .getPyStringBuilder();
    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    builder.value("ab");

    final PyNone none = builderNone.build();

    final PyString testSubject11 = builder.build();
    final PyString testSubject12 = builder.build();

    builder.value("ba");

    final PyString testSubject21 = builder.build();
    final PyString testSubject22 = builder.build();

    assertThat(((PyBase) testSubject11),
        comparesEqualTo((PyBase) testSubject12));
    assertThat(((PyBase) testSubject21),
        comparesEqualTo((PyBase) testSubject22));

    assertThat(((PyBase) testSubject11), lessThan((PyBase) testSubject21));
    assertThat(((PyBase) testSubject21), greaterThan((PyBase) testSubject11));

    assertThat(((PyBase) testSubject11), not(comparesEqualTo(((PyBase) none))));
  }

}

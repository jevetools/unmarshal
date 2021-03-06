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
import com.jevetools.unmarshal.python.api.PyBool;
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
public final class PyBoolTest
    extends AbstractPyBaseTest
{
  /**
   * Test method for {@link PyBool#value()}.
   */
  @Test
  public void testDefault()
  {
    final PyFactory.PyBoolBuilder builder = getPyFactory().getPyBoolBuilder();

    final PyBool testSubject = builder.build();

    assertThat(testSubject, not(nullValue()));
    assertThat(testSubject.value(), equalTo(false));
    assertThat(testSubject, equalTo(getPyFactory().getPyBoolFalse()));
    assertThat(testSubject.toString(), equalTo(Boolean.toString(false)));
  }

  /**
   * Test method for {@link PyBool#value()}.
   */
  @Test
  public void testValueOne()
  {
    final PyFactory.PyBoolBuilder builder = getPyFactory().getPyBoolBuilder();

    builder.value(true);

    final PyBool testSubject = builder.build();

    assertThat(testSubject, not(nullValue()));
    assertThat(testSubject.value(), equalTo(true));
    assertThat(testSubject, equalTo(getPyFactory().getPyBoolTrue()));
    assertThat(testSubject.toString(), equalTo(Boolean.toString(true)));
  }

  /**
   * Test method for {@link PyBool#value()}.
   */
  @Test
  public void testValueTwo()
  {
    final PyFactory.PyBoolBuilder builder = getPyFactory().getPyBoolBuilder();

    builder.value(false);

    final PyBool testSubject = builder.build();

    assertThat(testSubject, not(nullValue()));
    assertThat(testSubject.value(), equalTo(false));
    assertThat(testSubject.toString(), equalTo(Boolean.toString(false)));
  }

  /**
   * Test method for {@link PyBool#hashCode()}.
   */
  @Test
  public void testHashCode()
  {
    final PyFactory.PyBoolBuilder builder = getPyFactory().getPyBoolBuilder();

    builder.value(false);

    final PyBool testSubject11 = builder.build();
    final PyBool testSubject12 = builder.build();

    builder.value(true);

    final PyBool testSubject21 = builder.build();
    final PyBool testSubject22 = builder.build();

    assertThat(testSubject11.hashCode(), equalTo(testSubject12.hashCode()));
    assertThat(testSubject21.hashCode(), equalTo(testSubject22.hashCode()));

    assertThat(testSubject11.hashCode(), not(equalTo(testSubject22.hashCode())));
  }

  /**
   * Test method for {@link PyBool#getPyType()}.
   */
  @Test
  public void testGetPyType()
  {
    final PyFactory.PyBoolBuilder builder = getPyFactory().getPyBoolBuilder();

    final PyBool testSubject = builder.build();

    assertThat(testSubject, not(nullValue()));
    assertThat(testSubject.isBool(), not(false));
    assertThat(testSubject.getPyType(), equalTo(PyBase.PyType.BOOL));
    assertThat(testSubject.asBool(), not(nullValue()));
  }

  /**
   * Test method for {@link PyBool#equals()}.
   */
  @Test
  public void testEquals()
  {
    final PyFactory.PyBoolBuilder builder = getPyFactory().getPyBoolBuilder();
    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    builder.value(false);

    final PyNone none = builderNone.build();

    final PyBool testSubject11 = builder.build();
    final PyBool testSubject12 = builder.build();

    builder.value(true);

    final PyBool testSubject21 = builder.build();
    final PyBool testSubject22 = builder.build();

    assertThat(testSubject11, equalTo(testSubject12));
    assertThat(testSubject21, equalTo(testSubject22));

    assertThat(testSubject11, not(equalTo(testSubject22)));

    assertThat(((PyBase) testSubject11), not(equalTo(((PyBase) none))));
  }

  /**
   * Test method for {@link PyBool#accept(PyVisitor)}.
   */
  @Test
  public void testAccept()
  {
    final PyFactory.PyBoolBuilder builder = getPyFactory().getPyBoolBuilder();

    final PyBool testSubject = builder.build();

    assertThat(testSubject, not(nullValue()));

    final PyVisitor visitor = new TestVisitor(testSubject);

    testSubject.accept(visitor);
  }

  /**
   * Test method for {@link PyBool#compareTo(PyBase)}.
   */
  @Test
  public void testCompareTo()
  {
    final PyFactory.PyBoolBuilder builder = getPyFactory().getPyBoolBuilder();
    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    builder.value(false);

    final PyNone none = builderNone.build();

    final PyBool testSubject11 = builder.build();
    final PyBool testSubject12 = builder.build();

    builder.value(true);

    final PyBool testSubject21 = builder.build();
    final PyBool testSubject22 = builder.build();

    assertThat(((PyBase) testSubject11),
        comparesEqualTo((PyBase) testSubject12));
    assertThat(((PyBase) testSubject21),
        comparesEqualTo((PyBase) testSubject22));

    assertThat(((PyBase) testSubject11), lessThan((PyBase) testSubject21));
    assertThat(((PyBase) testSubject21), greaterThan((PyBase) testSubject11));

    assertThat(((PyBase) testSubject11), not(comparesEqualTo(((PyBase) none))));
    
    assertThat(((PyBase) testSubject11), not(comparesEqualTo(((PyBase) null))));
  }

}

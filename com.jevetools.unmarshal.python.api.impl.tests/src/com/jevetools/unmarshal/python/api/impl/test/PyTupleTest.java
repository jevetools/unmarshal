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
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.Iterator;

import org.junit.Test;

import com.jevetools.unmarshal.python.api.PyBase;
import com.jevetools.unmarshal.python.api.PyBool;
import com.jevetools.unmarshal.python.api.PyTuple;
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
public final class PyTupleTest
    extends AbstractPyBaseTest
{
  /**
   * Test method for {@link PyTuple#value()}.
   */
  @Test
  public void testDefault()
  {
    final PyFactory.PyTupleBuilder builder = getPyFactory().getPyTupleBuilder();

    final PyTuple testSubject = builder.build();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.size(), equalTo(0));

    assertThat(testSubject.isEmpty(), equalTo(true));

    assertThat(testSubject.toString(), equalTo("Tuple"));
  }

  /**
   * Test method for {@link PyTuple#accept(PyVisitor)}.
   */
  @Test
  public void getAccept()
  {
    final PyFactory.PyTupleBuilder builder = getPyFactory().getPyTupleBuilder();

    final PyTuple testSubject = builder.build();

    final PyVisitor visitor = new TestVisitor(testSubject);

    testSubject.accept(visitor);
  }

  /**
   * Test method for {@link PyTuple#getPyType()}.
   */
  @Test
  public void getGetType()
  {
    final PyFactory.PyTupleBuilder builder = getPyFactory().getPyTupleBuilder();

    final PyTuple testSubject = builder.build();

    assertThat(testSubject.getPyType(), equalTo(PyBase.PyType.TUPLE));

    assertThat(testSubject.isTuple(), equalTo(true));

    assertThat(testSubject.asTuple(), not(nullValue()));

    assertThat(testSubject.isAbstractList(), equalTo(true));
  }

  /**
   * Test method for {@link PyTuple#add(PyBase)}.
   */
  @Test
  public void testAdd()
  {
    final PyFactory.PyTupleBuilder builder = getPyFactory().getPyTupleBuilder();

    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    final PyTuple testSubject = builder.build();

    final PyNone none = builderNone.build();

    assertThat(testSubject, not(nullValue()));

    assertThat(none, not(nullValue()));

    testSubject.add(none);

    assertThat(testSubject.size(), equalTo(1));

    assertThat(testSubject.isEmpty(), equalTo(false));

    assertThat(((PyBase) testSubject.get(0)), sameInstance((PyBase) none));
  }

  /**
   * Test method for {@link PyTuple#add(PyBase)}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddNull()
  {
    final PyFactory.PyTupleBuilder builder = getPyFactory().getPyTupleBuilder();

    final PyTuple testSubject = builder.build();

    assertThat(testSubject, not(nullValue()));

    testSubject.add(null);
  }

  /**
   * Test method for {@link PyTuple#iterator()}.
   */
  @Test
  public void testIterator()
  {
    final PyFactory.PyTupleBuilder builder = getPyFactory().getPyTupleBuilder();

    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    final PyTuple testSubject = builder.build();

    final PyNone none = builderNone.build();

    assertThat(testSubject, not(nullValue()));

    assertThat(none, not(nullValue()));

    testSubject.add(none);

    final Iterator<PyBase> iterator = testSubject.iterator();

    assertThat(iterator.hasNext(), equalTo(true));

    assertThat(iterator.next(), not(nullValue()));

    assertThat(iterator.hasNext(), equalTo(false));

    assertThat(testSubject.isEmpty(), equalTo(false));

    assertThat(((PyBase) testSubject.get(0)), sameInstance((PyBase) none));
  }

  /**
   * Test method for {@link PyTuple#compareTo(PyBase)}.
   */
  @Test
  public void testCompareTo()
  {
    final PyFactory.PyTupleBuilder builder = getPyFactory().getPyTupleBuilder();

    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    final PyFactory.PyBoolBuilder builderBool = getPyFactory()
        .getPyBoolBuilder();

    final PyTuple testSubject11 = builder.build();
    final PyTuple testSubject12 = builder.build();

    final PyTuple testSubject21 = builder.build();
    final PyTuple testSubject22 = builder.build();

    final PyTuple testSubject31 = builder.build();
    final PyTuple testSubject32 = builder.build();

    final PyNone none = builderNone.build();

    testSubject21.add(none);
    testSubject22.add(none);

    final PyBool bool = builderBool.build();

    testSubject31.add(bool);
    testSubject32.add(bool);

    assertThat(((PyBase) testSubject11),
        comparesEqualTo((PyBase) testSubject12));
    assertThat(((PyBase) testSubject21),
        comparesEqualTo((PyBase) testSubject22));
    assertThat(((PyBase) testSubject31),
        comparesEqualTo((PyBase) testSubject32));

    assertThat(((PyBase) testSubject11), lessThan((PyBase) testSubject21));
    assertThat(((PyBase) testSubject21), lessThan((PyBase) testSubject31));
    assertThat(((PyBase) testSubject21), greaterThan((PyBase) testSubject11));

    assertThat(((PyBase) testSubject11), not(comparesEqualTo(((PyBase) none))));
  }

  /**
   * Test method for {@link PyTuple#equals(Object)}.
   */
  @Test
  public void testEquals()
  {
    final PyFactory.PyTupleBuilder builder = getPyFactory().getPyTupleBuilder();

    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    final PyFactory.PyBoolBuilder builderBool = getPyFactory()
        .getPyBoolBuilder();

    final PyTuple testSubject11 = builder.build();
    final PyTuple testSubject12 = builder.build();

    final PyTuple testSubject21 = builder.build();
    final PyTuple testSubject22 = builder.build();

    final PyTuple testSubject31 = builder.build();
    final PyTuple testSubject32 = builder.build();

    final PyNone none = builderNone.build();

    testSubject21.add(none);
    testSubject22.add(none);

    final PyBool bool = builderBool.build();

    testSubject31.add(bool);
    testSubject32.add(bool);

    assertThat(((PyBase) testSubject11), equalTo((PyBase) testSubject12));
    assertThat(((PyBase) testSubject21), equalTo((PyBase) testSubject22));
    assertThat(((PyBase) testSubject31), equalTo((PyBase) testSubject32));

    assertThat(((PyBase) testSubject21), not(equalTo((PyBase) testSubject12)));
    assertThat(((PyBase) testSubject31), not(equalTo((PyBase) testSubject22)));
    assertThat(((PyBase) testSubject11), not(equalTo((PyBase) testSubject32)));

    assertThat(((PyBase) testSubject11), not(equalTo(((PyBase) none))));
  }

  /**
   * Test method for {@link PyTuple#hashCode()}.
   */
  @Test
  public void testHashCode()
  {
    final PyFactory.PyTupleBuilder builder = getPyFactory().getPyTupleBuilder(
        -1);

    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    final PyTuple testSubject11 = builder.build();
    final PyTuple testSubject12 = builder.build();

    final PyTuple testSubject21 = builder.build();
    final PyTuple testSubject22 = builder.build();

    final PyNone none = builderNone.build();

    testSubject21.add(none);
    testSubject22.add(none);

    assertThat(testSubject11.hashCode(), equalTo(testSubject12.hashCode()));
    assertThat(testSubject21.hashCode(), equalTo(testSubject22.hashCode()));

    assertThat(testSubject11.hashCode(), not(equalTo(testSubject22.hashCode())));
  }

}

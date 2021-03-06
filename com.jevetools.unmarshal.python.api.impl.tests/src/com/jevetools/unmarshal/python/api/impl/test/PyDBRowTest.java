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
import java.util.Map.Entry;

import org.junit.Test;

import com.jevetools.unmarshal.python.api.PyBase;
import com.jevetools.unmarshal.python.api.PyBool;
import com.jevetools.unmarshal.python.api.PyNone;
import com.jevetools.unmarshal.python.api.PyDBRow;
import com.jevetools.unmarshal.python.api.PyFactory;
import com.jevetools.unmarshal.python.api.PyVisitor;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public final class PyDBRowTest
    extends AbstractPyBaseTest
{
  /**
   * Test method for {@link PyDBRow#value()}.
   */
  @Test
  public void testDefault()
  {
    final PyFactory.PyDBRowBuilder builder = getPyFactory().getPyDBRowBuilder();

    final PyDBRow testSubject = builder.build();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getHeader(), not(nullValue()));

    assertThat(testSubject.getDict(), not(nullValue()));

    assertThat(testSubject.getList(), not(nullValue()));

    assertThat(testSubject.toString(), equalTo("DBRow"));
  }

  /**
   * Test method for {@link PyDBRow#accept(PyVisitor)}.
   */
  @Test
  public void getAccept()
  {
    final PyFactory.PyDBRowBuilder builder = getPyFactory().getPyDBRowBuilder();

    final PyDBRow testSubject = builder.build();

    final PyVisitor visitor = new TestVisitor(testSubject);

    testSubject.accept(visitor);
  }

  /**
   * Test method for {@link PyDBRow#getPyType()}.
   */
  @Test
  public void getGetType()
  {
    final PyFactory.PyDBRowBuilder builder = getPyFactory().getPyDBRowBuilder();

    final PyDBRow testSubject = builder.build();

    assertThat(testSubject.getPyType(), equalTo(PyBase.PyType.DBROW));

    assertThat(testSubject.isDBRow(), equalTo(true));

    assertThat(testSubject.asDBRow(), not(nullValue()));

    assertThat(testSubject.isAbstractObjectEx(), equalTo(true));
  }

  /**
   * Test method for {@link PyDBRow#getHeader()}.
   */
  @Test
  public void getGetHeader()
  {
    final PyFactory.PyDBRowBuilder builder = getPyFactory().getPyDBRowBuilder();

    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    final PyNone none = builderNone.build();

    builder.header(none);

    final PyDBRow testSubject = builder.build();

    assertThat(testSubject.getHeader(), sameInstance(((PyBase) none)));
  }

  /**
   * Test method for {@link PyDBRow#equals(Object)}.
   */
  @Test
  public void getEquals()
  {
    final PyFactory.PyDBRowBuilder builder = getPyFactory().getPyDBRowBuilder();

    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    final PyFactory.PyBoolBuilder builderBool = getPyFactory()
        .getPyBoolBuilder();

    final PyDBRow testSubject11 = builder.build();
    final PyDBRow testSubject12 = builder.build();

    final PyNone none = builderNone.build();
    final PyBool bool = builderBool.build();

    builder.header(bool);

    final PyDBRow testSubject21 = builder.build();
    final PyDBRow testSubject22 = builder.build();

    testSubject21.getDict().put(bool, none);
    testSubject22.getDict().put(bool, none);

    final PyDBRow testSubject31 = builder.build();
    final PyDBRow testSubject32 = builder.build();

    testSubject31.getDict().put(none, bool);
    testSubject32.getDict().put(none, bool);

    assertThat(testSubject11, equalTo(testSubject12));

    assertThat(testSubject21, equalTo(testSubject22));

    assertThat(testSubject31, equalTo(testSubject32));

    assertThat(testSubject11, not(equalTo(testSubject22)));

    assertThat(testSubject11, not(equalTo(testSubject22)));
    assertThat(testSubject11, not(equalTo(testSubject32)));

    assertThat(testSubject21, not(equalTo(testSubject12)));
    assertThat(testSubject21, not(equalTo(testSubject32)));

    assertThat(((PyBase) testSubject11), not(equalTo(((PyBase) none))));
  }

  /**
   * Test method for {@link PyDBRow#compareTo(PyBase)}.
   */
  @Test
  public void getCompareTo()
  {
    final PyFactory.PyDBRowBuilder builder = getPyFactory().getPyDBRowBuilder();

    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    final PyFactory.PyBoolBuilder builderBool = getPyFactory()
        .getPyBoolBuilder();

    final PyNone none = builderNone.build();
    final PyBool bool = builderBool.build();

    builder.header(bool);

    final PyDBRow testSubject11 = builder.build();
    final PyDBRow testSubject12 = builder.build();

    final PyDBRow testSubject21 = builder.build();
    final PyDBRow testSubject22 = builder.build();

    builder.header(none);

    final PyDBRow testSubject31 = builder.build();
    final PyDBRow testSubject32 = builder.build();

    testSubject21.getDict().put(bool, none);
    testSubject22.getDict().put(bool, none);

    testSubject31.getDict().put(none, bool);
    testSubject32.getDict().put(none, bool);

    assertThat(((PyBase) testSubject11),
        comparesEqualTo((PyBase) testSubject12));
    assertThat(((PyBase) testSubject21),
        comparesEqualTo((PyBase) testSubject22));
    assertThat(((PyBase) testSubject31),
        comparesEqualTo((PyBase) testSubject32));

    assertThat(((PyBase) testSubject11), lessThan((PyBase) testSubject21));
    assertThat(((PyBase) testSubject21), greaterThan((PyBase) testSubject31));
    assertThat(((PyBase) testSubject21), greaterThan((PyBase) testSubject11));

    assertThat(((PyBase) testSubject11), not(comparesEqualTo(((PyBase) none))));
  }

  /**
   * Test method for {@link PyDBRow#hashCode()}.
   */
  @Test
  public void getHashCode()
  {
    final PyFactory.PyDBRowBuilder builder = getPyFactory().getPyDBRowBuilder();

    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    final PyFactory.PyBoolBuilder builderBool = getPyFactory()
        .getPyBoolBuilder();

    final PyDBRow testSubject11 = builder.build();
    final PyDBRow testSubject12 = builder.build();

    final PyNone none = builderNone.build();
    final PyBool bool = builderBool.build();

    builder.header(bool);

    final PyDBRow testSubject21 = builder.build();
    final PyDBRow testSubject22 = builder.build();

    testSubject21.getDict().put(none, bool);
    testSubject22.getDict().put(none, bool);

    assertThat(testSubject11.hashCode(), equalTo(testSubject12.hashCode()));

    assertThat(testSubject21.hashCode(), equalTo(testSubject22.hashCode()));

    assertThat(testSubject11.hashCode(), not(equalTo(testSubject22.hashCode())));
  }

  /**
   * Test method for {@link PyDBRow#iterator()}.
   */
  @Test
  public void getIterator()
  {
    final PyFactory.PyDBRowBuilder builder = getPyFactory().getPyDBRowBuilder();

    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    final PyDBRow testSubject = builder.build();

    final PyNone none = builderNone.build();

    assertThat(testSubject, not(nullValue()));

    assertThat(none, not(nullValue()));

    testSubject.getDict().put(none, none);

    final Iterator<Entry<PyBase, PyBase>> iterator = testSubject.iterator();

    assertThat(iterator.hasNext(), equalTo(true));

    assertThat(iterator.next(), not(nullValue()));

    assertThat(iterator.hasNext(), equalTo(false));

    assertThat(testSubject.getDict().isEmpty(), equalTo(false));

    assertThat(((PyBase) testSubject.getDict().get(none)),
        sameInstance((PyBase) none));
  }
}

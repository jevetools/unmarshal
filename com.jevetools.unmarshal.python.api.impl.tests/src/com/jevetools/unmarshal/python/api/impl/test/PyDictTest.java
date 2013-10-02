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

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import com.jevetools.unmarshal.python.api.PyBase;
import com.jevetools.unmarshal.python.api.PyBool;
import com.jevetools.unmarshal.python.api.PyDict;
import com.jevetools.unmarshal.python.api.PyFactory;
import com.jevetools.unmarshal.python.api.PyInt;
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
public final class PyDictTest
    extends AbstractPyBaseTest
{
  /**
   * Test method for {@link PyDict#value()}.
   */
  @Test
  public void testDefault()
  {
    final PyFactory.PyDictBuilder builder = getPyFactory().getPyDictBuilder();

    final PyDict testSubject = builder.build();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.size(), equalTo(0));

    assertThat(testSubject.isEmpty(), equalTo(true));

    assertThat(testSubject.toString(), equalTo("Dict"));
  }

  /**
   * Test method for {@link PyDict#accept(PyVisitor)}.
   */
  @Test
  public void getAccept()
  {
    final PyFactory.PyDictBuilder builder = getPyFactory().getPyDictBuilder();

    final PyDict testSubject = builder.build();

    final PyVisitor visitor = new TestVisitor(testSubject);

    testSubject.accept(visitor);
  }

  /**
   * Test method for {@link PyDict#getPyType()}.
   */
  @Test
  public void getGetType()
  {
    final PyFactory.PyDictBuilder builder = getPyFactory().getPyDictBuilder();

    final PyDict testSubject = builder.build();

    assertThat(testSubject.getPyType(), equalTo(PyBase.PyType.DICT));

    assertThat(testSubject.isDict(), equalTo(true));

    assertThat(testSubject.asDict(), not(nullValue()));
  }

  /**
   * Test method for {@link PyDict#get(PyBase)}.
   */
  @Test
  public void testGet()
  {
    final PyFactory.PyDictBuilder builder = getPyFactory().getPyDictBuilder();

    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    final PyFactory.PyIntBuilder builderInt = getPyFactory().getPyIntBuilder();

    final PyDict testSubject = builder.build();

    final PyNone key = builderNone.build();

    final PyInt value = builderInt.build();

    assertThat(testSubject, not(nullValue()));

    testSubject.put(key, value);

    assertThat(testSubject.containsKey(key), equalTo(true));

    assertThat(testSubject.containsKey(value), equalTo(false));

    assertThat(testSubject.size(), equalTo(1));

    assertThat(testSubject.get(key), equalTo(((PyBase) value)));
  }

  /**
   * Test method for {@link PyDict#iterator()}.
   */
  @Test
  public void testIterator()
  {
    final PyFactory.PyDictBuilder builder = getPyFactory().getPyDictBuilder();

    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    final PyFactory.PyIntBuilder builderInt = getPyFactory().getPyIntBuilder();

    final PyDict testSubject = builder.build();

    final PyNone key = builderNone.build();

    final PyInt value = builderInt.build();

    assertThat(testSubject, not(nullValue()));

    testSubject.put(key, value);

    final Iterator<Entry<PyBase, PyBase>> iterator = testSubject.iterator();

    assertThat(iterator, not(nullValue()));

    assertThat(iterator.hasNext(), equalTo(true));

    final Entry<PyBase, PyBase> entry = iterator.next();

    assertThat(entry.getKey(), not(nullValue()));
    assertThat(entry.getValue(), not(nullValue()));

    assertThat(entry.getKey(), equalTo(((PyBase) key)));
    assertThat(entry.getValue(), equalTo(((PyBase) value)));

    assertThat(iterator.hasNext(), equalTo(false));
  }

  /**
   * Test method for {@link PyDict#entrySet()}.
   */
  @Test
  public void testEntrySet()
  {
    final PyFactory.PyDictBuilder builder = getPyFactory().getPyDictBuilder();

    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    final PyFactory.PyIntBuilder builderInt = getPyFactory().getPyIntBuilder();

    final PyDict testSubject = builder.build();

    final PyNone key = builderNone.build();

    final PyInt value = builderInt.build();

    assertThat(testSubject, not(nullValue()));

    testSubject.put(key, value);

    final Set<Entry<PyBase, PyBase>> set = testSubject.entrySet();

    final Iterator<Entry<PyBase, PyBase>> iterator = set.iterator();

    assertThat(iterator, not(nullValue()));

    assertThat(iterator.hasNext(), equalTo(true));

    final Entry<PyBase, PyBase> entry = iterator.next();

    assertThat(entry.getKey(), not(nullValue()));
    assertThat(entry.getValue(), not(nullValue()));

    assertThat(entry.getKey(), equalTo(((PyBase) key)));
    assertThat(entry.getValue(), equalTo(((PyBase) value)));

    assertThat(iterator.hasNext(), equalTo(false));
  }

  /**
   * Test method for {@link PyDict#put(PyBase, PyBase)}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPutNullKey()
  {
    final PyFactory.PyDictBuilder builder = getPyFactory().getPyDictBuilder();

    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    final PyDict testSubject = builder.build();

    final PyNone none = builderNone.build();

    assertThat(testSubject, not(nullValue()));

    testSubject.put(null, none);
  }

  /**
   * Test method for {@link PyDict#put(PyBase, PyBase)}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPutNullValue()
  {
    final PyFactory.PyDictBuilder builder = getPyFactory().getPyDictBuilder();

    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    final PyDict testSubject = builder.build();

    final PyNone none = builderNone.build();

    assertThat(testSubject, not(nullValue()));

    testSubject.put(none, null);
  }

  /**
   * Test method for {@link PyDict#equals(Object)}.
   */
  @Test
  public void testEquals()
  {
    final PyFactory.PyDictBuilder builder = getPyFactory().getPyDictBuilder();

    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    final PyFactory.PyBoolBuilder builderBool = getPyFactory()
        .getPyBoolBuilder();

    final PyDict testSubject11 = builder.build();
    final PyDict testSubject12 = builder.build();

    final PyDict testSubject21 = builder.build();
    final PyDict testSubject22 = builder.build();

    final PyNone none = builderNone.build();
    final PyBool bool = builderBool.build();

    testSubject21.put(none, bool);
    testSubject22.put(none, bool);

    assertThat(testSubject11, equalTo(testSubject12));

    assertThat(testSubject21, equalTo(testSubject22));

    assertThat(testSubject11, not(equalTo(testSubject22)));

    assertThat(((PyBase) testSubject11), not(equalTo(((PyBase) none))));
  }

  /**
   * Test method for {@link PyDict#compareTo(PyBase)}.
   */
  @Test
  public void testCompareTo()
  {
    final PyFactory.PyDictBuilder builder = getPyFactory().getPyDictBuilder();

    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    final PyFactory.PyBoolBuilder builderBool = getPyFactory()
        .getPyBoolBuilder();

    final PyFactory.PyIntBuilder builderInt = getPyFactory().getPyIntBuilder();

    final PyDict testSubject11 = builder.build();
    final PyDict testSubject12 = builder.build();

    final PyDict testSubject21 = builder.build();
    final PyDict testSubject22 = builder.build();

    final PyDict testSubject31 = builder.build();
    final PyDict testSubject32 = builder.build();

    final PyDict testSubject41 = builder.build();
    final PyDict testSubject42 = builder.build();

    final PyNone none = builderNone.build();
    final PyBool bool = builderBool.build();
    final PyInt value = builderInt.build();

    testSubject21.put(none, bool);
    testSubject22.put(none, bool);

    testSubject41.put(bool, none);
    testSubject42.put(bool, none);

    testSubject31.put(none, value);
    testSubject32.put(none, value);

    assertThat(((PyBase) testSubject11),
        comparesEqualTo((PyBase) testSubject12));
    assertThat(((PyBase) testSubject21),
        comparesEqualTo((PyBase) testSubject22));
    assertThat(((PyBase) testSubject31),
        comparesEqualTo((PyBase) testSubject32));

    assertThat(((PyBase) testSubject11), lessThan((PyBase) testSubject21));
    assertThat(((PyBase) testSubject21), lessThan((PyBase) testSubject31));
    assertThat(((PyBase) testSubject21), lessThan((PyBase) testSubject41));
    assertThat(((PyBase) testSubject21), greaterThan((PyBase) testSubject11));

    assertThat(((PyBase) testSubject11), not(comparesEqualTo(((PyBase) none))));
  }

  /**
   * Test method for {@link PyDict#hashCode()}.
   */
  @Test
  public void testHashCode()
  {
    final PyFactory.PyDictBuilder builder = getPyFactory().getPyDictBuilder();

    final PyFactory.PyNoneBuilder builderNone = getPyFactory()
        .getPyNoneBuilder();

    final PyFactory.PyBoolBuilder builderBool = getPyFactory()
        .getPyBoolBuilder();

    final PyDict testSubject11 = builder.build();
    final PyDict testSubject12 = builder.build();

    final PyDict testSubject21 = builder.build();
    final PyDict testSubject22 = builder.build();

    final PyNone none = builderNone.build();
    final PyBool bool = builderBool.build();

    testSubject21.put(none, bool);
    testSubject22.put(none, bool);

    assertThat(testSubject11.hashCode(), equalTo(testSubject12.hashCode()));

    assertThat(testSubject21.hashCode(), equalTo(testSubject22.hashCode()));

    assertThat(testSubject11.hashCode(), not(equalTo(testSubject22.hashCode())));

    assertThat(((PyBase) testSubject11), not(equalTo(((PyBase) none))));
  }
}

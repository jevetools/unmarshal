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
package com.jevetools.unmarshal.python.api.service.impl.test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.jevetools.unmarshal.python.api.impl.internal.tests.AbstractTest;
import com.jevetools.unmarshal.python.api.service.PythonFactoryService;
import com.jevetools.unmarshal.python.api.service.impl.PythonFactoryServiceImpl;

/**
 * Copyright (c) 2013, jEVETools.
 *
 * All rights reserved.
 *
 * @version 0.0.1
 * @since 0.0.1
 */
public final class PythonFactoryServiceImplTest
    extends AbstractTest
{ // NOPMD
  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyNone()}.
   */
  @Test
  public void testGetPyNone()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyNone(), not(nullValue()));
  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyNoneBuilder()}.
   */
  @Test
  public void testGetPyNoneBuilder()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyNoneBuilder(), not(nullValue()));
  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyBoolBuilder()}.
   */
  @Test
  public void testGetPyBoolBuilder()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyBoolBuilder(), not(nullValue()));
  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyBoolFalse()}.
   */
  @Test
  public void testGetPyBoolFalse()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyBoolFalse(), not(nullValue()));
  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyBoolTrue()}.
   */
  @Test
  public void testGetPyBoolTrue()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyBoolTrue(), not(nullValue()));
  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyByteBuilder()}.
   */
  @Test
  public void testGetPyByteBuilder()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyByteBuilder(), not(nullValue()));
  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyShortBuilder()}.
   */
  @Test
  public void testGetPyShortBuilder()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyShortBuilder(), not(nullValue()));
  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyIntBuilder()}.
   */
  @Test
  public void testGetPyIntBuilder()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyIntBuilder(), not(nullValue()));
  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyLongBuilder()}.
   */
  @Test
  public void testGetPyLongBuilder()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyLongBuilder(), not(nullValue()));
  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyFloatBuilder()}.
   */
  @Test
  public void testGetPyFloatBuilder()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyFloatBuilder(), not(nullValue()));
  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyDoubleBuilder()}.
   */
  @Test
  public void testGetPyDoubleBuilder()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyDoubleBuilder(), not(nullValue()));
  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyString()}.
   */
  @Test
  public void testGetPyString()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyString(), not(nullValue()));
  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyStringBuilder()}.
   */
  @Test
  public void testGetPyStringBuilder()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyStringBuilder(), not(nullValue()));
  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyTupleBuilder()}.
   */
  @Test
  public void testGetPyTupleBuilder()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyTupleBuilder(), not(nullValue()));
  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyTupleBuilder(int)}.
   */
  @Test
  public void testGetPyTupleBuilderInt()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyTupleBuilder(1), not(nullValue()));
  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyListBuilder()}.
   */
  @Test
  public void testGetPyListBuilder()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyListBuilder(), not(nullValue()));
  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyListBuilder(int)}.
   */
  @Test
  public void testGetPyListBuilderInt()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyListBuilder(1), not(nullValue()));
  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyDictBuilder()}.
   */
  @Test
  public void testGetPyDictBuilder()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyDictBuilder(), not(nullValue()));
  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyObjectBuilder()}.
   */
  @Test
  public void testGetPyObjectBuilder()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyObjectBuilder(), not(nullValue()));

  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyObjectExBuilder()}.
   */
  @Test
  public void testGetPyObjectExBuilder()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyObjectExBuilder(), not(nullValue()));
  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyDBRowBuilder()}.
   */
  @Test
  public void testGetPyDBRowBuilder()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyDBRowBuilder(), not(nullValue()));
  }

  /**
   * Test method for
   * {@link PythonFactoryServiceImpl#getPyDBRowDescriptorBuilder()}.
   */
  @Test
  public void testGetPyDBRowDescriptorBuilder()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyDBRowDescriptorBuilder(), not(nullValue()));
  }

  /**
   * Test method for {@link PythonFactoryServiceImpl#getPyCRowSetBuilder()}.
   */
  @Test
  public void testGetPyCRowSetBuilder()
  {
    final PythonFactoryService testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getPyCRowSetBuilder(), not(nullValue()));
  }

  /**
   * @return test subject
   */
  private PythonFactoryService getTestSubject()
  {
    return new PythonFactoryServiceImpl();
  }
}

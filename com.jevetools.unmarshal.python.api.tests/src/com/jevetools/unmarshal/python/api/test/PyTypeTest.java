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
package com.jevetools.unmarshal.python.api.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.jevetools.unmarshal.python.api.PyBase.PyType;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public final class PyTypeTest
{
  /**
   * @since 0.0.1
   */
  private static final int TYPES_COUNT = 17;

  /**
   * {@link PyBase.PyType} test.
   * 
   * @throws Exception
   *             on error
   */
  @Test
  public void testPyTypeEnum() throws Exception //NOPMD
  {
    final PyType[] values = PyType.values();

    assertThat(values, not(nullValue()));

    assertThat(values.length, equalTo(TYPES_COUNT));

    assertThat(PyType.valueOf("BOOL"), equalTo(PyType.BOOL));
    assertThat(PyType.valueOf("NONE"), equalTo(PyType.NONE));
    assertThat(PyType.valueOf("BYTE"), equalTo(PyType.BYTE));
    assertThat(PyType.valueOf("SHORT"), equalTo(PyType.SHORT));
    assertThat(PyType.valueOf("INT"), equalTo(PyType.INT));
    assertThat(PyType.valueOf("FLOAT"), equalTo(PyType.FLOAT));
    assertThat(PyType.valueOf("DOUBLE"), equalTo(PyType.DOUBLE));
    assertThat(PyType.valueOf("STRING"), equalTo(PyType.STRING));
    assertThat(PyType.valueOf("TUPLE"), equalTo(PyType.TUPLE));
    assertThat(PyType.valueOf("LIST"), equalTo(PyType.LIST));
    assertThat(PyType.valueOf("DICT"), equalTo(PyType.DICT));
    assertThat(PyType.valueOf("OBJECT"), equalTo(PyType.OBJECT));
    assertThat(PyType.valueOf("OBJECTEX"), equalTo(PyType.OBJECTEX));
    assertThat(PyType.valueOf("DBROWDESCRIPTOR"),
        equalTo(PyType.DBROWDESCRIPTOR));
    assertThat(PyType.valueOf("DBROW"), equalTo(PyType.DBROW));
    assertThat(PyType.valueOf("CROWSET"), equalTo(PyType.CROWSET));
  }
}

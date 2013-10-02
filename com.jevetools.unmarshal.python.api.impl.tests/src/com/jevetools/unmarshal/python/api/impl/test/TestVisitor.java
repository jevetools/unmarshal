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
package com.jevetools.unmarshal.python.api.impl.test;

import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import com.jevetools.unmarshal.python.api.PyBase;
import com.jevetools.unmarshal.python.api.PyBool;
import com.jevetools.unmarshal.python.api.PyByte;
import com.jevetools.unmarshal.python.api.PyCRowSet;
import com.jevetools.unmarshal.python.api.PyDBRow;
import com.jevetools.unmarshal.python.api.PyDBRowDescriptor;
import com.jevetools.unmarshal.python.api.PyDict;
import com.jevetools.unmarshal.python.api.PyDouble;
import com.jevetools.unmarshal.python.api.PyFloat;
import com.jevetools.unmarshal.python.api.PyInt;
import com.jevetools.unmarshal.python.api.PyList;
import com.jevetools.unmarshal.python.api.PyLong;
import com.jevetools.unmarshal.python.api.PyNone;
import com.jevetools.unmarshal.python.api.PyObject;
import com.jevetools.unmarshal.python.api.PyObjectEx;
import com.jevetools.unmarshal.python.api.PyShort;
import com.jevetools.unmarshal.python.api.PyString;
import com.jevetools.unmarshal.python.api.PyTuple;
import com.jevetools.unmarshal.python.api.PyVisitor;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public final class TestVisitor
    implements PyVisitor
{ //NOPMD
  /**
   * 
   */
  private final transient PyBase mTestSubject;

  /**
   * @param aPyBase test subject
   * 
   * @since 0.0.1
   */
  public TestVisitor(final PyBase aPyBase)
  {
    super();
    mTestSubject = aPyBase;
  }

  @Override
  public void visit(final PyBool pyBool)
  {
    assertThat(pyBool, sameInstance(mTestSubject));
  }

  @Override
  public void visit(final PyByte pyByte)
  {
    assertThat(pyByte, sameInstance(mTestSubject));
  }

  @Override
  public void visit(final PyDBRow pyDBRow)
  {
    assertThat(pyDBRow, sameInstance(mTestSubject));
  }

  @Override
  public void visit(final PyCRowSet pyCRowSet)
  {
    assertThat(pyCRowSet, sameInstance(mTestSubject));
  }

  @Override
  public void visit(final PyDBRowDescriptor pyDBRowDescriptor)
  {
    assertThat(pyDBRowDescriptor, sameInstance(mTestSubject));
  }

  @Override
  public void visit(final PyDict pyDict)
  {
    assertThat(pyDict, sameInstance(mTestSubject));
  }

  @Override
  public void visit(final PyDouble pyDouble)
  {
    assertThat(pyDouble, sameInstance(mTestSubject));
  }

  @Override
  public void visit(final PyFloat pyFloat)
  {
    assertThat(pyFloat, sameInstance(mTestSubject));
  }

  @Override
  public void visit(final PyInt pyInt)
  {
    assertThat(pyInt, sameInstance(mTestSubject));
  }

  @Override
  public void visit(final PyList pyList)
  {
    assertThat(pyList, sameInstance(mTestSubject));
  }

  @Override
  public void visit(final PyLong pyLong)
  {
    assertThat(pyLong, sameInstance(mTestSubject));
  }

  @Override
  public void visit(final PyNone pyNone)
  {
    assertThat(pyNone, sameInstance(mTestSubject));
  }

  @Override
  public void visit(final PyObject pyObject)
  {
    assertThat(pyObject, sameInstance(mTestSubject));
  }

  @Override
  public void visit(final PyObjectEx pyObjectEx)
  {
    assertThat(pyObjectEx, sameInstance(mTestSubject));
  }

  @Override
  public void visit(final PyShort pyShort)
  {
    assertThat(pyShort, sameInstance(mTestSubject));
  }

  @Override
  public void visit(final PyString pyString)
  {
    assertThat(pyString, sameInstance(mTestSubject));
  }

  @Override
  public void visit(final PyTuple pyTuple)
  {
    assertThat(pyTuple, sameInstance(mTestSubject));
  }

}

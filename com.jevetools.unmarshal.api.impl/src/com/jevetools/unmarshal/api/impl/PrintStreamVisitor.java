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
package com.jevetools.unmarshal.api.impl;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Map.Entry;

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
public final class PrintStreamVisitor
    implements PyVisitor
{ // NOPMD
  /**
   * @since 0.0.1
   */
  private final transient PrintStream mStream;

  /**
   * @param stream
   *            print stream
   * @since 0.0.1
   */
  public PrintStreamVisitor(final PrintStream stream)
  {
    mStream = stream;
  }

  /**
   * @param pyBase
   *            PyBase
   * @since 0.0.1
   */
  private void println(final PyBase pyBase)
  {
    println(pyBase.toString());
  }

  /**
   * @param string
   *            string
   * @since 0.0.1
   */
  private void println(final String string)
  {
    mStream.println(string);
  }

  @Override
  public void visit(final PyBool pyBool)
  {
    println(pyBool);
  }

  @Override
  public void visit(final PyByte pyByte)
  {
    println(pyByte);
  }

  @Override
  public void visit(final PyDBRow pyDBRow)
  {
    println(pyDBRow);

    pyDBRow.getHeader().accept(this); // NOPMD
    pyDBRow.getList().accept(this); // NOPMD
    pyDBRow.getDict().accept(this); // NOPMD
  }

  @Override
  public void visit(final PyDBRowDescriptor pyDBRowDescriptor)
  {
    println(pyDBRowDescriptor);

    pyDBRowDescriptor.getHeader().accept(this); // NOPMD
    pyDBRowDescriptor.getList().accept(this); // NOPMD
    pyDBRowDescriptor.getDict().accept(this); // NOPMD
  }

  @Override
  public void visit(final PyDict pyDict)
  {
    println(pyDict + " " + pyDict.size());

    final Iterator<Entry<PyBase, PyBase>> iterator = pyDict.iterator();
    
    while (iterator.hasNext())
    {
      final Entry<PyBase, PyBase> pyBase = iterator.next();
      
      pyBase.getKey().accept(this); // NOPMD
      pyBase.getValue().accept(this); // NOPMD
    }

  }

  @Override
  public void visit(final PyDouble pyDouble)
  {
    println(pyDouble);
  }

  @Override
  public void visit(final PyFloat pyFloat)
  {
    println(pyFloat);
  }

  @Override
  public void visit(final PyInt pyInt)
  {
    println(pyInt);
  }

  @Override
  public void visit(final PyList pyList)
  {
    println(pyList + " " + pyList.size());

    final Iterator<PyBase> iterator = pyList.iterator();

    while (iterator.hasNext())
    {
      final PyBase pyBase = iterator.next();

      pyBase.accept(this); // NOPMD
    }
  }

  @Override
  public void visit(final PyLong pyLong)
  {
    println(pyLong);
  }

  @Override
  public void visit(final PyNone pyNone)
  {
    println(pyNone);
  }

  @Override
  public void visit(final PyObject pyObject)
  {
    println(pyObject);

    pyObject.getHeader().accept(this); // NOPMD
    pyObject.getBody().accept(this); // NOPMD
  }

  @Override
  public void visit(final PyObjectEx pyObjectEx)
  {
    println(pyObjectEx);

    pyObjectEx.getHeader().accept(this); // NOPMD
    pyObjectEx.getList().accept(this); // NOPMD
    pyObjectEx.getDict().accept(this); // NOPMD
  }

  @Override
  public void visit(final PyCRowSet aPyCRowSet)
  {
    println(aPyCRowSet);

    aPyCRowSet.getHeader().accept(this); // NOPMD
    aPyCRowSet.getList().accept(this); // NOPMD
    aPyCRowSet.getDict().accept(this); // NOPMD
  }

  @Override
  public void visit(final PyShort pyShort)
  {
    println(pyShort);
  }

  @Override
  public void visit(final PyString pyString)
  {
    println(pyString);
  }

  @Override
  public void visit(final PyTuple pyTuple)
  {
    println(pyTuple + " " + pyTuple.size());

    final Iterator<PyBase> iterator = pyTuple.iterator();

    while (iterator.hasNext())
    {
      final PyBase pyBase = iterator.next();

      pyBase.accept(this); // NOPMD
    }
  }
}

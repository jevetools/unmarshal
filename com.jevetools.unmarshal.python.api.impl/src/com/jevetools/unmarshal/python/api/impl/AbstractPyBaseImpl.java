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
package com.jevetools.unmarshal.python.api.impl; // NOPMD

import com.jevetools.unmarshal.python.api.PyBaseList;
import com.jevetools.unmarshal.python.api.PyBaseObjectEx;
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

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class AbstractPyBaseImpl
    implements PyBase
{ // NOPMD
  /**
   * @since 0.0.1
   */
  protected static final int HASH_PRIME = 31;

  /**
   * Hash code used for {@code true}.
   */
  protected static final int BOOL_HASH_TRUE = 1231;

  /**
   * Hash code used for {@code false}.
   */
  protected static final int BOOL_HASH_FALSE = 1237;

  /**
   * Shift used for hash code for {@code double}/{@code long} primitives.
   */
  protected static final int HASHCODE_SHIFT = 32;

  @Override
  public final PyBool asBool()
  {
    return getAs();
  }

  @Override
  public final PyByte asByte()
  {
    return getAs();
  }

  @Override
  public final PyCRowSet asCRowSet()
  {
    return getAs();
  }

  @Override
  public final PyDBRow asDBRow()
  {
    return getAs();
  }

  @Override
  public final PyDBRowDescriptor asDBRowDescriptor()
  {
    return getAs();
  }

  @Override
  public final PyDict asDict()
  {
    return getAs();
  }

  @Override
  public final PyDouble asDouble()
  {
    return getAs();
  }

  @Override
  public final PyFloat asFloat()
  {
    return getAs();
  }

  @Override
  public final PyInt asInt()
  {
    return getAs();
  }

  @Override
  public final PyList asList()
  {
    return getAs();
  }

  @Override
  public final PyBaseList asAbstractList()
  {
    return getAs();
  }

  @Override
  public final PyLong asLong()
  {
    return getAs();
  }

  @Override
  public final PyNone asNone()
  {
    return getAs();
  }

  @Override
  public final PyObject asObject()
  {
    return getAs();
  }

  @Override
  public final PyObjectEx asObjectEx()
  {
    return getAs();
  }

  @Override
  public final PyBaseObjectEx asAbstractObjectEx()
  {
    return getAs();
  }

  @Override
  public final PyShort asShort()
  {
    return getAs();
  }

  @Override
  public final PyString asString()
  {
    return getAs();
  }

  @Override
  public final PyTuple asTuple()
  {
    return getAs();
  }

  @Override
  public boolean equals(final Object other)
  {
    if (this == other)
    {
      return true;
    }

    if (!(other instanceof PyBase))
    {
      return false;
    }

    final PyBase otherCasted = (PyBase) other;

    if (getPyType() != otherCasted.getPyType()) // NOPMD
    {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode()
  {
    return HASH_PRIME + getPyTypeHashCode();
  }

  @Override
  public final boolean isBool()
  {
    return pyTypeEqual(PyType.BOOL);
  }

  @Override
  public final boolean isByte()
  {
    return pyTypeEqual(PyType.BYTE);
  }

  @Override
  public final boolean isCRowSet()
  {
    return pyTypeEqual(PyType.CROWSET);
  }

  @Override
  public final boolean isDBRow()
  {
    return pyTypeEqual(PyType.DBROW);
  }

  @Override
  public final boolean isDBRowDescriptor()
  {
    return pyTypeEqual(PyType.DBROWDESCRIPTOR);
  }

  @Override
  public final boolean isDict()
  {
    return pyTypeEqual(PyType.DICT);
  }

  @Override
  public final boolean isDouble()
  {
    return pyTypeEqual(PyType.DOUBLE);
  }

  @Override
  public final boolean isFloat()
  {
    return pyTypeEqual(PyType.FLOAT);
  }

  @Override
  public final boolean isInt()
  {
    return pyTypeEqual(PyType.INT);
  }

  @Override
  public final boolean isList()
  {
    return pyTypeEqual(PyType.LIST);
  }

  @Override
  public final boolean isAbstractList()
  {
    return this instanceof PyBaseList;
  }

  @Override
  public final boolean isLong()
  {
    return pyTypeEqual(PyType.LONG);
  }

  @Override
  public final boolean isNone()
  {
    return pyTypeEqual(PyType.NONE);
  }

  @Override
  public final boolean isObject()
  {
    return pyTypeEqual(PyType.OBJECT);
  }

  @Override
  public final boolean isObjectEx()
  {
    return pyTypeEqual(PyType.OBJECTEX);
  }

  @Override
  public final boolean isAbstractObjectEx()
  {
    return this instanceof PyBaseObjectEx;
  }

  @Override
  public final boolean isShort()
  {
    return pyTypeEqual(PyType.SHORT);
  }

  @Override
  public final boolean isString()
  {
    return pyTypeEqual(PyType.STRING);
  }

  @Override
  public final boolean isTuple()
  {
    return pyTypeEqual(PyType.TUPLE);
  }

  /**
   * @param other {@link PyBase}
   * @return 0 if same {@link PyBase}
   */
  protected final int comparePyType(final PyBase other)
  {
    if (other == null)
    {
      return 1;
    }

    final PyType pyType = getPyType();

    return pyType.compareTo(other.getPyType()); // NOPMD 
  }

  /**
   * Cast to E.
   * 
   * @param <E>
   *            type
   * @return E
   */
  @SuppressWarnings("unchecked")
  private <E> E getAs()
  {
    return (E) this;
  }

  /**
   * @return hashCode of {@link PyType}
   */
  private int getPyTypeHashCode()
  {
    final PyType pyType = getPyType();

    return HASH_PRIME + pyType.hashCode(); // NOPMD
  }

  /**
   * @param aPyType {@link PyType}
   * @return <code>true</code> of {@link PyType} matches this, 
   *    otherwise <code>false</code>
   */
  private boolean pyTypeEqual(final PyType aPyType)
  {
    final PyType pyType = getPyType();

    return pyType.equals(aPyType); // NOPMD
  }
}

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
package com.jevetools.unmarshal.python.api.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.jevetools.unmarshal.python.api.PyBase;
import com.jevetools.unmarshal.python.api.PyDict;
import com.jevetools.unmarshal.python.api.PyFactory.PyDictBuilder;
import com.jevetools.unmarshal.python.api.PyVisitor;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public final class PyDictImpl
    extends AbstractPyBaseImpl
    implements PyDict
{ // NOPMD
  /**
   * Used to store the {@link PyBase} elements.
   * 
   * @since 0.0.1
   */
  private final transient Map<PyBase, PyBase> mValue;

  /**
   * Builder for {@link PyDict}.
   * 
   * @version 0.0.1
   * @since 0.0.1
   */
  public static final class PyDictBuilderImpl
      implements PyDictBuilder
  {
    @Override
    public PyDict build()
    {
      return new PyDictImpl();
    }
  }

  /**
   * @since 0.0.1
   */
  PyDictImpl()
  {
    super();
    mValue = new TreeMap<>();
  }

  @Override
  public void accept(final PyVisitor visitor)
  {
    visitor.visit(this);
  }

  @Override
  public int compareTo(final PyBase other) // NOPMD
  {
    int result = comparePyType(other);

    if (result != 0)
    {
      return result;
    }

    if (size() == other.asDict().size()) // NOPMD
    {
      for (final Map.Entry<PyBase, PyBase> entry : other.asDict()) // NOPMD
      {
        if (!mValue.containsKey(entry.getKey())) // NOPMD
        {
          return -1;
        }

        result = mValue.get(// NOPMD
            entry.getKey()).compareTo(entry.getValue()); // NOPMD

        if (result != 0)
        {
          return result; // NOPMD
        }
      }
    }

    return size() - other.asDict().size(); // NOPMD
  }

  @Override
  public boolean containsKey(final PyBase key)
  {
    return mValue.containsKey(key);
  }

  @Override
  public Set<Entry<PyBase, PyBase>> entrySet()
  {
    return mValue.entrySet();
  }

  @Override
  public boolean equals(final Object other)
  {
    if (!super.equals(other))
    {
      return false;
    }

    final PyBase otherCasted = (PyBase) other;

    if (size() != otherCasted.asDict().size()) // NOPMD
    {
      return false;
    }

    return mValue.entrySet().equals(otherCasted.asDict().entrySet());  // NOPMD
  }

  @Override
  public PyBase get(final PyBase key)
  {
    return mValue.get(key);
  }

  @Override
  public PyType getPyType()
  {
    return PyType.DICT;
  }

  @Override
  public int hashCode()
  {
    final int hashCode = mValue.entrySet().hashCode(); // NOPMD
    return HASH_PRIME * super.hashCode() + hashCode;
  }

  @Override
  public boolean isEmpty()
  {
    return mValue.isEmpty();
  }

  @Override
  public Iterator<Entry<PyBase, PyBase>> iterator()
  {
    return mValue.entrySet().iterator(); // NOPMD
  }

  @Override
  public PyBase put(final PyBase key, final PyBase value)
  {
    if (key == null)
    {
      throw new IllegalArgumentException("key");
    }

    if (value == null)
    {
      throw new IllegalArgumentException("value");
    }

    return mValue.put(key, value);
  }

  @Override
  public int size()
  {
    return mValue.size();
  }

  @Override
  public String toString()
  {
    return "Dict";
  }
}

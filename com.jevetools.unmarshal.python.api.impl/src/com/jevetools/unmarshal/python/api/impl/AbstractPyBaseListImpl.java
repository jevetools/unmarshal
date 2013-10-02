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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.jevetools.unmarshal.python.api.PyBase;
import com.jevetools.unmarshal.python.api.PyBaseList;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class AbstractPyBaseListImpl
    extends AbstractPyBaseImpl
    implements PyBaseList
{ // NOPMD
  /**
   * Used to store the {@link PyBase} elements.
   * 
   * @since 0.0.1
   */
  private final transient List<PyBase> mValue;

  /**
   * @param aSize
   *            initial list size
   * @since 0.0.1
   */
  AbstractPyBaseListImpl(final int aSize)
  {
    super();

    if (aSize > 0)
    {
      mValue = new ArrayList<>(aSize);
    }
    else
    {
      mValue = new ArrayList<>();
    }
  }

  @Override
  public final boolean add(final PyBase pyBase)
  {
    if (pyBase == null)
    {
      throw new IllegalArgumentException();
    }

    return mValue.add(pyBase);
  }

  @Override
  public final int compareTo(final PyBase other) // NOPMD
  {
    final int result = comparePyType(other);

    if (result != 0)
    {
      return result;
    }

    if (size() == other.asAbstractList().size()) // NOPMD
    {
      for (final PyBase pyBase : other.asAbstractList()) // NOPMD
      {
        if (!mValue.contains(pyBase))
        {
          return -1; // NOPMD
        }
      }
    }

    return size() - other.asAbstractList().size(); // NOPMD
  }

  @Override
  public final boolean equals(final Object other) // NOPMD
  {
    if (!super.equals(other))
    {
      return false;
    }

    final PyBase otherCasted = (PyBase) other;

    if (size() != otherCasted.asAbstractList().size()) // NOPMD
    {
      return false;
    }

    for (final PyBase pyBase : otherCasted.asAbstractList()) // NOPMD
    {
      if (!mValue.contains(pyBase))
      {
        return false;
      }
    }

    return true;
  }

  @Override
  public final PyBase get(final int index)
  {
    return mValue.get(index);
  }

  @Override
  public final int hashCode()
  {
    return HASH_PRIME * super.hashCode() + mValue.hashCode();
  }

  @Override
  public final boolean isEmpty()
  {
    return mValue.isEmpty();
  }

  @Override
  public final Iterator<PyBase> iterator()
  {
    return mValue.iterator();
  }

  @Override
  public final int size()
  {
    return mValue.size();
  }
}

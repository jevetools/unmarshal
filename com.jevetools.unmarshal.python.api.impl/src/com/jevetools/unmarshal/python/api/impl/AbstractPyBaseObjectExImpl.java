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

import com.jevetools.unmarshal.python.api.PyBaseObjectEx;
import com.jevetools.unmarshal.python.api.PyBase;
import com.jevetools.unmarshal.python.api.PyDict;
import com.jevetools.unmarshal.python.api.PyList;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class AbstractPyBaseObjectExImpl
    extends AbstractPyBaseImpl
    implements PyBaseObjectEx
{
  /**
   * Used to store the {@link PyBase} header.
   * 
   * @since 0.0.1
   */
  private final transient PyBase mHeader;

  /**
   * Used to store the {@link PyBase} dictionary.
   * 
   * @since 0.0.1
   */
  private final transient PyDict mDict;

  /**
   * Used to store the {@link PyBase} list.
   * 
   * @since 0.0.1
   */
  private final transient PyList mList;

  /**
   * @param aHeader
   *            header
   * @param aDict
   *            dictionary
   * @param aList
   *            list
   * 
   * @since 0.0.1
   */
  public AbstractPyBaseObjectExImpl(final PyBase aHeader, final PyDict aDict,
      final PyList aList)
  {
    super();
    mHeader = aHeader;
    mDict = aDict;
    mList = aList;
  }

  @Override
  public final int compareTo(final PyBase other)
  {
    int result = getPyType().compareTo(other.getPyType()); // NOPMD

    if (result != 0)
    {
      return result;
    }

    final PyBase otherCast = other;

    result = mHeader.compareTo(otherCast.// NOPMD
        asAbstractObjectEx().getHeader()); // NOPMD

    if (result != 0)
    {
      return result;
    }

    result = mDict.compareTo(otherCast.asAbstractObjectEx().getDict()); // NOPMD

    if (result != 0)
    {
      return result;
    }

    return mList.compareTo(otherCast.asAbstractObjectEx().getList()); // NOPMD
  }

  @Override
  public final boolean equals(final Object other)
  {
    if (!super.equals(other))
    {
      return false;
    }

    final PyBase otherCasted = (PyBase) other;

    if (!mHeader.equals(otherCasted.asAbstractObjectEx().getHeader())) // NOPMD
    {
      return false;
    }

    if (!mDict.equals(otherCasted.asAbstractObjectEx().getDict())) // NOPMD
    {
      return false;
    }

    return mList.equals(otherCasted.asAbstractObjectEx().getList()); // NOPMD
  }

  @Override
  public final PyDict getDict()
  {
    return mDict;
  }

  @Override
  public final PyBase getHeader()
  {
    return mHeader;
  }

  @Override
  public final PyList getList()
  {
    return mList;
  }

  @Override
  public final int hashCode()
  {
    final int result = HASH_PRIME * super.hashCode() + mHeader.hashCode();

    return HASH_PRIME * HASH_PRIME * result + mDict.hashCode()
        + mList.hashCode();
  }
}

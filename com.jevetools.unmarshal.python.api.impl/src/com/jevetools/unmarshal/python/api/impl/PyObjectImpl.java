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

import com.jevetools.unmarshal.python.api.PyBase;
import com.jevetools.unmarshal.python.api.PyFactory.PyObjectBuilder;
import com.jevetools.unmarshal.python.api.PyObject;
import com.jevetools.unmarshal.python.api.PyVisitor;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public final class PyObjectImpl
    extends AbstractPyBaseImpl
    implements PyObject
{
  /**
   * Used to store the {@link PyBase} header.
   * 
   * @since 0.0.1
   */
  private final transient PyBase mHeader;

  /**
   * Used to store the {@link PyBase} body.
   * 
   * @since 0.0.1
   */
  private final transient PyBase mBody;

  /**
   * Builder for {@link PyObject}.
   * 
   * @version 0.0.1
   * @since 0.0.1
   */
  public static final class PyObjectBuilderImpl
      implements PyObjectBuilder
  {
    /**
     * {@link PyBase} header.
     * 
     * @since 0.0.1
     */
    private transient PyBase mHeader = PyFactoryImpl.NONE;

    /**
     * {@link PyBase} body.
     * 
     * @since 0.0.1
     */
    private transient PyBase mBody = PyFactoryImpl.NONE;

    @Override
    public PyObject build()
    {
      return new PyObjectImpl(this);
    }

    @Override
    public PyObjectBuilder body(final PyBase body)
    {
      mBody = body;
      return this;
    }

    @Override
    public PyObjectBuilder header(final PyBase header)
    {
      mHeader = header;
      return this;
    }
  }

  /**
   * @param builder
   *            builder
   * @since 0.0.1
   */
  PyObjectImpl(final PyObjectBuilderImpl builder)
  {
    super();
    mHeader = builder.mHeader;
    mBody = builder.mBody;
  }

  @Override
  public void accept(final PyVisitor visitor)
  {
    visitor.visit(this);
  }

  @Override
  public int compareTo(final PyBase other)
  {
    int result = comparePyType(other);

    if (result != 0)
    {
      return result;
    }

    final PyBase otherCasted = other;

    result = mHeader.compareTo(otherCasted.asObject().getHeader()); // NOPMD

    if (result != 0)
    {
      return result;
    }

    return mBody.compareTo(otherCasted.asObject().getBody()); // NOPMD
  }

  @Override
  public boolean equals(final Object other)
  {
    if (!super.equals(other))
    {
      return false;
    }

    final PyBase otherCasted = (PyBase) other;

    if (!mHeader.equals(otherCasted.asObject().getHeader())) // NOPMD
    {
      return false;
    }

    return mBody.equals(otherCasted.asObject().getBody()); // NOPMD
  }

  @Override
  public PyBase getBody()
  {
    return mBody;
  }

  @Override
  public PyBase getHeader()
  {
    return mHeader;
  }

  @Override
  public PyType getPyType()
  {
    return PyType.OBJECT;
  }

  @Override
  public int hashCode()
  {
    return HASH_PRIME * super.hashCode() + mHeader.hashCode()
        + mBody.hashCode();
  }

  @Override
  public String toString()
  {
    return "Object";
  }
}

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
import com.jevetools.unmarshal.python.api.PyDouble;
import com.jevetools.unmarshal.python.api.PyFactory.PyDoubleBuilder;
import com.jevetools.unmarshal.python.api.PyVisitor;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public final class PyDoubleImpl
    extends AbstractPyBaseImpl
    implements PyDouble
{
  /**
   * Used to store a {@code double} value.
   * 
   * @since 0.0.1
   */
  private final transient double mValue;

  /**
   * Builder for {@link PyDouble}.
   * 
   * @version 0.0.1
   * @since 0.0.1
   */
  public static final class PyDoubleBuilderImpl
      implements PyDoubleBuilder
  {
    /**
     * Used to store a {@code double} value.
     * 
     * @since 0.0.1
     */
    private transient double mValue;

    @Override
    public PyDouble build()
    {
      return new PyDoubleImpl(this);
    }

    @Override
    public PyDoubleBuilder value(final double value)
    {
      mValue = value;
      return this;
    }
  }

  /**
   * @param builder
   *            builder
   * @since 0.0.1
   */
  PyDoubleImpl(final PyDoubleBuilderImpl builder)
  {
    super();
    mValue = builder.mValue;
  }

  @Override
  public void accept(final PyVisitor visitor)
  {
    visitor.visit(this);
  }

  @Override
  public int compareTo(final PyBase other)
  {
    final int result = comparePyType(other);

    if (result != 0)
    {
      return result;
    }

    return Double.compare(mValue, other.asDouble().value()); // NOPMD
  }

  @Override
  public boolean equals(final Object other)
  {
    if (!super.equals(other))
    {
      return false;
    }

    final PyBase otherCasted = (PyBase) other;

    if (Double.compare(mValue, otherCasted.asDouble().value()) != 0) // NOPMD
    {
      return false;
    }

    return true;
  }

  @Override
  public PyType getPyType()
  {
    return PyType.DOUBLE;
  }

  @Override
  public int hashCode()
  {
    final long temp = Double.doubleToLongBits(mValue);

    return HASH_PRIME * super.hashCode()
        + (int) (temp ^ (temp >>> HASHCODE_SHIFT));
  }

  @Override
  public String toString()
  {
    return Double.toString(mValue);
  }

  @Override
  public double value()
  {
    return mValue;
  }
}

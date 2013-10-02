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
import java.util.Map.Entry;

import com.jevetools.unmarshal.python.api.PyBase;
import com.jevetools.unmarshal.python.api.PyDBRow;
import com.jevetools.unmarshal.python.api.PyFactory.PyDBRowBuilder;
import com.jevetools.unmarshal.python.api.PyVisitor;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public final class PyDBRowImpl
    extends AbstractPyBaseObjectExImpl
    implements PyDBRow
{
  /**
   * Builder for {@link PyDBRow}.
   * 
   * @version 0.0.1
   * @since 0.0.1
   */
  public static final class PyDBRowBuilderImpl
      implements PyDBRowBuilder
  {
    /**
     * {@link PyBase} header.
     * 
     * @since 0.0.1
     */
    private transient PyBase mHeader = PyFactoryImpl.NONE;

    @Override
    public PyDBRow build()
    {
      return new PyDBRowImpl(this);
    }

    @Override
    public PyDBRowBuilder header(final PyBase header)
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
  PyDBRowImpl(final PyDBRowBuilderImpl builder)
  {
    super(builder.mHeader, PyFactoryImpl.FACTORY.getPyDictBuilder().build(),
        PyFactoryImpl.FACTORY.getPyListBuilder().build());
  }

  @Override
  public void accept(final PyVisitor visitor)
  {
    visitor.visit(this);
  }

  @Override
  public PyType getPyType()
  {
    return PyType.DBROW;
  }

  @Override
  public Iterator<Entry<PyBase, PyBase>> iterator()
  {
    return getDict().iterator(); // NOPMD
  }

  @Override
  public String toString()
  {
    return "DBRow";
  }
}

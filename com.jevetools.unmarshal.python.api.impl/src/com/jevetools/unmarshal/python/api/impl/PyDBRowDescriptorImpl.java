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

import com.jevetools.unmarshal.python.api.PyBase;
import com.jevetools.unmarshal.python.api.PyFactory.PyDBRowDescriptorBuilder;
import com.jevetools.unmarshal.python.api.PyDBRowDescriptor;
import com.jevetools.unmarshal.python.api.PyFactory;
import com.jevetools.unmarshal.python.api.PyVisitor;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public final class PyDBRowDescriptorImpl
    extends AbstractPyBaseObjectExImpl
    implements PyDBRowDescriptor
{
  /**
   * @since 0.0.1
   */
  private static final String INVALID_HEADER = "invalid db row header";

  /**
   * Builder for {@link PyDBRowDescriptor}.
   * 
   * @version 0.0.1
   * @since 0.0.1
   */
  public static final class PyDBRowDescriptorBuilderImpl
      implements PyDBRowDescriptorBuilder
  {
    /**
     * {@link PyBase} header.
     * 
     * @since 0.0.1
     */
    private transient PyBase mHeader = PyFactoryImpl.NONE;

    @Override
    public PyDBRowDescriptor build()
    {
      return new PyDBRowDescriptorImpl(this);
    }

    @Override
    public PyFactory.PyDBRowDescriptorBuilder header(final PyBase header)
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
  PyDBRowDescriptorImpl(final PyDBRowDescriptorBuilderImpl builder)
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
    return PyType.DBROWDESCRIPTOR;
  }

  @Override
  public Iterator<PyBase> iterator()
  {
    final PyBase header = getHeader();

    if (!header.isTuple()) // NOPMD
    {
      throw new IllegalStateException(INVALID_HEADER);
    }

    if (header.asTuple().size() < 2) // NOPMD
    {
      throw new IllegalStateException(INVALID_HEADER);
    }

    if (!header.asTuple().get(1).isTuple()) // NOPMD
    {
      throw new IllegalStateException(INVALID_HEADER);
    }

    if (header.asTuple().get(1).asTuple().size() == 0) // NOPMD
    {
      throw new IllegalStateException(INVALID_HEADER);
    }

    if (!header.asTuple().get(1).asTuple().get(0).isTuple()) // NOPMD
    {
      throw new IllegalStateException(INVALID_HEADER);
    }

    return header.asTuple().get(1).asTuple().get(0).asTuple().// NOPMD
        iterator(); // NOPMD
  }

  @Override
  public String toString()
  {
    return "DBRowDescriptor";
  }
}

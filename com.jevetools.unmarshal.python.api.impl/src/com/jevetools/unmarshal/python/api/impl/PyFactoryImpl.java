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
package com.jevetools.unmarshal.python.api.impl; //NOPMD

import com.jevetools.unmarshal.python.api.PyBool;
import com.jevetools.unmarshal.python.api.PyFactory;
import com.jevetools.unmarshal.python.api.PyNone;
import com.jevetools.unmarshal.python.api.PyString;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public final class PyFactoryImpl
    implements PyFactory
{
  /**
   * Static factory.
   */
  public static final PyFactory FACTORY = new PyFactoryImpl();

  /**
   * Static PyNone.
   */
  public static final PyNone NONE = FACTORY.getPyNoneBuilder().build();

  /**
   * Static PyString.
   */
  public static final PyString STRING = FACTORY.getPyStringBuilder().build();

  /**
   * Static PyBool true.
   */
  public static final PyBool TRUE = FACTORY.getPyBoolBuilder().value(true)
      .build();

  /**
   * Static PyBool false.
   */
  public static final PyBool FALSE = FACTORY.getPyBoolBuilder().value(false)
      .build();

  /**
   * Default constructor.
   */
  private PyFactoryImpl()
  {

  }

  @Override
  public PyBoolBuilder getPyBoolBuilder()
  {
    return new PyBoolImpl.PyBoolBuilderImpl();
  }

  @Override
  public PyBool getPyBoolFalse()
  {
    return FALSE;
  }

  @Override
  public PyBool getPyBoolTrue()
  {
    return TRUE;
  }

  @Override
  public PyByteBuilder getPyByteBuilder()
  {
    return new PyByteImpl.PyByteBuilderImpl();
  }

  @Override
  public PyCRowSetBuilder getPyCRowSetBuilder()
  {
    return new PyCRowSetImpl.PyCRowSetBuilderImpl();
  }

  @Override
  public PyDBRowBuilder getPyDBRowBuilder()
  {
    return new PyDBRowImpl.PyDBRowBuilderImpl();
  }

  @Override
  public PyDBRowDescriptorBuilder getPyDBRowDescriptorBuilder()
  {
    return new PyDBRowDescriptorImpl.PyDBRowDescriptorBuilderImpl();
  }

  @Override
  public PyDictBuilder getPyDictBuilder()
  {
    return new PyDictImpl.PyDictBuilderImpl();
  }

  @Override
  public PyDoubleBuilder getPyDoubleBuilder()
  {
    return new PyDoubleImpl.PyDoubleBuilderImpl();
  }

  @Override
  public PyFloatBuilder getPyFloatBuilder()
  {
    return new PyFloatImpl.PyFloatBuilderImpl();
  }

  @Override
  public PyIntBuilder getPyIntBuilder()
  {
    return new PyIntImpl.PyIntBuilderImpl();
  }

  @Override
  public PyListBuilder getPyListBuilder()
  {
    return new PyListImpl.PyListBuilderImpl(1);
  }

  @Override
  public PyListBuilder getPyListBuilder(final int size)
  {
    return new PyListImpl.PyListBuilderImpl(size);
  }

  @Override
  public PyLongBuilder getPyLongBuilder()
  {
    return new PyLongImpl.PyLongBuilderImpl();
  }

  @Override
  public PyNone getPyNone()
  {
    return NONE;
  }

  @Override
  public PyNoneBuilder getPyNoneBuilder()
  {
    return new PyNoneImpl.PyNoneBuilderImpl();
  }

  @Override
  public PyObjectBuilder getPyObjectBuilder()
  {
    return new PyObjectImpl.PyObjectBuilderImpl();
  }

  @Override
  public PyObjectExBuilder getPyObjectExBuilder()
  {
    return new PyObjectExImpl.PyObjectExBuilderImpl();
  }

  @Override
  public PyShortBuilder getPyShortBuilder()
  {
    return new PyShortImpl.PyShortBuilderImpl();
  }

  @Override
  public PyString getPyString()
  {
    return STRING;
  }

  @Override
  public PyStringBuilder getPyStringBuilder()
  {
    return new PyStringImpl.PyStringBuilderImpl();
  }

  @Override
  public PyTupleBuilder getPyTupleBuilder()
  {
    return new PyTupleImpl.PyTupleBuilderImpl(1);
  }

  @Override
  public PyTupleBuilder getPyTupleBuilder(final int size)
  {
    return new PyTupleImpl.PyTupleBuilderImpl(size);
  }
}

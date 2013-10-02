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
package com.jevetools.unmarshal.python.api.service.impl; // NOPMD

import com.jevetools.unmarshal.python.api.PyBool;
import com.jevetools.unmarshal.python.api.PyFactory;
import com.jevetools.unmarshal.python.api.PyNone;
import com.jevetools.unmarshal.python.api.PyString;
import com.jevetools.unmarshal.python.api.impl.PyFactoryImpl;
import com.jevetools.unmarshal.python.api.service.PythonFactoryService;

/**
 * Copyright (c) 2013, jEVETools.
 *
 * All rights reserved.
 *
 * @version 0.0.1
 * @since 0.0.1
 */
public final class PythonFactoryServiceImpl
    implements PythonFactoryService
{
  /**
   * Static factory.
   */
  private static final PyFactory FACTORY = PyFactoryImpl.FACTORY;

  @Override
  public PyNone getPyNone()
  {
    return FACTORY.getPyNone();
  }

  @Override
  public PyNoneBuilder getPyNoneBuilder()
  {
    return FACTORY.getPyNoneBuilder();
  }

  @Override
  public PyBoolBuilder getPyBoolBuilder()
  {
    return FACTORY.getPyBoolBuilder();
  }

  @Override
  public PyBool getPyBoolFalse()
  {
    return FACTORY.getPyBoolFalse();
  }

  @Override
  public PyBool getPyBoolTrue()
  {
    return FACTORY.getPyBoolTrue();
  }

  @Override
  public PyByteBuilder getPyByteBuilder()
  {
    return FACTORY.getPyByteBuilder();
  }

  @Override
  public PyShortBuilder getPyShortBuilder()
  {
    return FACTORY.getPyShortBuilder();
  }

  @Override
  public PyIntBuilder getPyIntBuilder()
  {
    return FACTORY.getPyIntBuilder();
  }

  @Override
  public PyLongBuilder getPyLongBuilder()
  {
    return FACTORY.getPyLongBuilder();
  }

  @Override
  public PyFloatBuilder getPyFloatBuilder()
  {
    return FACTORY.getPyFloatBuilder();
  }

  @Override
  public PyDoubleBuilder getPyDoubleBuilder()
  {
    return FACTORY.getPyDoubleBuilder();
  }

  @Override
  public PyString getPyString()
  {
    return FACTORY.getPyString();
  }

  @Override
  public PyStringBuilder getPyStringBuilder()
  {
    return FACTORY.getPyStringBuilder();
  }

  @Override
  public PyTupleBuilder getPyTupleBuilder()
  {
    return FACTORY.getPyTupleBuilder();
  }

  @Override
  public PyTupleBuilder getPyTupleBuilder(final int aSize)
  {
    return FACTORY.getPyTupleBuilder(aSize);
  }

  @Override
  public PyListBuilder getPyListBuilder()
  {
    return FACTORY.getPyListBuilder();
  }

  @Override
  public PyListBuilder getPyListBuilder(final int aSize)
  {
    return FACTORY.getPyListBuilder(aSize);
  }

  @Override
  public PyDictBuilder getPyDictBuilder()
  {
    return FACTORY.getPyDictBuilder();
  }

  @Override
  public PyObjectBuilder getPyObjectBuilder()
  {
    return FACTORY.getPyObjectBuilder();
  }

  @Override
  public PyObjectExBuilder getPyObjectExBuilder()
  {
    return FACTORY.getPyObjectExBuilder();
  }

  @Override
  public PyDBRowBuilder getPyDBRowBuilder()
  {
    return FACTORY.getPyDBRowBuilder();
  }

  @Override
  public PyDBRowDescriptorBuilder getPyDBRowDescriptorBuilder()
  {
    return FACTORY.getPyDBRowDescriptorBuilder();
  }

  @Override
  public PyCRowSetBuilder getPyCRowSetBuilder()
  {
    return FACTORY.getPyCRowSetBuilder();
  }
}

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
package com.jevetools.unmarshal.api.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Path;

import org.osgi.service.log.LogService;

import com.jevetools.unmarshal.api.Reader;
import com.jevetools.unmarshal.api.ParseException;
import com.jevetools.unmarshal.api.impl.PrintStreamVisitor;
import com.jevetools.unmarshal.api.impl.RecursiveImpl;
import com.jevetools.unmarshal.api.service.UnmarshalService;
import com.jevetools.unmarshal.python.api.PyBase;
import com.jevetools.unmarshal.python.api.PyVisitor;
import com.jevetools.unmarshal.python.api.service.PythonFactoryService;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public final class UnmarshalServiceImpl
    implements UnmarshalService
{
  /**
   * PyFactoryService reference.
   */
  private transient PythonFactoryService mPyFactoryService;

  /**
   * {@link LogService} reference.
   */
  private transient LogService mLogService;

  /**
   * @param service
   *            PyFactoryService
   */
  protected void bindPythonFactoryService(final PythonFactoryService service)
  {
    mPyFactoryService = service;
    RecursiveImpl.setFACTORY(mPyFactoryService);
  }

  /**
   * @param serv
   *            PyFactoryService
   */
  protected void unbindPythonFactoryService(final PythonFactoryService serv)
  {
    if (mPyFactoryService == serv) // NOPMD
    {
      mPyFactoryService = null; // NOPMD
      RecursiveImpl.setFACTORY(null);
    }
  }

  /**
   * @param service
   *            PyFactoryService
   */
  protected void bindLogService(final LogService service)
  {
    mLogService = service;
  }

  /**
   * @param serv
   *            PyFactoryService
   */
  protected void unbindLogService(final LogService serv)
  {
    if (mLogService == serv) // NOPMD
    {
      mLogService = null; // NOPMD
    }
  }

  @Override
  public PyBase read(final Path path) throws ParseException, IOException
  {
    final Reader reader = new RecursiveImpl();
    return reader.read(path);
  }

  @Override
  public PyBase read(final InputStream aStream) throws ParseException,
      IOException
  {
    final Reader reader = new RecursiveImpl();

    return reader.read(aStream);
  }

  @Override
  public void dump(final PyBase pyBase, final PrintStream stream)
  {
    final PyVisitor pyVisitor = new PrintStreamVisitor(stream);
    pyBase.accept(pyVisitor);
  }

  @Override
  public PyBase getDictKey(final String key)
  {
    if (mPyFactoryService != null)
    {
      return mPyFactoryService.getPyStringBuilder().value(key).build(); // NOPMD
    }
    throw new IllegalStateException("Invalid PythonFactoryService");
  }
}

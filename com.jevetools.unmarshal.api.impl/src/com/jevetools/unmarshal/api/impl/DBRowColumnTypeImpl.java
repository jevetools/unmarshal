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
package com.jevetools.unmarshal.api.impl;

import java.util.concurrent.ConcurrentHashMap;

import com.jevetools.unmarshal.python.api.PyBool;
import com.jevetools.unmarshal.python.api.PyByte;
import com.jevetools.unmarshal.python.api.PyDouble;
import com.jevetools.unmarshal.python.api.PyInt;
import com.jevetools.unmarshal.python.api.PyLong;
import com.jevetools.unmarshal.python.api.PyShort;
import com.jevetools.unmarshal.python.api.PyString;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public enum DBRowColumnTypeImpl implements DBRowColumnType
{
  /**
     */
  SHORT(2)
  {
    @Override
    public PyShort read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readShort(byteBuffer);
    }

    @Override
    public DBRowColumnTypeSize typeSize()
    {
      return DBRowColumnTypeSizeImpl.BIT16;
    }
  },
  /**
     */
  BOOL(11)
  {
    @Override
    public PyBool read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readBool(byteBuffer);
    }

    @Override
    public DBRowColumnTypeSize typeSize()
    {
      return DBRowColumnTypeSizeImpl.BIT1;
    }
  },
  /**
     */
  INT(3)
  {
    @Override
    public PyInt read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readInt(byteBuffer);
    }

    @Override
    public DBRowColumnTypeSize typeSize()
    {
      return DBRowColumnTypeSizeImpl.BIT32;
    }
  },
  /**
     */
  DOUBLE(5)
  {
    @Override
    public PyDouble read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readDouble(byteBuffer);
    }

    @Override
    public DBRowColumnTypeSize typeSize()
    {
      return DBRowColumnTypeSizeImpl.BIT64;
    }
  },
  /**
     */
  CURRENCY(6)
  {
    @Override
    public PyLong read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readLong(byteBuffer);
    }

    @Override
    public DBRowColumnTypeSize typeSize()
    {
      return DBRowColumnTypeSizeImpl.BIT64;
    }
  },
  /**
     */
  WINFILETIME(64)
  {
    @Override
    public PyLong read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readLong(byteBuffer);
    }

    @Override
    public DBRowColumnTypeSize typeSize()
    {
      return DBRowColumnTypeSizeImpl.BIT64;
    }
  },
  /**
     */
  BYTE(17)
  {
    @Override
    public PyByte read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readByte(byteBuffer);
    }

    @Override
    public DBRowColumnTypeSize typeSize()
    {
      return DBRowColumnTypeSizeImpl.BIT8;
    }
  },
  /**
     */
  LONG(20)
  {
    @Override
    public PyLong read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readLong(byteBuffer);
    }

    @Override
    public DBRowColumnTypeSize typeSize()
    {
      return DBRowColumnTypeSizeImpl.BIT64;
    }
  },
  /**
     */
  STRING(129)
  {
    @Override
    public PyString read(final MarshalBuffer byteBuffer)
    {
      throw new AssertionError();
    }

    @Override
    public DBRowColumnTypeSize typeSize()
    {
      return DBRowColumnTypeSizeImpl.BIT0;
    }
  },
  /**
     */
  UNICODE(130)
  {
    @Override
    public PyString read(final MarshalBuffer byteBuffer)
    {
      throw new AssertionError();
    }

    @Override
    public DBRowColumnTypeSize typeSize()
    {
      return DBRowColumnTypeSizeImpl.BIT0;
    }
  };

  /**
     */
  private final int mType; // NOPMD

  /**
   * Copyright (c) 2013, jEVETools.
   * 
   * All rights reserved.
   * 
   * @version 0.0.1
   * @since 0.0.1
   */
  private static class DBMap
      extends ConcurrentHashMap<Integer, DBRowColumnTypeImpl>
  {
    /**
     */
    private static final long serialVersionUID = -4210341612033506226L;
  };

  /**
     */
  private static final ConcurrentHashMap<Integer, DBRowColumnTypeImpl> TYPES = 
      new DBMap();

  static
  {
    for (final DBRowColumnTypeImpl type : values()) // NOPMD
    {
      TYPES.put(Integer.valueOf(type.mType), type);
    }
  }

  /**
   * @param type
   *            type
   * @return type reader
   */
  public static final DBRowColumnTypeImpl get(final int type)
  {
    if (TYPES.containsKey(Integer.valueOf(type)))
    {
      return TYPES.get(Integer.valueOf(type));
    }
    throw new IllegalStateException("type: " + type);
  }

  /**
   * @param type
   *            type
   */
  private DBRowColumnTypeImpl(final int type)
  {
    mType = type;
  }
}

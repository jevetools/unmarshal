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

import com.jevetools.unmarshal.api.IllegalOpCodeException;
import com.jevetools.unmarshal.api.ParseException;
import com.jevetools.unmarshal.python.api.PyBase;
import com.jevetools.unmarshal.python.api.PyBaseObjectEx;
import com.jevetools.unmarshal.python.api.PyBool;
import com.jevetools.unmarshal.python.api.PyByte;
import com.jevetools.unmarshal.python.api.PyDBRow;
import com.jevetools.unmarshal.python.api.PyDict;
import com.jevetools.unmarshal.python.api.PyDouble;
import com.jevetools.unmarshal.python.api.PyInt;
import com.jevetools.unmarshal.python.api.PyList;
import com.jevetools.unmarshal.python.api.PyNone;
import com.jevetools.unmarshal.python.api.PyObject;
import com.jevetools.unmarshal.python.api.PyShort;
import com.jevetools.unmarshal.python.api.PyString;
import com.jevetools.unmarshal.python.api.PyTuple;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public enum OpCodeReaderImpl implements OpCodeReader<PyBase>
{
  /**
   * None.
   */
  NONE((byte) 0x01)
  {
    @Override
    public PyNone read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readNone(byteBuffer);
    }
  },

  /**
   * Identifier.
   */
  IDENTIFIER((byte) 0x02)
  {
    @Override
    public PyBase read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readGlobal(byteBuffer);
    }
  },

  /**
   * Int.
   */
  INT((byte) 0x04)
  {
    @Override
    public PyInt read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readInt(byteBuffer);
    }
  },

  /**
   * Short.
   */
  SHORT((byte) 0x05)
  {
    @Override
    public PyShort read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readShort(byteBuffer);
    }
  },

  /**
   * Byte.
   */
  BYTE((byte) 0x06)
  {
    @Override
    public PyByte read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readByte(byteBuffer);
    }
  },

  /**
   * Int 0.
   */
  INTZERO((byte) 0x08)
  {
    @Override
    public PyInt read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readIntZero(byteBuffer);
    }
  },

  /**
   * Int 0.
   */
  INTONE((byte) 0x09)
  {
    @Override
    public PyInt read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readIntOne(byteBuffer);
    }
  },

  /**
   * Double.
   */
  DOUBLE((byte) 0x0a)
  {
    @Override
    public PyDouble read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readDouble(byteBuffer);
    }
  },

  /**
   * Double 0.0.
   */
  DOUBLEZERO((byte) 0x0b)
  {
    @Override
    public PyDouble read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readDoubleZero(byteBuffer);
    }
  },

  /**
   * Empty string.
   */
  STRINGEMPTY((byte) 0x0e)
  {
    @Override
    public PyString read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readUnicodeEmpty(byteBuffer);
    }
  },

  /**
   * One character string.
   */
  STRINGONE((byte) 0x0f)
  {
    @Override
    public PyString read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readStringOne(byteBuffer);
    }
  },

  /**
   * String reference.
   */
  STRINGREF((byte) 0x11)
  {
    @Override
    public PyString read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readStringReference(byteBuffer);
    }
  },

  /**
   * Reference.
   */
  REFERENCE((byte) 0x1b)
  {
    @Override
    public PyBase read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readReference(byteBuffer);
    }
  },

  /**
   * Boolean true.
   */
  TRUE((byte) 0x1f)
  {
    @Override
    public PyBool read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readTrue(byteBuffer);
    }
  },

  /**
   * Unicode string.
   */
  UNICODE((byte) 0x12)
  {
    @Override
    public PyString read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readUnicode(byteBuffer);
    }
  },

  /**
   * String buffer.
   */
  BUFFER((byte) 0x13)
  {
    @Override
    public PyString read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readBuffer(byteBuffer);
    }
  },

  /**
   * Tuple.
   */
  TUPLEVAR((byte) 0x14)
  {
    @Override
    public PyTuple read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readTuple(byteBuffer);
    }
  },

  /**
   * List.
   */
  LISTVAR((byte) 0x15)
  {
    @Override
    public PyList read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readList(byteBuffer);
    }
  },

  /**
   * Dict.
   */
  DICT((byte) 0x16)
  {
    @Override
    public PyDict read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readDict(byteBuffer);
    }
  },

  /**
   * None.
   */
  OBJECT((byte) 0x17)
  {
    @Override
    public PyObject read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readObject(byteBuffer);
    }
  },

  /**
   * Boolean false.
   */
  FALSE((byte) 0x20)
  {
    @Override
    public PyBool read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readFalse(byteBuffer);
    }
  },

  /**
   * ObjectEx.
   */
  OBJECTSHARED((byte) 0x22)
  {
    @Override
    public PyBase read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readObjectEx(byteBuffer);
    }
  },

  /**
   * ObjectEx.
   */
  OBJECTEX((byte) 0x23)
  {
    @Override
    public PyBaseObjectEx read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readObjectEx(byteBuffer);
    }
  },

  /**
   * Tuple with one element.
   */
  TUPLEONE((byte) 0x25)
  {
    @Override
    public PyTuple read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readTupleOne(byteBuffer);
    }
  },

  /**
   * Empty list.
   */
  LISTEMPTY((byte) 0x26)
  {
    @Override
    public PyList read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readListEmpty(byteBuffer);
    }
  },

  /**
   * List with one element.
   */
  LISTONE((byte) 0x27)
  {
    @Override
    public PyList read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readListOne(byteBuffer);
    }
  },

  /**
   * Empty unicode string.
   */
  UNICODEEMPTY((byte) 0x28)
  {
    @Override
    public PyString read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readUnicodeEmpty(byteBuffer);
    }
  },

  /**
   * One character unicode string.
   */
  UNICODEONE((byte) 0x29)
  {
    @Override
    public PyString read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readUnicodeOne(byteBuffer);
    }
  },

  /**
   * Packed row format.
   */
  PACKED((byte) 0x2a)
  {
    @Override
    public PyDBRow read(final MarshalBuffer byteBuffer) throws ParseException
    {
      return RecursiveImpl.readPacked(byteBuffer);
    }
  },

  /**
   * Embedded stream.
   */
  EMBEDDED((byte) 0x2b)
  {
    @Override
    public PyBase read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readEmbedded(byteBuffer);
    }
  },

  /**
   * Tuple with two elements.
   */
  TUPLETWO((byte) 0x2c)
  {
    @Override
    public PyTuple read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readTupleTwo(byteBuffer);
    }
  },

  /**
   * UTF8 buffer.
   */
  UTF8BUFFER((byte) 0x2e)
  {
    @Override
    public PyString read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readBuffer(byteBuffer);
    }
  },

  /**
   * Variable length int.
   */
  INTVAR((byte) 0x2f)
  {
    @Override
    public PyBase read(final MarshalBuffer byteBuffer)
    {
      return RecursiveImpl.readIntVar(byteBuffer);
    }
  };

  /**
   * opCode.
   */
  private final Byte mOpCode; // NOPMD

  /**
   * Copyright (c) 2013, jEVETools.
   * 
   * All rights reserved.
   * 
   * @version 0.0.1
   * @since 0.0.1
   */
  private static class CodeMap
      extends ConcurrentHashMap<Byte, OpCodeReaderImpl>
  {
    /**
     */
    private static final long serialVersionUID = -5837547417000553294L;
  };

  /**
   * OpCode Map.
   */
  private static final ConcurrentHashMap<Byte, OpCodeReaderImpl> CODES = 
      new CodeMap();

  static
  {
    for (final OpCodeReaderImpl opCode : values()) // NOPMD
    {
      CODES.put(opCode.mOpCode, opCode);
    }
  }

  /**
   * Returns opCode reader.
   * 
   * @param opCode
   *            opCode
   * @return reader
   * @throws IllegalOpCodeException
   *             when unknown opcode is requested
   */
  public static final OpCodeReaderImpl get(final byte opCode)
      throws IllegalOpCodeException
  {
    if (CODES.containsKey(Byte.valueOf(opCode)))
    {
      return CODES.get(Byte.valueOf(opCode));
    }
    throw new IllegalOpCodeException(opCode);
  }

  /**
   * Constructor.
   * 
   * @param opCode
   *            opCode
   */
  private OpCodeReaderImpl(final byte opCode)
  {
    mOpCode = Byte.valueOf(opCode);
  }
}

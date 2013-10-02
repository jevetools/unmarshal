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
package com.jevetools.unmarshal.api.impl; // NOPMD

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.jevetools.unmarshal.api.IllegalOpCodeException;
import com.jevetools.unmarshal.api.Reader;
import com.jevetools.unmarshal.api.ParseException;
import com.jevetools.unmarshal.api.impl.MarshalBufferImpl.DBRowMap;
import com.jevetools.unmarshal.api.impl.MarshalBufferImpl.DBRowMapPair;
import com.jevetools.unmarshal.api.impl.MarshalBufferImpl.DBRowPair;
import com.jevetools.unmarshal.python.api.PyBase;
import com.jevetools.unmarshal.python.api.PyBaseObjectEx;
import com.jevetools.unmarshal.python.api.PyBool;
import com.jevetools.unmarshal.python.api.PyByte;
import com.jevetools.unmarshal.python.api.PyCRowSet;
import com.jevetools.unmarshal.python.api.PyDBRow;
import com.jevetools.unmarshal.python.api.PyDBRowDescriptor;
import com.jevetools.unmarshal.python.api.PyDict;
import com.jevetools.unmarshal.python.api.PyDouble;
import com.jevetools.unmarshal.python.api.PyFactory;
import com.jevetools.unmarshal.python.api.PyInt;
import com.jevetools.unmarshal.python.api.PyList;
import com.jevetools.unmarshal.python.api.PyLong;
import com.jevetools.unmarshal.python.api.PyNone;
import com.jevetools.unmarshal.python.api.PyObject;
import com.jevetools.unmarshal.python.api.PyObjectEx;
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
public class RecursiveImpl
    implements Reader
{
  /**
   * @since 0.0.1
   */
  private static final int BUFFER_SIZE = 1024;

  /**
   * Shared bit filter.
   */
  private static final int SHARED_BIT_FILTER = 0x3f;

  /**
   * Shared bit mask.
   */
  private static final byte SHARED_MASK = 0x40;

  /**
   * Max byte value.
   */
  private static final int MAX_BYTE = 255;

  /**
   * Max byte value in hex.
   */
  private static final int MAXBYTE_HEX = 0xFF;

  /**
   * Object interator marker.
   */
  private static final byte OBJECT_MARKER = (byte) 0x2d;

  /**
   * PyBase factory.
   */
  private static PyFactory mFACTORY;

  /**
   * @param dbRowDescriptor
   *            db row descriptor
   * @return map
   * @throws ParseException
   *             on error
   */
  private static DBRowMapPair extractDBRowDescriptorInfo(
      final PyDBRowDescriptor dbRowDescriptor) throws ParseException
  {

    final DBRowMap map = new DBRowMap()
    {
      /**
             */
      private static final long serialVersionUID = 5025033142561005030L;

      {
        put(DBRowColumnTypeSizeImpl.BIT64, new ArrayList<DBRowPair>());
        put(DBRowColumnTypeSizeImpl.BIT32, new ArrayList<DBRowPair>());
        put(DBRowColumnTypeSizeImpl.BIT16, new ArrayList<DBRowPair>());
        put(DBRowColumnTypeSizeImpl.BIT8, new ArrayList<DBRowPair>());
        put(DBRowColumnTypeSizeImpl.BIT1, new ArrayList<DBRowPair>());
        put(DBRowColumnTypeSizeImpl.BIT0, new ArrayList<DBRowPair>());
      }
    };

    short size = 0;

    for (final PyBase pyBase : dbRowDescriptor)
    {
      if (!pyBase.isTuple() || (pyBase.asTuple().size() != 2))
      {
        throw new ParseException("invalid db row header");
      }
      if (!pyBase.asTuple().get(0).isString())
      {
        throw new ParseException("invalid db row header");
      }
      if (!pyBase.asTuple().get(1).isByte()
          && !pyBase.asTuple().get(1).isShort())
      {
        throw new ParseException("invalid db row header");
      }

      DBRowColumnTypeImpl rowColumnType;

      if (pyBase.asTuple().get(1).isByte())
      {
        rowColumnType = DBRowColumnTypeImpl.get(pyBase.asTuple().get(1)
            .asByte().value());
      }
      else
      {
        rowColumnType = DBRowColumnTypeImpl.get(pyBase.asTuple().get(1)
            .asShort().value());
      }

      size += rowColumnType.typeSize().sizeInBits();

      map.get(rowColumnType.typeSize()).add(
          new DBRowPair(rowColumnType, pyBase.asTuple().get(0).asString()
              .value()));
    }

    return new DBRowMapPair(Short.valueOf(size), map);
  }

  /**
   * Returns {@code byte} from bit set.
   * 
   * @param bitSet
   *            bit set
   * 
   * @return {@code byte} from bit set.
   */
  private static byte fromBitSet(final BitSet bitSet)
  {
    byte b = 0;

    for (int i = 0; i < bitSet.length(); i++)
    {
      if (bitSet.get(i))
      {
        b |= 1 << i;
      }
    }
    return b;
  }

  /**
   * @param dbRowDescriptor
   *            db row descriptor
   * @param marshalByteBuffer
   *            byte buffer
   * @return map
   * @throws ParseException
   *             on error
   */
  private static DBRowMap getDBRowDescriptorColumnsMap(
      final PyDBRowDescriptor dbRowDescriptor,
      final MarshalBuffer marshalByteBuffer) throws ParseException
  {
    if (!marshalByteBuffer.containsKey(dbRowDescriptor))
    {
      marshalByteBuffer.put(dbRowDescriptor,
          extractDBRowDescriptorInfo(dbRowDescriptor));
    }
    return marshalByteBuffer.get(dbRowDescriptor).getSecond();
  }

  /**
   * @param dbRowDescriptor
   *            db row descriptor
   * @param marshalByteBuffer
   *            byte buffer
   * @return map
   * @throws ParseException
   *             on error
   */
  private static Short getDBRowDescriptorSizeInBits(
      final PyDBRowDescriptor dbRowDescriptor,
      final MarshalBuffer marshalByteBuffer) throws ParseException
  {
    if (!marshalByteBuffer.containsKey(dbRowDescriptor))
    {
      marshalByteBuffer.put(dbRowDescriptor,
          extractDBRowDescriptorInfo(dbRowDescriptor));
    }
    return marshalByteBuffer.get(dbRowDescriptor).getFirst();
  }

  /**
   * @return PyFactory
   */
  private static PyFactory getFactory()
  {
    if (mFACTORY != null)
    {
      return mFACTORY;
    }
    throw new IllegalStateException("PyFactory not set.");
  }

  /**
   * Returns object name or null.
   * 
   * @param pyHeader
   *            header
   * @return object name or null
   */
  private static String getObjectName(final PyBase pyHeader)
  {
    String className = null;
    if (pyHeader.isTuple())
    {
      if (pyHeader.asTuple().get(0).isString())
      {
        className = pyHeader.asTuple().get(0).asString().value();
      }
      else if (pyHeader.asTuple().get(0).isTuple())
      {
        if (pyHeader.asTuple().get(0).asTuple().size() == 1)
        {
          if (pyHeader.asTuple().get(0).asTuple().get(0).isString())
          {
            className = pyHeader.asTuple().get(0).asTuple().get(0).asString()
                .value();
          }
        }
      }
    }
    return (className);
  }

  /**
   * Returns the length. The length is stored as an unsigned byte. If its
   * value is exceeds 255 it is stored as an integer.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return length
   */
  private static int length(final MarshalBuffer byteBuffer)
  {

    int length = 0;

    length = byteBuffer.get() & MAXBYTE_HEX;

    if (length == MAX_BYTE)
    {
      length = byteBuffer.getInt();
    }

    return length;
  }

  /**
   * @param buffer
   *            buffer
   * @return PyBase
   * @throws RuntimeException
   * @throws IllegalStateException
   */
  protected static PyBase load(final MarshalBuffer buffer)
  {

    try
    {
      buffer.initialize();

      final PyBase pyBase = read(buffer);
      if (buffer.processed())
      {
        return (pyBase);
      }
      throw new IllegalStateException(String.format(
          "Unprocessed bytes left in stream - offset: %08d",
          (buffer.position() + buffer.parentPosition())));
    }
    catch (final IllegalStateException e)
    {
      throw new IllegalStateException(e.getMessage(), e);
    }
    catch (final IllegalOpCodeException e)
    {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }

  /**
   * Read.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return PyBase
   */
  private static PyBase read(final MarshalBuffer byteBuffer)
  {
    int offset = 0;
    int realMagicByte = 0;
    boolean sharedPy = false;

    try
    {
      offset = byteBuffer.position() + byteBuffer.parentPosition();
      final byte magicByte = byteBuffer.get();
      sharedPy = (magicByte & RecursiveImpl.SHARED_MASK) != 0;
      realMagicByte = (magicByte & MAXBYTE_HEX & SHARED_BIT_FILTER);
      int sharedPos = 0;

      final OpCodeReaderImpl opCode = OpCodeReaderImpl
          .get((byte) realMagicByte);

      if (sharedPy)
      {
        sharedPos = byteBuffer.getNextSharedIndex();
      }

      final PyBase pyBase = opCode.read(byteBuffer);

      if (sharedPy)
      {
        byteBuffer.setShared(sharedPos, pyBase);
      }

      return (pyBase);
    }
    catch (final IllegalOpCodeException e)
    {
      throw new IllegalStateException(String.format(
          "Illegal Op Code - offset: %08d opcode: 0x%02x", offset,
          (e.getOpCode() & MAXBYTE_HEX)));
    }
    catch (final BufferUnderflowException e)
    {
      throw new IllegalStateException(String.format(
          "Buffer Underflow - offset: %08d opcode: 0x%02x", offset,
          (realMagicByte & MAXBYTE_HEX)));
    }
    catch (final ParseException e)
    {
      throw new IllegalStateException(String.format(
          "offset: %08d opcode: 0x%02x", offset, 
          (realMagicByte & MAXBYTE_HEX)));
    }
  }

  /**
   * Returns <tt>PyBool</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyBool</tt>
   */
  protected static PyBool readBool(final MarshalBuffer byteBuffer)
  {
    if (byteBuffer.getBoolean())
    {
      return (getFactory().getPyBoolTrue());
    }
    else
    {
      return (getFactory().getPyBoolFalse());
    }
  }

  /**
   * Returns <tt>PyString</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyString</tt>
   */
  protected static PyString readBuffer(final MarshalBuffer byteBuffer)
  {
    return (readString(byteBuffer));
  }

  /**
   * Returns <tt>PyByte</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyByte</tt>
   */
  protected static PyByte readByte(final MarshalBuffer byteBuffer)
  {
    final PyFactory.PyByteBuilder builder = getFactory().getPyByteBuilder();
    builder.value(byteBuffer.get());
    return (builder.build());
  }

  /**
   * Returns <tt>PyCRowSet</tt>.
   * 
   * @param pyHeader
   *            header
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyCRowSet</tt>
   */
  protected static PyCRowSet readCRowSet(final PyBase pyHeader,
      final MarshalBuffer byteBuffer)
  {
    final PyFactory.PyCRowSetBuilder builder = getFactory()
        .getPyCRowSetBuilder();
    builder.header(pyHeader);
    final PyCRowSet pyCRowSet = builder.build();

    read(pyCRowSet, byteBuffer);

    return (pyCRowSet);
  }

  /**
   * Returns <tt>PyDBRowDescriptor</tt>.
   * 
   * @param pyHeader
   *            header
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyDBRowDescriptor</tt>
   */
  protected static PyDBRowDescriptor readDBRowDescriptor(final PyBase pyHeader,
      final MarshalBuffer byteBuffer)
  {
    final PyFactory.PyDBRowDescriptorBuilder builder = getFactory()
        .getPyDBRowDescriptorBuilder();
    builder.header(pyHeader);
    final PyDBRowDescriptor pyDBRowDescriptor = builder.build();

    read(pyDBRowDescriptor, byteBuffer);

    return (pyDBRowDescriptor);
  }

  /**
   * @param aObject object
   * @param byteBuffer buffer
   *
   * @since 0.0.1
   */
  protected static void read(final PyBaseObjectEx aObject,
      final MarshalBuffer byteBuffer)
  {
    while (byteBuffer.peekByte() != OBJECT_MARKER)
    {
      aObject.getList().add(read(byteBuffer));
    }
    byteBuffer.get();
    PyBase key = null;
    PyBase value = null;

    while (byteBuffer.peekByte() != OBJECT_MARKER)
    {
      value = read(byteBuffer);
      key = read(byteBuffer);
      aObject.getDict().put(key, value);
    }
    byteBuffer.get();
  }

  /**
   * Returns <tt>PyDict</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyDict</tt>
   */
  protected static PyDict readDict(final MarshalBuffer byteBuffer)
  {
    final int size = length(byteBuffer);
    final PyFactory.PyDictBuilder builder = getFactory().getPyDictBuilder();
    final PyDict pyDict = builder.build();
    PyBase key = null;
    PyBase value = null;
    for (int idx = 0; idx < size; idx++)
    {
      value = read(byteBuffer);
      key = read(byteBuffer);
      pyDict.put(key, value);
    }
    return (pyDict);
  }

  /**
   * Returns <tt>PyDouble</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyDouble</tt>
   */
  protected static PyDouble readDouble(final MarshalBuffer byteBuffer)
  {
    final PyFactory.PyDoubleBuilder builder = getFactory().getPyDoubleBuilder();
    builder.value(byteBuffer.getDouble());
    return (builder.build());
  }

  /**
   * Returns <tt>PyDouble</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyDouble</tt>
   */
  protected static PyDouble readDoubleZero(final MarshalBuffer byteBuffer)
  {
    final PyFactory.PyDoubleBuilder builder = getFactory().getPyDoubleBuilder();
    builder.value(0.0);
    return (builder.build());
  }

  /**
   * Returns <tt>PyByte</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyByte</tt>
   */
  protected static PyBase readEmbedded(final MarshalBuffer byteBuffer)
  {
    final int size = length(byteBuffer);
    final int parentOffset = byteBuffer.position();

    final byte[] bytes = byteBuffer.readBytes(size);

    final ByteBuffer subBuffer = ByteBuffer.wrap(bytes);
    subBuffer.order(ByteOrder.LITTLE_ENDIAN);

    final MarshalBuffer bufferReader = new MarshalBufferImpl(subBuffer,
        parentOffset);

    try
    {
      return (load(bufferReader));
    }
    catch (final IllegalStateException e)
    {
      throw new IllegalStateException("subbuffer: " + e.getMessage(), e);
    }
  }

  /**
   * Returns <tt>PyBool</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyBool</tt>
   */
  protected static PyBool readFalse(final MarshalBuffer byteBuffer)
  {
    return (getFactory().getPyBoolFalse());
  }

  /**
   * Returns <tt>PyBase</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyBase</tt>
   */
  protected static PyBase readGlobal(final MarshalBuffer byteBuffer)
  {
    return (readString(byteBuffer));
  }

  /**
   * Returns <tt>PyInt</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyInt</tt>
   */
  protected static PyInt readInt(final MarshalBuffer byteBuffer)
  {
    final PyFactory.PyIntBuilder builder = getFactory().getPyIntBuilder();
    builder.value(byteBuffer.getInt());
    return (builder.build());
  }

  /**
   * Returns <tt>PyInt</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyInt</tt>
   */
  protected static PyInt readIntOne(final MarshalBuffer byteBuffer)
  {
    final PyFactory.PyIntBuilder builder = getFactory().getPyIntBuilder();
    builder.value(1);
    return (builder.build());
  }

  /**
   * Returns <tt>PyBase</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyBase</tt>
   */
  protected static PyBase readIntVar(final MarshalBuffer byteBuffer)
  {
    final int size = length(byteBuffer);

    switch (size)
    {
      case 0:
        return getFactory().getPyLongBuilder().value(0).build();
      case 2:
        return getFactory().getPyShortBuilder().value(byteBuffer.getShort())
            .build();
      case Byte.SIZE / 2:
        return getFactory().getPyIntBuilder().value(byteBuffer.getInt())
            .build();
      case Byte.SIZE:
        return getFactory().getPyLongBuilder().value(byteBuffer.getLong())
            .build();
      default:
        final byte[] bytes = byteBuffer.readBytes(size);
        final BigInteger bi = new BigInteger(bytes);
        return getFactory().getPyLongBuilder().value(bi.longValue()).build();
    }
  }

  /**
   * Returns <tt>PyInt</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyInt</tt>
   */
  protected static PyInt readIntZero(final MarshalBuffer byteBuffer)
  {
    final PyFactory.PyIntBuilder builder = getFactory().getPyIntBuilder();
    builder.value(0);
    return (builder.build());
  }

  /**
   * Returns <tt>PyList</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyList</tt>
   */
  protected static PyList readList(final MarshalBuffer byteBuffer)
  {
    return (readListN(byteBuffer, length(byteBuffer)));
  }

  /**
   * Returns <tt>PyList</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyList</tt>
   */
  protected static PyList readListEmpty(final MarshalBuffer byteBuffer)
  {
    return (readListN(byteBuffer, 0));
  }

  /**
   * Returns <tt>PyList</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @param size
   *            size
   * @return <tt>PyList</tt>
   */
  protected static PyList readListN(final MarshalBuffer byteBuffer,
      final int size)
  {
    final PyFactory.PyListBuilder builder = getFactory().getPyListBuilder(size);
    final PyList pyList = builder.build();
    for (int idx = 0; idx < size; idx++)
    {
      pyList.add(read(byteBuffer));
    }
    return (pyList);
  }

  /**
   * Returns <tt>PyList</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyList</tt>
   */
  protected static PyList readListOne(final MarshalBuffer byteBuffer)
  {
    return (readListN(byteBuffer, 1));
  }

  /**
   * Returns <tt>PyLong</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyLong</tt>
   */
  protected static PyLong readLong(final MarshalBuffer byteBuffer)
  {
    final PyFactory.PyLongBuilder builder = getFactory().getPyLongBuilder();
    builder.value(byteBuffer.getLong());
    return (builder.build());
  }

  /**
   * Returns <tt>PyNone</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyNone</tt>
   */
  protected static PyNone readNone(final MarshalBuffer byteBuffer)
  {
    return (getFactory().getPyNone());
  }

  /**
   * Returns <tt>PyObject</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyObject</tt>
   */
  protected static PyObject readObject(final MarshalBuffer byteBuffer)
  {
    final PyFactory.PyObjectBuilder builder = getFactory().getPyObjectBuilder();
    builder.header(read(byteBuffer));
    builder.body(read(byteBuffer));
    final PyObject pyObject = builder.build();
    return (pyObject);
  }

  /**
   * Returns <tt>PyBaseObjectEx</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyBaseObjectEx</tt>
   */
  protected static PyBaseObjectEx readObjectEx(final MarshalBuffer byteBuffer)
  {
    final PyBase pyHeader = read(byteBuffer);
    final String className = getObjectName(pyHeader);
    if (className != null)
    {
      if (className.compareTo("blue.DBRowDescriptor") == 0)
      {
        return (readDBRowDescriptor(pyHeader, byteBuffer));
      }
      else if (className.compareTo("dbutil.CRowset") == 0)
      {
        return (readCRowSet(pyHeader, byteBuffer));
      }
    }
    final PyFactory.PyObjectExBuilder builder = getFactory()
        .getPyObjectExBuilder();
    builder.header(pyHeader);
    final PyObjectEx pyObjectEx = builder.build();

    read(pyObjectEx, byteBuffer);

    return (pyObjectEx);
  }

  /**
   * Returns <tt>PyDBRow</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyDBRow</tt>
   * @throws ParseException
   *             when unable to parse buffer
   */
  protected static PyDBRow readPacked(final MarshalBuffer byteBuffer)
      throws ParseException
  {
    final PyBase pyHeader = read(byteBuffer);

    final int length = length(byteBuffer);
    final byte[] bytes = new byte[length];
    byteBuffer.get(bytes);

    final int uncompressedSize = (getDBRowDescriptorSizeInBits(
        pyHeader.asDBRowDescriptor(), byteBuffer) / Byte.SIZE);

    final byte[] uncompressed = zerouncompress(bytes, uncompressedSize);

    final ByteBuffer unpackedBuffer = ByteBuffer.wrap(uncompressed);
    unpackedBuffer.order(ByteOrder.LITTLE_ENDIAN);

    final MarshalBuffer uncompressedByteBuffer = new MarshalBufferImpl(
        unpackedBuffer);

    final PyFactory.PyDBRowBuilder builder = getFactory().getPyDBRowBuilder();
    builder.header(pyHeader);

    final PyDBRow pyDBRow = builder.build();

    final PyDBRowDescriptor pyDBRowDescriptor = pyHeader.asDBRowDescriptor();

    final DBRowMap map = getDBRowDescriptorColumnsMap(pyDBRowDescriptor,
        byteBuffer);

    final Set<Entry<DBRowColumnTypeSize, List<DBRowPair>>> set = map.entrySet();

    for (final Entry<DBRowColumnTypeSize, List<DBRowPair>> entry : set)
    {
      for (final DBRowPair pair : entry.getValue())
      {
        final String rowKey = pair.getSecond();

        final DBRowColumnType rowType = pair.getFirst();

        if (rowType.typeSize().sizeInBits() == 0)
        {
          pyDBRow.getDict().put(
              getFactory().getPyStringBuilder().value(rowKey).build(),
              read(byteBuffer));
        }
        else
        {
          final PyBase value = rowType.read(uncompressedByteBuffer);
          pyDBRow.getDict().put(
              getFactory().getPyStringBuilder().value(rowKey).build(), value);
        }
      }
    }

    return (pyDBRow);
  }

  /**
   * Returns <tt>PyBase</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyBase</tt>
   */
  protected static PyBase readReference(final MarshalBuffer byteBuffer)
  {
    final int idx = length(byteBuffer);
    return (byteBuffer.getShared(idx));
  }

  /**
   * Returns <tt>PyShort</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyShort</tt>
   */
  protected static PyShort readShort(final MarshalBuffer byteBuffer)
  {
    final PyFactory.PyShortBuilder builder = getFactory().getPyShortBuilder();
    builder.value(byteBuffer.getShort());
    return (builder.build());
  }

  /**
   * Returns <tt>PyString</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyString</tt>
   */
  protected static PyString readString(final MarshalBuffer byteBuffer)
  {
    final int length = length(byteBuffer);
    byte[] bytes = new byte[length];
    byteBuffer.get(bytes);
    final PyFactory.PyStringBuilder builder = getFactory().getPyStringBuilder();
    builder.value(new String(bytes, Charset.forName("UTF-8")));
    return (builder.build());
  }

  /**
   * Returns <tt>PyString</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @param size
   *            size
   * @return <tt>PyString</tt>
   */
  protected static PyString readStringN(final MarshalBuffer byteBuffer,
      final int size)
  {
    byte[] bytes = new byte[size];
    byteBuffer.get(bytes);
    final PyFactory.PyStringBuilder builder = getFactory().getPyStringBuilder();
    builder.value(new String(bytes, Charset.forName("UTF-8")));
    return (builder.build());
  }

  /**
   * Returns <tt>PyString</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyString</tt>
   */
  protected static PyString readStringOne(final MarshalBuffer byteBuffer)
  {
    return (readStringN(byteBuffer, 1));
  }

  /**
   * Returns <tt>PyString</tt>.
   * 
   * @param buffer
   *            byteBuffer
   * @return <tt>PyString</tt>
   */
  protected static PyString readStringReference(final MarshalBuffer buffer)
  {
    final int idx = length(buffer);
    final PyFactory.PyStringBuilder builder = getFactory().getPyStringBuilder();
    builder.value(Strings.get(idx));
    return (builder.build());
  }

  /**
   * Returns <tt>PyBool</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyBool</tt>
   */
  protected static PyBool readTrue(final MarshalBuffer byteBuffer)
  {
    return (getFactory().getPyBoolTrue());
  }

  /**
   * Returns <tt>PyTuple</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyTuple</tt>
   */
  protected static PyTuple readTuple(final MarshalBuffer byteBuffer)
  {
    return (readTupleN(byteBuffer, length(byteBuffer)));
  }

  /**
   * Returns <tt>PyTuple</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @param size
   *            size
   * @return <tt>PyTuple</tt>
   */
  protected static PyTuple readTupleN(final MarshalBuffer byteBuffer,
      final int size)
  {
    final PyFactory.PyTupleBuilder builder = getFactory().getPyTupleBuilder(
        size);
    final PyTuple pyTuple = builder.build();
    for (int idx = 0; idx < size; idx++)
    {
      pyTuple.add(read(byteBuffer));
    }
    return (pyTuple);
  }

  /**
   * Returns <tt>PyTuple</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyTuple</tt>
   */
  protected static PyTuple readTupleOne(final MarshalBuffer byteBuffer)
  {
    return (readTupleN(byteBuffer, 1));
  }

  /**
   * Returns <tt>PyTuple</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyTuple</tt>
   */
  protected static PyTuple readTupleTwo(final MarshalBuffer byteBuffer)
  {
    return (readTupleN(byteBuffer, 2));
  }

  /**
   * Returns <tt>PyString</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyString</tt>
   */
  protected static PyString readUnicode(final MarshalBuffer byteBuffer)
  {
    return (readStringN(byteBuffer, length(byteBuffer) * 2));
  }

  /**
   * Returns <tt>PyString</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyString</tt>
   */
  protected static PyString readUnicodeEmpty(final MarshalBuffer byteBuffer)
  {
    return (getFactory().getPyString());
  }

  /**
   * Returns <tt>PyString</tt>.
   * 
   * @param byteBuffer
   *            byteBuffer
   * @return <tt>PyString</tt>
   */
  protected static PyString readUnicodeOne(final MarshalBuffer byteBuffer)
  {
    return (readStringN(byteBuffer, 2));
  }

  /**
   * @param fACTORY
   *            factory
   */
  public static final void setFACTORY(final PyFactory fACTORY)
  {
    mFACTORY = fACTORY;
  }

  /**
   * Zero uncompress bytes buffer.
   * 
   * @param bytes
   *            bytes
   * @param size
   *            size
   * @return uncompressed bytes buffer
   */
  private static byte[] zerouncompress(final byte[] bytes, final int size)
  {

    final byte[] out = new byte[size + Byte.SIZE * 2];
    int outpos = 0;
    byte current = 0;
    int len = 0;
    int pos = 0;
    int three = Byte.SIZE / 2 - 1;

    for (int loop = 0; loop < out.length; loop++)
    {
      out[loop] = 0;
    }

    while (pos < bytes.length)
    {

      current = bytes[pos++];

      final BitSet bitSet = new BitSet(8);
      for (int i = 0; i < Byte.SIZE; i++)
      {
        if ((current & (1 << i)) > 0)
        {
          bitSet.set(i);
        }
      }

      if (bitSet.get(three))
      {
        len = fromBitSet(bitSet.get(0, three)) + 1;
        for (int i = 0; i < len; i++)
        {
          out[outpos++] = 0;
        }
      }
      else
      {
        len = Byte.SIZE - fromBitSet(bitSet.get(0, three));
        for (int i = 0; i < len; i++)
        {
          out[outpos++] = bytes[pos++];
        }
      }

      if (bitSet.get(Byte.SIZE - 1))
      {
        len = fromBitSet(bitSet.get(Byte.SIZE / 2, Byte.SIZE - 1)) + 1;
        for (int i = 0; i < len; i++)
        {
          out[outpos++] = 0;
        }
      }
      else
      {
        len = Byte.SIZE - fromBitSet(bitSet.get(Byte.SIZE / 2, Byte.SIZE - 1));
        for (int i = 0; (i < len) && (pos < bytes.length); i++)
        {
          out[outpos++] = bytes[pos++];
        }
      }
    }

    return out;
  }

  @Override
  public final PyBase read(final InputStream aStream) throws IOException,
      ParseException
  {
    final ByteArrayOutputStream cache = new ByteArrayOutputStream();

    int bytes = 0;
    final byte[] byteBuffer = new byte[BUFFER_SIZE];

    while ((bytes = aStream.read(byteBuffer)) != -1)
    {
      cache.write(byteBuffer, 0, bytes);
    }

    final ByteBuffer buffer = ByteBuffer.wrap(cache.toByteArray());

    buffer.order(ByteOrder.LITTLE_ENDIAN);

    try
    {
      return read(buffer);
    }
    catch (final IllegalStateException e)
    {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }

  @Override
  public final PyBase read(final Path path) throws IOException
  {
    ByteBuffer buffer = null;

    try (FileChannel channel = FileChannel.open(path))
    {
      final long fileSize = Files.size(path);

      buffer = ByteBuffer.allocate((int) fileSize);

      channel.read(buffer, 0);
    }

    buffer.order(ByteOrder.LITTLE_ENDIAN);
    buffer.flip();

    try
    {
      return read(buffer);
    }
    catch (final IllegalStateException e)
    {
      throw new IllegalStateException(path.toString() + " " + e.getMessage(),
          e);
    }
  }

  /**
   * @param aBuffer buffer
   * @return object
   *
   * @since 0.0.1
   */
  private PyBase read(final ByteBuffer aBuffer)
  {
    final MarshalBuffer bufferReader = new MarshalBufferImpl(aBuffer, 0);

    return (load(bufferReader));
  }
}

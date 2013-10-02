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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jevetools.unmarshal.api.IllegalOpCodeException;
import com.jevetools.unmarshal.python.api.PyBase;
import com.jevetools.unmarshal.python.api.PyDBRowDescriptor;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public final class MarshalBufferImpl
    implements MarshalBuffer
{
  /**
     */
  private static final int START_INDEX = 5;

  /**
     */
  private static final int STREAM_IDENTIFIER = 0x7e;

  /**
     */
  private static final int MAX_BYTE_HEX = 0xFF;

  /**
     */
  private final transient ByteBuffer mByteBuffer;

  /**
     */
  private transient ByteBuffer mSharedByteBuffer;

  /**
     */
  private transient Map<Integer, PyBase> mSharedPyBase;

  /**
     */
  private final transient int mParentPosition;

  /**
     */
  private transient byte mBoolContainer;

  /**
     */
  private transient byte mBoolBitPosition;

  /**
     */
  private final transient ConcurrentHashMap<PyDBRowDescriptor, DBRowMapPair> 
    map = new ConcurrentHashMap<>();

  /**
   * Copyright (c) 2013, jEVETools.
   * 
   * All rights reserved.
   * 
   * @version 0.0.1
   * @since 0.0.1
   */
  static class DBRowPair
      extends Pair<DBRowColumnType, String>
  {
    /**
     * @param first
     *            first
     * @param second
     *            second
     */
    public DBRowPair(final DBRowColumnType first, final String second)
    {
      super(first, second);
    }
  };

  /**
   * Copyright (c) 2013, jEVETools.
   * 
   * All rights reserved.
   * 
   * @version 0.0.1
   * @since 0.0.1
   */
  static class DBRowMap
      extends LinkedHashMap<DBRowColumnTypeSize, List<DBRowPair>>
  {
    /**
     */
    private static final long serialVersionUID = 1051423871980969682L;
  };

  /**
   * Copyright (c) 2013, jEVETools.
   * 
   * All rights reserved.
   * 
   * @version 0.0.1
   * @since 0.0.1
   */
  static class DBRowMapPair
      extends Pair<Short, DBRowMap>
  {
    /**
     * @param first
     *            first
     * @param second
     *            second
     */
    public DBRowMapPair(final Short first, final DBRowMap second)
    {
      super(first, second);
    }
  };

  /**
   * @param byteBuffer
   *            byte buffer
   */
  public MarshalBufferImpl(final ByteBuffer byteBuffer)
  {
    this(byteBuffer, 0);
  }

  /**
   * @param byteBuffer
   *            byte buffer
   * @param position
   *            parent position
   */
  public MarshalBufferImpl(final ByteBuffer byteBuffer, final int position)
  {
    mByteBuffer = byteBuffer;
    mParentPosition = position;
  }

  @Override
  public boolean containsKey(final Object key)
  {
    return map.containsKey(key);
  }

  @Override
  public byte get()
  {
    return mByteBuffer.get();
  }

  @Override
  public ByteBuffer get(final byte[] dst)
  {
    return mByteBuffer.get(dst);
  }

  @Override
  public DBRowMapPair get(final Object key)
  {
    return map.get(key);
  }

  @Override
  public boolean getBoolean() // NOPMD
  {
    if (mBoolBitPosition == 0 || mBoolBitPosition == Byte.SIZE)
    {
      mBoolBitPosition = 0;
      mBoolContainer = get();
    }

    if ((mBoolContainer >> mBoolBitPosition++ & 0x01) != 0) // NOPMD
    {
      return true; // NOPMD
    }

    return false;
  }

  @Override
  public double getDouble()
  {
    return mByteBuffer.getDouble();
  }

  @Override
  public int getInt()
  {
    return mByteBuffer.getInt();
  }

  @Override
  public long getLong()
  {
    return mByteBuffer.getLong();
  }

  @Override
  public int getNextSharedIndex()
  {
    return mSharedByteBuffer.getInt();
  }

  @Override
  public PyBase getShared(final int index)
  {
    if (mSharedPyBase.containsKey(Integer.valueOf(index)))
    {
      return mSharedPyBase.get(Integer.valueOf(index));
    }
    throw new IllegalStateException("Invalid index: " + index);
  }

  @Override
  public short getShort() // NOPMD
  {
    return mByteBuffer.getShort();
  }

  @Override
  public void initialize() throws IllegalOpCodeException
  {
    final byte byteValue = get();

    final int intValue = byteValue & MAX_BYTE_HEX;

    if (intValue != STREAM_IDENTIFIER)
    {
      throw new IllegalOpCodeException(byteValue, String.format(
          "Illegal Op Code - offset: %08d opcode: 0x%02x", 1, byteValue));
    }

    final int sharedSize = getInt();

    mSharedPyBase = new HashMap<>(sharedSize);
    mByteBuffer.position(0);
    mByteBuffer.position(mByteBuffer.capacity() - sharedSize * 2 * 2);
    mSharedByteBuffer = mByteBuffer.slice();
    mSharedByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    mByteBuffer.position(START_INDEX);
    mBoolBitPosition = 0;
    mBoolContainer = 0;
  }

  @Override
  public int parentPosition()
  {
    return mParentPosition;
  }

  @Override
  public byte peekByte()
  {
    final byte temp = mByteBuffer.get();
    mByteBuffer.position(mByteBuffer.position() - 1);
    return temp;
  }

  @Override
  public int position()
  {
    return mByteBuffer.position();
  }

  @Override
  public boolean processed()
  {
    if (position() != mByteBuffer.capacity() - mSharedByteBuffer.capacity())
    {
      return false;
    }

    return true;
  }

  @Override
  public DBRowMapPair put(final PyDBRowDescriptor key, final DBRowMapPair value)
  {
    return map.put(key, value);
  }

  @Override
  public byte[] readBytes(final int size)
  {
    final byte[] bytes = new byte[size];
    mByteBuffer.get(bytes);
    return bytes;
  }

  @Override
  public PyBase setShared(final int index, final PyBase pyBase)
  {
    if (!mSharedPyBase.containsKey(Integer.valueOf(index)))
    {
      return mSharedPyBase.put(Integer.valueOf(index), pyBase);
    }
    throw new IllegalStateException("Index already set: " + index);
  }
}

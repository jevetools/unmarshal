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

import com.jevetools.unmarshal.api.IllegalOpCodeException;
import com.jevetools.unmarshal.api.impl.MarshalBufferImpl.DBRowMapPair;
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
public interface MarshalBuffer
{
  /**
   * @param key
   *            key
   * @return DBRowColumnTypeReader
   * @see java.util.Map#containsKey(java.lang.Object)
   */
  boolean containsKey(Object key);

  /**
   * @return value
   */
  byte get();

  /**
   * @param dst
   *            dst
   * @return value
   */
  ByteBuffer get(byte[] dst);

  /**
   * @param key
   *            key
   * @return map
   * @see java.util.Map#get(java.lang.Object)
   */
  DBRowMapPair get(Object key);

  /**
   * @return value
   */
  boolean getBoolean(); // NOPMD

  /**
   * @return value
   */
  double getDouble();

  /**
   * @return value
   */
  int getInt();

  /**
   * @return value
   */
  long getLong();

  /**
   * @return value
   */
  int getNextSharedIndex();

  /**
   * @param index
   *            index
   * @return value
   */
  PyBase getShared(int index);

  /**
   * @return value
   */
  short getShort(); // NOPMD

  /**
   * @throws IllegalOpCodeException
   *             on wrong stream format
   */
  void initialize() throws IllegalOpCodeException;

  /**
   * @return value
   */
  int parentPosition();

  /**
   * @return value
   */
  byte peekByte();

  /**
   * @return value
   */
  int position();

  /**
   * @return value
   */
  boolean processed();

  /**
   * @param key
   *            key
   * @param value
   *            value
   * @return value
   * @see java.util.Map#put(java.lang.Object, java.lang.Object)
   */
  DBRowMapPair put(PyDBRowDescriptor key, DBRowMapPair value);

  /**
   * @param size
   *            size
   * @return value
   */
  byte[] readBytes(int size);

  /**
   * @param index
   *            index
   * @param pyBase
   *            pyBase
   * @return value
   */
  PyBase setShared(int index, PyBase pyBase);
}

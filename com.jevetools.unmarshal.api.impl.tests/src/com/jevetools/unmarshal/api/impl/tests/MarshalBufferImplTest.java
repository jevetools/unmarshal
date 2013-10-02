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
package com.jevetools.unmarshal.api.impl.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.junit.Test;

import com.jevetools.unmarshal.api.IllegalOpCodeException;
import com.jevetools.unmarshal.api.impl.MarshalBufferImpl;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public class MarshalBufferImplTest
{

  /**
   */
  @Test
  public void testGet()
  {
    final byte[] bytes = new byte[] { 1, 2, 3, 4 };

    final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

    final MarshalBufferImpl testSubject = new MarshalBufferImpl(byteBuffer);

    assertThat(testSubject.get(), equalTo((byte) 1));
    assertThat(testSubject.get(), equalTo((byte) 2));
    assertThat(testSubject.get(), equalTo((byte) 3));
    assertThat(testSubject.get(), equalTo((byte) 4));
  }

  /**
   */
  @Test
  public void testGetByteArray()
  {
    final byte[] bytes = new byte[] { 1, 2, 3, 4 };

    final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

    final MarshalBufferImpl testSubject = new MarshalBufferImpl(byteBuffer);

    final byte[] out = new byte[2];

    testSubject.get(out);

    assertThat(out[0], equalTo((byte) 1));
    assertThat(out[1], equalTo((byte) 2));
    assertThat(testSubject.get(), equalTo((byte) 3));
    assertThat(testSubject.get(), equalTo((byte) 4));
  }

  /**
   */
  @Test
  public void testGetBoolean()
  {
    final byte[] bytes = new byte[] { 85, -86 };

    final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

    final MarshalBufferImpl testSubject = new MarshalBufferImpl(byteBuffer);

    assertThat(testSubject.getBoolean(), equalTo(true));
    assertThat(testSubject.position(), equalTo(1));
    assertThat(testSubject.getBoolean(), equalTo(false));
    assertThat(testSubject.getBoolean(), equalTo(true));
    assertThat(testSubject.getBoolean(), equalTo(false));
    assertThat(testSubject.getBoolean(), equalTo(true));
    assertThat(testSubject.getBoolean(), equalTo(false));
    assertThat(testSubject.getBoolean(), equalTo(true));
    assertThat(testSubject.getBoolean(), equalTo(false));
    assertThat(testSubject.position(), equalTo(1));

    assertThat(testSubject.parentPosition(), equalTo(0));

    assertThat(testSubject.getBoolean(), equalTo(false));
    assertThat(testSubject.position(), equalTo(2));
    assertThat(testSubject.getBoolean(), equalTo(true));
    assertThat(testSubject.getBoolean(), equalTo(false));
    assertThat(testSubject.getBoolean(), equalTo(true));
    assertThat(testSubject.getBoolean(), equalTo(false));
    assertThat(testSubject.getBoolean(), equalTo(true));
    assertThat(testSubject.getBoolean(), equalTo(false));
    assertThat(testSubject.getBoolean(), equalTo(true));
    assertThat(testSubject.position(), equalTo(2));
  }

  /**
   */
  @Test
  public void testGetShort()
  {
    final byte[] bytes = new byte[] { 1, 2 };

    final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

    final MarshalBufferImpl testSubject = new MarshalBufferImpl(byteBuffer);

    assertThat(testSubject.getShort(), equalTo((short) 513));
  }

  /**
   */
  @Test
  public void testGetInt()
  {
    final byte[] bytes = new byte[] { 1, 2, 3, 4 };

    final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

    final MarshalBufferImpl testSubject = new MarshalBufferImpl(byteBuffer);

    assertThat(testSubject.getInt(), equalTo(67305985));
  }

  /**
   */
  @Test
  public void testGetLong()
  {
    final byte[] bytes = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };

    final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

    final MarshalBufferImpl testSubject = new MarshalBufferImpl(byteBuffer);

    assertThat(testSubject.getLong(), equalTo(578437695752307201L));
  }

  /**
   */
  @Test
  public void testGetDouble()
  {
    final byte[] bytes = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };

    final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

    final MarshalBufferImpl testSubject = new MarshalBufferImpl(byteBuffer);

    assertThat(testSubject.getDouble(), equalTo(0.0));
  }

  /**
   * @throws IllegalOpCodeException
   *             on error
   */
  @Test
  public void test() throws IllegalOpCodeException
  {
    final byte[] bytes = new byte[] { 126, 1, 0, 0, 0, 1, 1, 0, 0 };

    final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

    final MarshalBufferImpl testSubject = new MarshalBufferImpl(byteBuffer);

    testSubject.initialize();

    assertThat(testSubject.getNextSharedIndex(), equalTo(257));

    assertThat(testSubject.processed(), equalTo(true));
  }

  /**
   * @throws IllegalOpCodeException
   *             on error
   */
  @Test
  public void testNotProcessed() throws IllegalOpCodeException
  {
    final byte[] bytes = new byte[] { 126, 1, 0, 0, 0, 0, 1, 1, 1, 0 };

    final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

    final MarshalBufferImpl testSubject = new MarshalBufferImpl(byteBuffer);

    testSubject.initialize();

    assertThat(testSubject.getNextSharedIndex(), equalTo(65793));

    assertThat(testSubject.processed(), equalTo(false));
  }

  /**
   * @throws IllegalOpCodeException
   *             on error
   */
  @Test(expected = IllegalOpCodeException.class)
  public void testException() throws IllegalOpCodeException
  {
    final byte[] bytes = new byte[] { 1, 1, 0, 0, 0, 1, 1, 0, 0 };

    final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

    final MarshalBufferImpl testSubject = new MarshalBufferImpl(byteBuffer);

    testSubject.initialize();

    assertThat(testSubject.getNextSharedIndex(), equalTo(257));
  }

  /**
   * @throws IllegalOpCodeException
   *             on error
   */
  @Test
  public void testReadBytes() throws IllegalOpCodeException
  {
    final byte[] bytes = new byte[] { 126, 1, 0, 0, 0, 2, 3, 1, 1, 1, 0 };

    final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

    final MarshalBufferImpl testSubject = new MarshalBufferImpl(byteBuffer);

    testSubject.initialize();

    assertThat(testSubject.getNextSharedIndex(), equalTo(65793));

    final byte[] out = testSubject.readBytes(2);

    assertThat(out.length, equalTo(2));

    assertThat(out[0], equalTo((byte) 2));
    assertThat(out[1], equalTo((byte) 3));

    assertThat(testSubject.processed(), equalTo(true));
  }

  /**
   */
  @Test
  public void testPeekByte()
  {
    final byte[] bytes = new byte[] { 1, 2 };

    final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

    final MarshalBufferImpl testSubject = new MarshalBufferImpl(byteBuffer);

    assertThat(testSubject.get(), equalTo((byte) 1));
    assertThat(testSubject.peekByte(), equalTo((byte) 2));
    assertThat(testSubject.get(), equalTo((byte) 2));
  }

  /**
   * @throws IllegalOpCodeException
   *             on error
   */
  @Test
  public void testShared() throws IllegalOpCodeException
  {
    final byte[] bytes = new byte[] { 126, 2, 0, 0, 0, 2, 0, 0, 0, 1, 0, 0, 0 };

    final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

    final MarshalBufferImpl testSubject = new MarshalBufferImpl(byteBuffer);

    testSubject.initialize();

    assertThat(testSubject.getNextSharedIndex(), equalTo(2));
    assertThat(testSubject.getNextSharedIndex(), equalTo(1));

    assertThat(testSubject.processed(), equalTo(true));
  }
}

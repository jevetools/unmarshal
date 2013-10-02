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
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.jevetools.unmarshal.api.ParseException;
import com.jevetools.unmarshal.api.service.UnmarshalService;
import com.jevetools.unmarshal.python.api.PyBase;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public class RecursiveImplTest
    extends AbstractTest
{
  /**
   */
  @Rule
  public ExpectedException mExpectedException = ExpectedException.none();

  /**
   * @throws Exception 
   */
  @Test
  public void testNone() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 1 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isNone(), equalTo(true));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testIdentifier() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 2, 2, 97, 98 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isString(), equalTo(true));

    assertThat(pyBase.asString().toString(), equalTo("ab"));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testInt() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 4, 16, 0, 16, 0 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isInt(), equalTo(true));

    assertThat(pyBase.asInt().value(), equalTo(1048592));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testIntOther() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 4, (byte) 0x94,
        (byte) 0x8b, 5, 0 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isInt(), equalTo(true));

    assertThat(pyBase.asInt().value(), equalTo(363412));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testShort() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 5, 16, 16 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isShort(), equalTo(true));

    assertThat(pyBase.asShort().value(), equalTo((short) 4112));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testByte() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 6, 16 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isByte(), equalTo(true));

    assertThat(pyBase.asByte().value(), equalTo((byte) 16));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testIntZero() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 8 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isInt(), equalTo(true));

    assertThat(pyBase.asInt().value(), equalTo(0));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testIntOne() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 9 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isInt(), equalTo(true));

    assertThat(pyBase.asInt().value(), equalTo(1));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testDouble() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x0a, 0, 0, 0, 0, 0, 0,
        0, 0 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isDouble(), equalTo(true));

    assertThat(pyBase.asDouble().value(), equalTo(0.0));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testDoubleZero() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x0b };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isDouble(), equalTo(true));

    assertThat(pyBase.asDouble().value(), equalTo(0.0));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testStringEmpty() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x0e };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isString(), equalTo(true));

    assertThat(pyBase.asString().toString(), equalTo(""));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testUnicodeEmpty() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x28 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isString(), equalTo(true));

    assertThat(pyBase.asString().toString(), equalTo(""));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testUnicodeOne() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x29, 97, 98 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isString(), equalTo(true));

    assertThat(pyBase.asString().toString(), equalTo("ab"));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testUTF8Buffer() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x2e, 2, 97, 98 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isString(), equalTo(true));

    assertThat(pyBase.asString().toString(), equalTo("ab"));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testStringOne() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x0f, 97 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isString(), equalTo(true));

    assertThat(pyBase.asString().toString(), equalTo("a"));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testUnicode() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x12, 1, 97, 98 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isString(), equalTo(true));

    assertThat(pyBase.asString().toString(), equalTo("ab"));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testBuffer() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x13, 1, 97 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isString(), equalTo(true));

    assertThat(pyBase.asString().toString(), equalTo("a"));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testStringRef() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x11, 1 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isString(), equalTo(true));

    assertThat(pyBase.asString().toString(), equalTo("*corpid"));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testTrue() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x1f };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isBool(), equalTo(true));

    assertThat(pyBase.asBool().value(), equalTo(true));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testFalse() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x20 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isBool(), equalTo(true));

    assertThat(pyBase.asBool().value(), equalTo(false));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testTupleVar() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x14, 2, 0x1f, 0x20 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isTuple(), equalTo(true));

    assertThat(pyBase.asTuple().size(), equalTo(2));

    assertThat(pyBase.asTuple().get(0).isBool(), equalTo(true));
    assertThat(pyBase.asTuple().get(1).isBool(), equalTo(true));

    assertThat(pyBase.asTuple().get(0).asBool().value(), equalTo(true));
    assertThat(pyBase.asTuple().get(1).asBool().value(), equalTo(false));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testTupleOne() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x25, 0x1f };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isTuple(), equalTo(true));

    assertThat(pyBase.asTuple().size(), equalTo(1));

    assertThat(pyBase.asTuple().get(0).isBool(), equalTo(true));

    assertThat(pyBase.asTuple().get(0).asBool().value(), equalTo(true));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testTupleTwo() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x2c, 0x1f, 0x20 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isTuple(), equalTo(true));

    assertThat(pyBase.asTuple().size(), equalTo(2));

    assertThat(pyBase.asTuple().get(0).isBool(), equalTo(true));
    assertThat(pyBase.asTuple().get(1).isBool(), equalTo(true));

    assertThat(pyBase.asTuple().get(0).asBool().value(), equalTo(true));
    assertThat(pyBase.asTuple().get(1).asBool().value(), equalTo(false));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testListVar() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x15, 2, 0x1f, 0x20 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isList(), equalTo(true));

    assertThat(pyBase.asList().size(), equalTo(2));

    assertThat(pyBase.asList().get(0).isBool(), equalTo(true));
    assertThat(pyBase.asList().get(1).isBool(), equalTo(true));

    assertThat(pyBase.asList().get(0).asBool().value(), equalTo(true));
    assertThat(pyBase.asList().get(1).asBool().value(), equalTo(false));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testListEmpty() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x26 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isList(), equalTo(true));

    assertThat(pyBase.asList().size(), equalTo(0));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testListOne() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x27, 0x20 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isList(), equalTo(true));

    assertThat(pyBase.asList().size(), equalTo(1));

    assertThat(pyBase.asList().get(0).isBool(), equalTo(true));

    assertThat(pyBase.asList().get(0).asBool().value(), equalTo(false));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testEmbedded() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x2b, 6, 0x7e, 0, 0, 0,
        0, 1 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isNone(), equalTo(true));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testInvalidEmbedded() throws Exception
  {
    mExpectedException.expect(IllegalStateException.class);
    mExpectedException.expectMessage("subbuffer: ");

    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x2b, 6, 0x7e, 0, 0, 0,
        0, 0 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isNone(), equalTo(true));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testReferenced() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 1, 0, 0, 0, 0x2c, 0x41, 0x1b, 1, 1,
        0, 0, 0 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isTuple(), equalTo(true));
    assertThat(pyBase.asTuple().size(), equalTo(2));
    assertThat(pyBase.asTuple().get(0).isNone(), equalTo(true));
    assertThat(pyBase.asTuple().get(1).isNone(), equalTo(true));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testInvalidReferenced() throws Exception
  {
    mExpectedException.expect(IllegalStateException.class);
    mExpectedException.expectMessage("Invalid index: 2");

    final byte[] bytes = new byte[] { 0x7e, 1, 0, 0, 0, 0x2c, 0x41, 0x1b, 2, 1,
        0, 0, 0 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isTuple(), equalTo(true));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testInvalidShared() throws Exception
  {
    mExpectedException.expect(IllegalStateException.class);
    mExpectedException
        .expectMessage("Buffer Underflow - offset: 00000007 opcode: 0x01");

    final byte[] bytes = new byte[] { 0x7e, 1, 0, 0, 0, 0x2c, 0x41, 0x41, 1, 0,
        0, 0 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isTuple(), equalTo(true));
    assertThat(pyBase.asTuple().size(), equalTo(2));
    assertThat(pyBase.asTuple().get(0).isNone(), equalTo(true));
    assertThat(pyBase.asTuple().get(1).isNone(), equalTo(true));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testInvalidSharedIndex() throws Exception
  {
    mExpectedException.expect(IllegalStateException.class);
    mExpectedException.expectMessage("Index already set: 1");

    final byte[] bytes = new byte[] { 0x7e, 2, 0, 0, 0, 0x2c, 0x41, 0x41, 1, 0,
        0, 0, 1, 0, 0, 0 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isTuple(), equalTo(true));
    assertThat(pyBase.asTuple().size(), equalTo(2));
    assertThat(pyBase.asTuple().get(0).isNone(), equalTo(true));
    assertThat(pyBase.asTuple().get(1).isNone(), equalTo(true));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testDict() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x16, 1, 0x13, 1, 97,
        0x13, 1, 98 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isDict(), equalTo(true));
    assertThat(pyBase.asDict().size(), equalTo(1));

    final Set<Entry<PyBase, PyBase>> entrySet = pyBase.asDict().entrySet();

    final Entry<PyBase, PyBase> next = entrySet.iterator().next();

    assertThat(next.getKey().isString(), equalTo(true));
    assertThat(next.getValue().isString(), equalTo(true));

    assertThat(next.getKey().asString().value(), equalTo("b"));
    assertThat(next.getValue().asString().value(), equalTo("a"));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testObject() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x17, 0x13, 1, 97,
        0x13, 1, 98 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isObject(), equalTo(true));

    assertThat(pyBase.asObject().getHeader().isString(), equalTo(true));
    assertThat(pyBase.asObject().getBody().isString(), equalTo(true));

    assertThat(pyBase.asObject().getHeader().asString().value(), equalTo("a"));
    assertThat(pyBase.asObject().getBody().asString().value(), equalTo("b"));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testObjectEx() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x23, 0x13, 1, 97,
        0x2d, 0x2d };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isObjectEx(), equalTo(true));

    assertThat(pyBase.asObjectEx().getHeader().isString(), equalTo(true));

    assertThat(pyBase.asObjectEx().getHeader().asString().value(), equalTo("a"));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testObjectExList() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x23, 0x13, 1, 97,
        0x1f, 0x20, 0x2d, 0x2d };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isObjectEx(), equalTo(true));

    assertThat(pyBase.asObjectEx().getHeader().isString(), equalTo(true));

    assertThat(pyBase.asObjectEx().getHeader().asString().value(), equalTo("a"));

    assertThat(pyBase.asObjectEx().getList().size(), equalTo(2));

    assertThat(pyBase.asObjectEx().getList().get(0).isBool(), equalTo(true));
    assertThat(pyBase.asObjectEx().getList().get(1).isBool(), equalTo(true));

    assertThat(pyBase.asObjectEx().getList().get(0).asBool().value(),
        equalTo(true));
    assertThat(pyBase.asObjectEx().getList().get(1).asBool().value(),
        equalTo(false));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testObjectExDict() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x23, 0x13, 1, 97,
        0x2d, 0x1f, 0x20, 0x2d };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isObjectEx(), equalTo(true));

    assertThat(pyBase.asObjectEx().getHeader().isString(), equalTo(true));

    assertThat(pyBase.asObjectEx().getHeader().asString().value(), equalTo("a"));

    assertThat(pyBase.asObjectEx().getList().size(), equalTo(0));

    assertThat(pyBase.asObjectEx().getDict().size(), equalTo(1));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testObjectExClassName() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x23, 0x2c, 0x13, 1,
        97, 0x13, 1, 98, 0x2d, 0x2d };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isObjectEx(), equalTo(true));

    assertThat(pyBase.asObjectEx().getHeader().isTuple(), equalTo(true));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testObjectExClassNameTuple() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x23, 0x25, 0x25, 0x13,
        1, 97, 0x2d, 0x2d };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isObjectEx(), equalTo(true));

    assertThat(pyBase.asObjectEx().getHeader().isTuple(), equalTo(true));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testObjectExClassNameTupleSecond() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x23, 0x25, 0x2c, 0x13,
        1, 97, 0x13, 1, 98, 0x2d, 0x2d };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isObjectEx(), equalTo(true));

    assertThat(pyBase.asObjectEx().getHeader().isTuple(), equalTo(true));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testObjectExClassNameTupleInvalid() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x23, 0x25, 0x25, 1,
        0x2d, 0x2d };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isObjectEx(), equalTo(true));

    assertThat(pyBase.asObjectEx().getHeader().isTuple(), equalTo(true));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testObjectExClassNameInvalid() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x23, 0x2c, 1, 1, 0x2d,
        0x2d };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isObjectEx(), equalTo(true));

    assertThat(pyBase.asObjectEx().getHeader().isTuple(), equalTo(true));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testObjectIntVarShort() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x2f, 2, 16, 16 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isShort(), equalTo(true));

    assertThat(pyBase.asShort().value(), equalTo((short) 4112));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testObjectIntVarZero() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x2f, 0 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isLong(), equalTo(true));

    assertThat(pyBase.asLong().value(), equalTo(0L));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testObjectIntVarInt() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x2f, 4, 16, 16, 16, 0 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isInt(), equalTo(true));

    assertThat(pyBase.asInt().value(), equalTo(1052688));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testObjectIntVarLong() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x2f, 8, 16, 16, 16, 0,
        16, 16, 16, 0 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isLong(), equalTo(true));

    assertThat(pyBase.asLong().value(), equalTo(4521260533944336L));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testObjectIntVarOne() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x2f, 1, 16 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isLong(), equalTo(true));

    assertThat(pyBase.asLong().value(), equalTo(16L));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testObjectIntVarThree() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x2f, 3, 16, 16, 16 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isLong(), equalTo(true));

    assertThat(pyBase.asLong().value(), equalTo(1052688L));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testObjectIntVarFive() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x2f, 5, 16, 16, 16,
        16, 16 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isLong(), equalTo(true));

    assertThat(pyBase.asLong().value(), equalTo(68988964880L));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testObjectIntVarSix() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x2f, 6, 16, 16, 16,
        16, 16, 16 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isLong(), equalTo(true));

    assertThat(pyBase.asLong().value(), equalTo(17661175009296L));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testObjectIntVarSeven() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x2f, 7, 16, 16, 16,
        16, 16, 16, 16 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isLong(), equalTo(true));

    assertThat(pyBase.asLong().value(), equalTo(4521260802379792L));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testNotProcessed() throws Exception
  {
    mExpectedException.expect(IllegalStateException.class);
    mExpectedException
        .expectMessage("Unprocessed bytes left in stream - offset: 00000006");

    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 1, 1 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isLong(), equalTo(true));

    assertThat(pyBase.asLong().value(), equalTo(4521260802379792L));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testInvalidStream() throws Exception
  {
    mExpectedException.expect(IllegalStateException.class);
    mExpectedException
        .expectMessage("Illegal Op Code - offset: 00000001 opcode: 0x7f");

    final byte[] bytes = new byte[] { 0x7f };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isLong(), equalTo(true));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testPath() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 1 };

    final Path path = Files.createTempFile("bca", "abc");

    Files.write(path, bytes);

    final PyBase pyBase = readStream(path);

    Files.deleteIfExists(path);

    assertThat(pyBase.isNone(), equalTo(true));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testInvalidPath() throws Exception
  {
    mExpectedException.expect(IllegalStateException.class);
    mExpectedException
        .expectMessage("Illegal Op Code - offset: 00000001 opcode: 0x7f");

    final byte[] bytes = new byte[] { 0x7f };

    final Path path = Files.createTempFile("abc", "bca");

    Files.write(path, bytes);

    final PyBase pyBase = readStream(path);

    Files.deleteIfExists(path);

    assertThat(pyBase.isLong(), equalTo(true));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testInvalidOpCode() throws Exception
  {
    mExpectedException.expect(IllegalStateException.class);
    mExpectedException
        .expectMessage("Illegal Op Code - offset: 00000005 opcode: 0x00");

    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0 };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isLong(), equalTo(true));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testBufferUnderflow() throws Exception
  {
    mExpectedException.expect(IllegalStateException.class);
    mExpectedException
        .expectMessage("Buffer Underflow - offset: 00000005 opcode: 0x00");

    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isLong(), equalTo(true));
  }

  /**
   * @throws Exception 
   */
  @Test
  public void testObjectShared() throws Exception
  {
    final byte[] bytes = new byte[] { 0x7e, 0, 0, 0, 0, 0x22, 0x13, 1, 97,
        0x2d, 0x2d };

    final PyBase pyBase = readStream(bytes);

    assertThat(pyBase.isObjectEx(), equalTo(true));

    assertThat(pyBase.asObjectEx().getHeader().isString(), equalTo(true));

    assertThat(pyBase.asObjectEx().getHeader().asString().value(), equalTo("a"));
  }

  /**
   * @param bytes
   * @return
   * @throws Exception
   * @throws ParseException
   * @throws IOException
   *
   * @since 0.0.1
   */
  private PyBase readStream(final byte[] bytes) throws Exception,
      ParseException, IOException
  {
    final InputStream stream = new ByteArrayInputStream(bytes);

    final UnmarshalService testSubject = getTestService();

    final PyBase pyBase = testSubject.read(stream);
    return pyBase;
  }

  /**
   * @param bytes
   * @return
   * @throws Exception
   * @throws ParseException
   * @throws IOException
   *
   * @since 0.0.1
   */
  private PyBase readStream(final Path aPath) throws Exception, ParseException,
      IOException
  {
    final UnmarshalService testSubject = getTestService();

    final PyBase pyBase = testSubject.read(aPath);
    return pyBase;
  }

  /**
   * @throws Exception
   *             on error
   */
  private UnmarshalService getTestService() throws Exception // NOPMD
  {
    final Bundle testSubject = getTestSubject();

    assertThat(testSubject, not(nullValue()));

    final BundleContext bundleContext = testSubject.getBundleContext();

    assertThat(testSubject, not(nullValue()));

    final ServiceReference<UnmarshalService> reference = bundleContext
        .getServiceReference(UnmarshalService.class);

    assertThat(reference, not(nullValue()));

    final UnmarshalService service = bundleContext.getService(reference);

    assertThat(service, not(nullValue()));

    return service;
  }
}

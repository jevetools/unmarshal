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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.jevetools.unmarshal.api.impl.Strings;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public final class StringsTest
{
  /**
   * @throws Exception
   *             on error
   */
  @Test
  public void test() throws Exception // NOPMD
  {
    final String testSubject = Strings.get(0);

    assertThat(testSubject, not(nullValue()));
    assertThat(testSubject, equalTo("invalid strings index - null"));
  }

  /**
   * @throws Exception
   *             on error
   */
  @Test(expected = InvocationTargetException.class)
  public void testConstructor() throws Exception // NOPMD
  {
    final Constructor<?> constructor = Strings.class.getDeclaredConstructor();

    constructor.setAccessible(true);

    constructor.newInstance();
  }

  /**
   * @throws Exception
   *             on error
   */
  @Test(expected = IndexOutOfBoundsException.class)
  public void testGetNegative() throws Exception // NOPMD
  {
    Strings.get(-1);
  }

  /**
   * @throws Exception
   *             on error
   */
  @Test(expected = IndexOutOfBoundsException.class)
  public void testGet() throws Exception // NOPMD
  {
    Strings.get(Long.SIZE * 2 * 2);
  }
}

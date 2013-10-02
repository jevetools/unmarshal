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
package com.jevetools.unmarshal.api.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.jevetools.unmarshal.api.IllegalSizeException;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public final class IllegalSizeExceptionTest
{
  /**
   * Test message.
   * 
   * @since 0.0.1
   */
  private static final String MESSAGE = "message";

  /**
   * Test.
   * 
   * @throws IllegalSizeException
   *             on success
   */
  @Test(expected = IllegalSizeException.class)
  public void testIllegalSizeException() throws IllegalSizeException
  {
    final IllegalSizeException testSubject = new IllegalSizeException();

    assertThat(testSubject, not(nullValue()));

    throw testSubject;
  }

  /**
   * Test.
   * 
   * @throws IllegalSizeException
   *             on success
   */
  @Test(expected = IllegalSizeException.class)
  public void testIllegalSizeExceptionString() throws IllegalSizeException
  {
    final IllegalSizeException testSubject = new IllegalSizeException(MESSAGE);

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getMessage(), equalTo(MESSAGE));

    throw testSubject;
  }

  /**
   * Test.
   * 
   * @throws IllegalSizeException
   *             on success
   */
  @Test(expected = IllegalSizeException.class)
  public void testIllegalSizeExceptionStringThrowable()
      throws IllegalSizeException
  {
    final Throwable exception = new Exception();

    final IllegalSizeException testSubject = new IllegalSizeException(MESSAGE,
        exception);

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getMessage(), equalTo(MESSAGE));
    assertThat(testSubject.getCause(), equalTo(exception));

    throw testSubject;
  }

  /**
   * Test.
   * 
   * @throws IllegalSizeException
   *             on success
   */
  @Test(expected = IllegalSizeException.class)
  public void testIllegalSizeExceptionStringThrowableBooleanBoolean()
      throws IllegalSizeException
  {
    final Throwable exception = new Exception();

    final IllegalSizeException testSubject = new IllegalSizeException(MESSAGE,
        exception, true, true);

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getMessage(), equalTo(MESSAGE));
    assertThat(testSubject.getCause(), equalTo(exception));

    throw testSubject;
  }

  /**
   * Test.
   * 
   * @throws IllegalSizeException
   *             on success
   */
  @Test(expected = IllegalSizeException.class)
  public void testIllegalSizeExceptionThrowable() throws IllegalSizeException
  {
    final Throwable exception = new Exception();

    final IllegalSizeException testSubject = 
        new IllegalSizeException(exception);

    assertThat(testSubject, not(nullValue()));

    assertThat(testSubject.getCause(), equalTo(exception));

    throw testSubject;
  }

}

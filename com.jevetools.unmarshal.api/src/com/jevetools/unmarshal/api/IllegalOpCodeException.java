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
package com.jevetools.unmarshal.api;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
@SuppressWarnings("serial")
public class IllegalOpCodeException
    extends ParseException
{
  /**
   * opcode.
   * 
   * @since 0.0.1
   */
  private final byte mOpCode;

  /**
   * @param opCode
   *            opcode
   * @since 0.0.1
   */
  public IllegalOpCodeException(final byte opCode)
  {
    super();
    mOpCode = opCode;
  }

  /**
   * @param opCode
   *            opcode
   * @param message
   *            message
   * @since 0.0.1
   */
  public IllegalOpCodeException(final byte opCode, final String message)
  {
    super(message);
    mOpCode = opCode;
  }

  /**
   * @param opCode
   *            opcode
   * @param message
   *            message
   * @param cause
   *            cause
   * @since 0.0.1
   */
  public IllegalOpCodeException(final byte opCode, final String message,
      final Throwable cause)
  {
    super(message, cause);
    mOpCode = opCode;
  }

  /**
   * @param opCode
   *            opcode
   * @param cause
   *            cause
   * @since 0.0.1
   */
  public IllegalOpCodeException(final byte opCode, final Throwable cause)
  {
    super(cause);
    mOpCode = opCode;
  }

  /**
   * @return the opcode
   * @since 0.0.1
   */
  public final byte getOpCode()
  {
    return mOpCode;
  }
}

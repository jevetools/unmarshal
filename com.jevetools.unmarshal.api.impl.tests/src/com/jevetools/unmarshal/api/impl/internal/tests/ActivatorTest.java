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
package com.jevetools.unmarshal.api.impl.internal.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.jevetools.unmarshal.api.impl.tests.AbstractTest;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public final class ActivatorTest
    extends AbstractTest
{
  /**
   * @throws Exception
   *             on error
   */
  @Test
  public void testImports() throws Exception
  {
    final List<String> packages = new ArrayList<>(9);

    packages.add("org.osgi.framework");
    packages.add("org.osgi.framework.namespace");
    packages.add("org.osgi.framework.wiring");
    packages.add("org.osgi.resource");
    packages.add("com.jevetools.unmarshal.python.api");
    packages.add("com.jevetools.unmarshal.python.api.service");
    packages.add("com.jevetools.unmarshal.api");
    packages.add("com.jevetools.unmarshal.api.service");
    packages.add("org.osgi.service.log");

    checkImports(getTestSubject(), packages);
  }

  /**
   * @throws Exception
   *             on error
   */
  @Test
  public void testExports() throws Exception
  {
    final List<String> packages = new ArrayList<>(0);

    checkExports(getTestSubject(), packages);
  }
}

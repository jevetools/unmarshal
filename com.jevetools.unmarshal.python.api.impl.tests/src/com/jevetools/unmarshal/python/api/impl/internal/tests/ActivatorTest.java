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
package com.jevetools.unmarshal.python.api.impl.internal.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.jevetools.unmarshal.python.api.service.PythonFactoryService;

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
   * Seconds to wait for OSGi to fulfill dependencies.
   */
  private static final int OSGI_WAIT = 2;

  /**
   * Synchronization aid for OSGi dependencies fulfillment.
   */
  private static final CountDownLatch DEPENDENCYLATCH = new CountDownLatch(1);

  /**
   * Invoked before each run.
   */
  @BeforeClass
  public static void beforClass()
  {
    try
    {
      assertThat(DEPENDENCYLATCH.await(OSGI_WAIT, TimeUnit.SECONDS),
          equalTo(false));
    }
    catch (InterruptedException ex)
    {
      fail("OSGi dependencies unfulfilled");
    }
  }

  /**
   * Invoked before each test method.
   */
  @Before
  public void beforeMethod()
  {
    final Bundle bundle = FrameworkUtil.getBundle(this.getClass());

    assertThat(bundle, not(nullValue()));
  }

  /**
   * @throws InvalidSyntaxException
   *             on error
   */
  @Test
  public void testImports() throws InvalidSyntaxException
  {
    final List<String> packages = new ArrayList<>(2);

    packages.add("com.jevetools.unmarshal.python.api");
    packages.add("com.jevetools.unmarshal.python.api.service");

    final Bundle testSubject = FrameworkUtil.getBundle(this.getClass());

    assertThat(testSubject, not(nullValue()));

    checkImports(testSubject, packages);
  }

  /**
   */
  @Test
  public void testExports()
  {
    final List<String> packages = new ArrayList<>(0);

    final Bundle testSubject = FrameworkUtil.getBundle(this.getClass());

    assertThat(testSubject, not(nullValue()));

    checkExports(testSubject, packages);
  }

  /**
   */
  @Test
  public void testService()
  {
    final Bundle testSubject = FrameworkUtil.getBundle(this.getClass());

    final BundleContext context = testSubject.getBundleContext();

    final ServiceReference<PythonFactoryService> reference = context
        .getServiceReference(PythonFactoryService.class);

    assertThat(reference, not(nullValue()));

    final PythonFactoryService service = context.getService(reference);

    assertThat(service, not(nullValue()));
  }
}

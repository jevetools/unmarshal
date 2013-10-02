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
import static org.junit.Assert.fail;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.osgi.framework.Bundle;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleRequirement;
import org.osgi.framework.wiring.BundleRevision;
import org.osgi.framework.wiring.BundleWiring;
import org.osgi.resource.Namespace;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class AbstractTest
{
  /**
   * Tested {@link Bundle}.
   */
  private Bundle testSubject;

  /**
   * Seconds to wait for OSGi to fulfill dependencies.
   */
  private static final int OSGI_WAIT = 3;

  /**
   * Synchronization aid for OSGi dependencies fulfillment.
   */
  private static final CountDownLatch DEPENDENCYLATCH = new CountDownLatch(1);

  /**
   * Returns the {@link Bundle} to be tested.
   * 
   * @return {@link Bundle} to be tested
   */
  protected final Bundle getTestSubject()
  {
    return testSubject;
  }

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
  public final void beforeMethod()
  {

    final Bundle bundle = FrameworkUtil.getBundle(this.getClass());

    assertThat(bundle, not(nullValue()));

    testSubject = bundle;
  }

  /**
   * Invoked after each test method.
   */
  @After
  public final void afterMethod()
  {
    testSubject = null;
  }

  /**
   * Checks imported packages.
   * 
   * @param bundle
   *            {@link Bundle}
   * @param packages
   *            {@link List}
   * 
   * @throws Exception
   *             on error
   */
  protected final void checkImports(final Bundle bundle,
      final List<String> packages) throws Exception
  {

    final BundleWiring bundleWiring = bundle.adapt(BundleWiring.class);

    final List<BundleRequirement> requirements = bundleWiring
        .getRequirements(BundleRevision.PACKAGE_NAMESPACE);

    assertThat(packages.size(), equalTo(requirements.size()));

    for (final BundleRequirement requirement : requirements)
    {
      final String filter = ((String) requirement.getDirectives().get(
          Namespace.REQUIREMENT_FILTER_DIRECTIVE));
      final Filter packageFilter = FrameworkUtil.createFilter(filter);
      assertThat(matches(packageFilter, packages), equalTo(true));
    }
  }

  /**
   * Checks exported packages.
   * 
   * @param bundle
   *            {@link Bundle}
   * @param packages
   *            {@link List}
   */
  protected final void checkExports(final Bundle bundle,
      final List<String> packages)
  {

    final BundleWiring bundleWiring = bundle.adapt(BundleWiring.class);

    final List<BundleCapability> capabilities = bundleWiring
        .getCapabilities(BundleRevision.PACKAGE_NAMESPACE);

    assertThat(packages.size(), equalTo(capabilities.size()));

    for (final BundleCapability capability : capabilities)
    {
      final String name = ((String) capability.getAttributes().get(
          BundleRevision.PACKAGE_NAMESPACE));
      assertThat(packages.contains(name), equalTo(true));
    }
  }

  /**
   * Returns {@code true} if {@link Filter} matches one of the {@link List}s
   * packages otherwise {@code false}.
   * 
   * @param filter
   *            {@link Filter}
   * @param packages
   *            {@link List}
   * @return {@code true} if {@link Filter} matches one of the {@link List}s
   *         packages otherwise {@code false}
   */
  private boolean matches(final Filter filter, final List<String> packages)
  {

    for (final String name : packages)
    {
      final Dictionary<String, String> dictionary = new Hashtable<>(1);
      dictionary.put(BundleRevision.PACKAGE_NAMESPACE, name);

      if (filter.matchCase(dictionary))
      {
        return true;
      }
    }

    return false;
  }

}

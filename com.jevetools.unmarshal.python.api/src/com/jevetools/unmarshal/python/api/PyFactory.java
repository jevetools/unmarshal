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
package com.jevetools.unmarshal.python.api;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public interface PyFactory
{ //NOPMD
  /**
   * Builder interface.
   * 
   * @param <T>
   */
  interface PyBuilder<T>
  {
    /**
     * Returns builder.
     * 
     * @return builder for <T>
     */
    T build();
  }

  /**
   * {@link PyNone} builder.
   * 
   * @see {@link PyNone}
   * @see {@link PyBuilder}
   */
  interface PyNoneBuilder
      extends PyBuilder<PyNone>
  {
  }

  /**
   * {@link PyBool} builder.
   * 
   * @see {@link PyBool}
   * @see {@link PyBuilder}
   */
  interface PyBoolBuilder
      extends PyBuilder<PyBool>
  {
    /**
     * Sets {@code boolean} value.
     * 
     * @param aValue
     *            {@code boolean} value
     * 
     * @return {@link PyBoolBuilder}
     */
    PyBoolBuilder value(final boolean aValue);
  }

  /**
   * {@link PyByte} builder.
   * 
   * @see {@link PyByte}
   * @see {@link PyBuilder}
   */
  interface PyByteBuilder
      extends PyBuilder<PyByte>
  {
    /**
     * Sets {@code byte} value.
     * 
     * @param aValue
     *            {@code byte} value
     * 
     * @return {@link PyByteBuilder}
     */
    PyByteBuilder value(final byte aValue);
  }

  /**
   * {@link PyShort} builder.
   * 
   * @see {@link PyShort}
   * @see {@link PyBuilder}
   */
  interface PyShortBuilder
      extends PyBuilder<PyShort>
  {
    /**
     * Sets {@code short} value.
     * 
     * @param aValue
     *            {@code short} value
     * 
     * @return {@link PyShortBuilder}
     */
    PyShortBuilder value(final short aValue); //NOPMD
  }

  /**
   * {@link PyInt} builder.
   * 
   * @see {@link PyInt}
   * @see {@link PyBuilder}
   */
  interface PyIntBuilder
      extends PyBuilder<PyInt>
  {
    /**
     * Sets {@code int} value.
     * 
     * @param aValue
     *            {@code int} value
     * 
     * @return {@link PyIntBuilder}
     */
    PyIntBuilder value(final int aValue);
  }

  /**
   * {@link PyLong} builder.
   * 
   * @see {@link PyLong}
   * @see {@link PyBuilder}
   */
  interface PyLongBuilder
      extends PyBuilder<PyLong>
  {
    /**
     * Sets {@code long} value.
     * 
     * @param aValue
     *            {@code long} value
     * 
     * @return {@link PyLongBuilder}
     */
    PyLongBuilder value(final long aValue);
  }

  /**
   * {@link PyFloat} builder.
   * 
   * @see {@link PyFloat}
   * @see {@link PyBuilder}
   */
  interface PyFloatBuilder
      extends PyBuilder<PyFloat>
  {
    /**
     * Sets {@code float} value.
     * 
     * @param aValue
     *            {@code float} value
     * 
     * @return {@link PyFloatBuilder}
     */
    PyFloatBuilder value(final float aValue);
  }

  /**
   * {@link PyDouble} builder.
   * 
   * @see {@link PyDouble}
   * @see {@link PyBuilder}
   */
  interface PyDoubleBuilder
      extends PyBuilder<PyDouble>
  {
    /**
     * Sets {@code double} value.
     * 
     * @param aValue
     *            {@code double} value
     * 
     * @return {@link PyDoubleBuilder}
     */
    PyDoubleBuilder value(final double aValue);
  }

  /**
   * {@link PyString} builder.
   * 
   * @see {@link PyString}
   * @see {@link PyBuilder}
   */
  interface PyStringBuilder
      extends PyBuilder<PyString>
  {
    /**
     * Sets {@code String} value.
     * 
     * @param aValue
     *            {@code String} value
     * 
     * @return {@link PyStringBuilder}
     */
    PyStringBuilder value(final String aValue);
  }

  /**
   * {@link PyTuple} builder.
   * 
   * @see {@link PyTuple}
   * @see {@link PyBuilder}
   */
  interface PyTupleBuilder
      extends PyBuilder<PyTuple>
  {

  }

  /**
   * {@link PyList} builder.
   * 
   * @see {@link PyList}
   * @see {@link PyBuilder}
   */
  interface PyListBuilder
      extends PyBuilder<PyList>
  {

  }

  /**
   * {@link PyDict} builder.
   * 
   * @see {@link PyDict}
   * @see {@link PyBuilder}
   */
  interface PyDictBuilder
      extends PyBuilder<PyDict>
  {
  }

  /**
   * {@link PyObject} builder.
   * 
   * @see {@link PyObject}
   * @see {@link PyBuilder}
   */
  interface PyObjectBuilder
      extends PyBuilder<PyObject>
  {
    /**
     * Sets {@code PyObject} body.
     * 
     * @param aBody
     *            {@code PyObject} body
     * 
     * @return {@link PyObjectBuilder}
     */
    PyObjectBuilder body(final PyBase aBody);

    /**
     * Sets {@code PyObject} header.
     * 
     * @param aHeader
     *            {@code PyObject} header
     * 
     * @return {@link PyObjectBuilder}
     */
    PyObjectBuilder header(final PyBase aHeader);
  }

  /**
   * {@link PyObjectEx} builder.
   * 
   * @see {@link PyObjectEx}
   * @see {@link PyBuilder}
   */
  interface PyObjectExBuilder
      extends PyBuilder<PyObjectEx>
  {
    /**
     * Sets {@code PyObjectEx} header.
     * 
     * @param aHeader
     *            {@code PyObjectEx} header
     * 
     * @return {@link PyObjectExBuilder}
     */
    PyObjectExBuilder header(final PyBase aHeader);
  }

  /**
   * {@link PyDBRowDescriptor} builder.
   * 
   * @see {@link PyDBRowDescriptor}
   * @see {@link PyBuilder}
   */
  interface PyDBRowDescriptorBuilder
      extends PyBuilder<PyDBRowDescriptor>
  {
    /**
     * Sets {@code PyDBRowDescriptor} header.
     * 
     * @param aHeader
     *            {@code PyDBRowDescriptor} header
     * 
     * @return {@link PyDBRowDescriptorBuilder}
     */
    PyDBRowDescriptorBuilder header(final PyBase aHeader);
  }

  /**
   * {@link PyDBRow} builder.
   * 
   * @see {@link PyDBRow}
   * @see {@link PyBuilder}
   */
  interface PyDBRowBuilder
      extends PyBuilder<PyDBRow>
  {
    /**
     * Sets {@code PyDBRow} header.
     * 
     * @param aHeader
     *            {@code PyDBRow} header
     * 
     * @return {@link PyDBRowBuilder}
     */
    PyDBRowBuilder header(final PyBase aHeader);
  }

  /**
   * {@link PyCRowSet} builder.
   * 
   * @see {@link PyCRowSet}
   * @see {@link PyBuilder}
   */
  interface PyCRowSetBuilder
      extends PyBuilder<PyCRowSet>
  {
    /**
     * Sets {@code PyCRowSet} header.
     * 
     * @param aHeader
     *            {@code PyCRowSet} header
     * 
     * @return {@link PyCRowSetBuilder}
     */
    PyCRowSetBuilder header(final PyBase aHeader);
  }

  /**
   * Returns {@link PyNone}.
   * 
   * @return {@link PyNone}
   * 
   * @see {@link PyNone}
   * @see {@link PyNoneBuilder}
   */
  PyNone getPyNone();

  /**
   * Returns {@link PyNone} builder.
   * 
   * @return {@link PyNone} builder
   * 
   * @see {@link PyNone}
   * @see {@link PyNoneBuilder}
   */
  PyNoneBuilder getPyNoneBuilder();

  /**
   * Returns {@link PyBool} builder.
   * 
   * @return {@link PyBool} builder
   * 
   * @see {@link PyBool}
   * @see {@link PyBoolBuilder}
   */
  PyBoolBuilder getPyBoolBuilder();

  /**
   * Returns static {@link PyBool} {@code false}.
   * 
   * @return {@link PyBool} {@code false}
   * 
   * @see {@link PyBool}
   */
  PyBool getPyBoolFalse();

  /**
   * Returns static {@link PyBool} {@code true}.
   * 
   * @return {@link PyBool} {@code true}
   * 
   * @see {@link PyBool}
   */
  PyBool getPyBoolTrue();

  /**
   * Returns {@link PyByte} builder.
   * 
   * @return {@link PyByte} builder
   * 
   * @see {@link PyByte}
   * @see {@link PyByteBuilder}
   */
  PyByteBuilder getPyByteBuilder();

  /**
   * Returns {@link PyShort} builder.
   * 
   * @return {@link PyShort} builder
   * 
   * @see {@link PyShort}
   * @see {@link PyShortBuilder}
   */
  PyShortBuilder getPyShortBuilder();

  /**
   * Returns {@link PyInt} builder.
   * 
   * @return {@link PyInt} builder
   * 
   * @see {@link PyInt}
   * @see {@link PyIntBuilder}
   */
  PyIntBuilder getPyIntBuilder();

  /**
   * Returns {@link PyLong} builder.
   * 
   * @return {@link PyLong} builder
   * 
   * @see {@link PyLong}
   * @see {@link PyLongBuilder}
   */
  PyLongBuilder getPyLongBuilder();

  /**
   * Returns {@link PyFloat} builder.
   * 
   * @return {@link PyFloat} builder
   * 
   * @see {@link PyFloat}
   * @see {@link PyFloatBuilder}
   */
  PyFloatBuilder getPyFloatBuilder();

  /**
   * Returns {@link PyDouble} builder.
   * 
   * @return {@link PyDouble} builder
   * 
   * @see {@link PyDouble}
   * @see {@link PyDoubleBuilder}
   */
  PyDoubleBuilder getPyDoubleBuilder();

  /**
   * Returns static empty {@link PyString} builder.
   * 
   * @return static empty {@link PyString} builder
   * 
   * @see {@link PyString}
   * @see {@link PyStringBuilder}
   */
  PyString getPyString();

  /**
   * Returns {@link PyString} builder.
   * 
   * @return {@link PyString} builder
   * 
   * @see {@link PyString}
   * @see {@link PyStringBuilder}
   */
  PyStringBuilder getPyStringBuilder();

  /**
   * Returns {@link PyTuple} builder.
   * 
   * @return {@link PyTuple} builder
   * 
   * @see {@link PyTuple}
   * @see {@link PyTupleBuilder}
   */
  PyTupleBuilder getPyTupleBuilder();

  /**
   * Returns {@link PyTuple} builder.
   * 
   * @param aSize
   *            initial size
   * 
   * @return {@link PyTuple} builder
   * 
   * @see {@link PyTuple}
   * @see {@link PyTupleBuilder}
   */
  PyTupleBuilder getPyTupleBuilder(int aSize);

  /**
   * Returns {@link PyList} builder.
   * 
   * @return {@link PyList} builder
   * 
   * @see {@link PyList}
   * @see {@link PyListBuilder}
   */
  PyListBuilder getPyListBuilder();

  /**
   * Returns {@link PyList} builder.
   * 
   * @param aSize
   *            initial size
   * 
   * @return {@link PyList} builder
   * 
   * @see {@link PyList}
   * @see {@link PyListBuilder}
   */
  PyListBuilder getPyListBuilder(int aSize);

  /**
   * Returns {@link PyDict} builder.
   * 
   * @return {@link PyDict} builder
   * 
   * @see {@link PyDict}
   * @see {@link PyDictBuilder}
   */
  PyDictBuilder getPyDictBuilder();

  /**
   * Returns {@link PyObject} builder.
   * 
   * @return {@link PyObject} builder
   * 
   * @see {@link PyObject}
   * @see {@link PyObjectBuilder}
   */
  PyObjectBuilder getPyObjectBuilder();

  /**
   * Returns {@link PyObjectEx} builder.
   * 
   * @return {@link PyObjectEx} builder
   * 
   * @see {@link PyObjectEx}
   * @see {@link PyObjectExBuilder}
   */
  PyObjectExBuilder getPyObjectExBuilder();

  /**
   * Returns {@link PyDBRow} builder.
   * 
   * @return {@link PyDBRow} builder
   * 
   * @see {@link PyDBRow}
   * @see {@link PyDBRowBuilder}
   */
  PyDBRowBuilder getPyDBRowBuilder();

  /**
   * Returns {@link PyDBRowDescriptor} builder.
   * 
   * @return {@link PyDBRowDescriptor} builder
   * 
   * @see {@link PyDBRowDescriptor}
   * @see {@link PyDBRowDescriptorBuilder}
   */
  PyDBRowDescriptorBuilder getPyDBRowDescriptorBuilder();

  /**
   * Returns {@link PyCRowSet} builder.
   * 
   * @return {@link PyCRowSet} builder
   * 
   * @see {@link PyCRowSet}
   * @see {@link PyCRowSetBuilder}
   */
  PyCRowSetBuilder getPyCRowSetBuilder();
}

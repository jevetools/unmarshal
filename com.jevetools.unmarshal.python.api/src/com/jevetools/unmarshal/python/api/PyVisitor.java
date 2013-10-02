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
public interface PyVisitor
{ //NOPMD
  /**
   * Visits {@code PyBool}.
   *
   * @param pyBool
   *            object to visit
   * @since 0.0.1
   */
  void visit(final PyBool pyBool);

  /**
   * Visits {@code PyByte}.
   *
   * @param pyByte
   *            object to visit
   * @since 0.0.1
   */
  void visit(final PyByte pyByte);

  /**
   * Visits {@code PyDBRow}.
   *
   * @param pyDBRow
   *            object to visit
   * @since 0.0.1
   */
  void visit(final PyDBRow pyDBRow);

  /**
   * Visits {@code PyCRowSet}.
   *
   * @param pyCRowSet
   *            object to visit
   * @since 0.0.1
   */
  void visit(final PyCRowSet pyCRowSet);

  /**
   * Visits {@code PyDBRowDescriptor}.
   *
   * @param pyDBRowDescriptor
   *            object to visit
   * @since 0.0.1
   */
  void visit(final PyDBRowDescriptor pyDBRowDescriptor);

  /**
   * Visits {@code PyDict}.
   *
   * @param pyDict
   *            object to visit
   * @since 0.0.1
   */
  void visit(final PyDict pyDict);

  /**
   * Visits {@code PyDouble}.
   *
   * @param pyDouble
   *            object to visit
   * @since 0.0.1
   */
  void visit(final PyDouble pyDouble);

  /**
   * Visits {@code PyFloat}.
   *
   * @param pyFloat
   *            object to visit
   * @since 0.0.1
   */
  void visit(final PyFloat pyFloat);

  /**
   * Visits {@code PyInt}.
   *
   * @param pyInt
   *            object to visit
   * @since 0.0.1
   */
  void visit(final PyInt pyInt);

  /**
   * Visits {@code PyList}.
   *
   * @param pyList
   *            object to visit
   * @since 0.0.1
   */
  void visit(final PyList pyList);

  /**
   * Visits {@code PyLong}.
   *
   * @param pyLong
   *            object to visit
   * @since 0.0.1
   */
  void visit(final PyLong pyLong);

  /**
   * Visits {@code PyNone}.
   *
   * @param pyNone
   *            object to visit
   * @since 0.0.1
   */
  void visit(final PyNone pyNone);

  /**
   * Visits {@code PyObject}.
   *
   * @param pyObject
   *            object to visit
   * @since 0.0.1
   */
  void visit(final PyObject pyObject);

  /**
   * Visits {@code PyObjectEx}.
   *
   * @param pyObjectEx
   *            object to visit
   * @since 0.0.1
   */
  void visit(final PyObjectEx pyObjectEx);

  /**
   * Visits {@code PyShort}.
   *
   * @param pyShort
   *            object to visit
   * @since 0.0.1
   */
  void visit(final PyShort pyShort);

  /**
   * Visits {@code PyString}.
   *
   * @param pyString
   *            object to visit
   * @since 0.0.1
   */
  void visit(final PyString pyString);

  /**
   * Visits {@code PyTuple}.
   *
   * @param pyTuple
   *            object to visit
   * @since 0.0.1
   */
  void visit(final PyTuple pyTuple);
}

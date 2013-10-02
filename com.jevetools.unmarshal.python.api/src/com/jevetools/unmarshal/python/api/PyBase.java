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
public interface PyBase
    extends Comparable<PyBase>
{ // NOPMD
  /**
   * Types.
   * 
   * @since 0.0.1
   */
  enum PyType
  {
    /**
     * None.
     * 
     * @since 0.0.1
     */
    NONE,
    /**
     * Boolean.
     * 
     * @since 0.0.1
     */
    BOOL,
    /**
     * Byte.
     * 
     * @since 0.0.1
     */
    BYTE,
    /**
     * Short.
     * 
     * @since 0.0.1
     */
    SHORT,
    /**
     * Integer.
     * 
     * @since 0.0.1
     */
    INT,
    /**
     * Long.
     * 
     * @since 0.0.1
     */
    LONG,
    /**
     * Float.
     * 
     * @since 0.0.1
     */
    FLOAT,
    /**
     * Double.
     * 
     * @since 0.0.1
     */
    DOUBLE,
    /**
     * String.
     * 
     * @since 0.0.1
     */
    STRING,
    /**
     * Tuple.
     * 
     * @since 0.0.1
     */
    TUPLE,
    /**
     * List.
     * 
     * @since 0.0.1
     */
    LIST,
    /**
     * Dictionary.
     * 
     * @since 0.0.1
     */
    DICT,
    /**
     * Object.
     * 
     * @since 0.0.1
     */
    OBJECT,
    /**
     * ObjectEx.
     * 
     * @since 0.0.1
     */
    OBJECTEX,
    /**
     * DBRowDescriptor.
     * 
     * @since 0.0.1
     */
    DBROWDESCRIPTOR,
    /**
     * DBRow.
     * 
     * @since 0.0.1
     */
    DBROW,
    /**
     * CRowSet.
     * 
     * @since 0.0.1
     */
    CROWSET
  }

  /**
   * @param visitor
   *            visitor
   * @since 0.0.1
   */
  void accept(PyVisitor visitor);

  /**
   * @return {@link PyBool} if implementing the {@link PyBool} interface,
   *         otherwise throws {@link ClassCastException}.
   * @since 0.0.1
   */
  PyBool asBool();

  /**
   * @return {@link PyByte} if implementing the {@link PyByte} interface,
   *         otherwise throws {@link ClassCastException}.
   * @since 0.0.1
   */
  PyByte asByte();

  /**
   * @return {@link PyCRowSet} if implementing the {@link PyCRowSet}
   *         interface, otherwise throws {@link ClassCastException}.
   * @since 0.0.1
   */
  PyCRowSet asCRowSet();

  /**
   * @return {@link PyDBRow} if implementing the {@link PyDBRow} interface,
   *         otherwise throws {@link ClassCastException}.
   * @since 0.0.1
   */
  PyDBRow asDBRow();

  /**
   * @return {@link PyDBRowDescriptor} if implementing the
   *         {@link PyDBRowDescriptor} interface, otherwise throws
   *         {@link ClassCastException}.
   * @since 0.0.1
   */
  PyDBRowDescriptor asDBRowDescriptor();

  /**
   * @return {@link PyDict} if implementing the {@link PyDict} interface,
   *         otherwise throws {@link ClassCastException}.
   * @since 0.0.1
   */
  PyDict asDict();

  /**
   * @return {@link PyDouble} if implementing the {@link PyDouble} interface,
   *         otherwise throws {@link ClassCastException}.
   * @since 0.0.1
   */
  PyDouble asDouble();

  /**
   * @return {@link PyFloat} if implementing the {@link PyFloat} interface,
   *         otherwise throws {@link ClassCastException}.
   * @since 0.0.1
   */
  PyFloat asFloat();

  /**
   * @return {@link PyInt} if implementing the {@link PyInt} interface,
   *         otherwise throws {@link ClassCastException}.
   * @since 0.0.1
   */
  PyInt asInt();

  /**
   * @return {@link PyList} if implementing the {@link PyList} interface,
   *         otherwise throws {@link ClassCastException}.
   * @since 0.0.1
   */
  PyList asList();

  /**
   * @return {@link PyBaseList} if implementing the {@link PyBaseList}
   *         interface, otherwise throws {@link ClassCastException}.
   * @since 0.0.1
   */
  PyBaseList asAbstractList();

  /**
   * @return {@link PyLong} if implementing the {@link PyLong} interface,
   *         otherwise throws {@link ClassCastException}.
   * @since 0.0.1
   */
  PyLong asLong();

  /**
   * @return {@link PyNone} if implementing the {@link PyNone} interface,
   *         otherwise throws {@link ClassCastException}.
   * @since 0.0.1
   */
  PyNone asNone();

  /**
   * @return {@link PyObject} if implementing the {@link PyObject} interface,
   *         otherwise throws {@link ClassCastException}.
   * @since 0.0.1
   */
  PyObject asObject();

  /**
   * @return {@link PyObjectEx} if implementing the {@link PyObjectEx}
   *         interface, otherwise throws {@link ClassCastException}.
   * @since 0.0.1
   */
  PyObjectEx asObjectEx();

  /**
   * @return {@link PyBaseObjectEx} if implementing the
   *         {@link PyBaseObjectEx} interface, otherwise throws
   *         {@link ClassCastException}.
   * @since 0.0.1
   */
  PyBaseObjectEx asAbstractObjectEx();

  /**
   * @return {@link PyShort} if implementing the {@link PyShort} interface,
   *         otherwise throws {@link ClassCastException}.
   * @since 0.0.1
   */
  PyShort asShort();

  /**
   * @return {@link PyString} if implementing the {@link PyString} interface,
   *         otherwise throws {@link ClassCastException}.
   * @since 0.0.1
   */
  PyString asString();

  /**
   * @return {@link PyTuple} if implementing the {@link PyTuple} interface,
   *         otherwise throws {@link ClassCastException}.
   * @since 0.0.1
   */
  PyTuple asTuple();

  /**
   * @return PyType
   * @since 0.0.1
   */
  PyType getPyType();

  /**
   * @return {@code true} if implementing the {@link PyBool} interface,
   *         otherwise {@code false}
   * @since 0.0.1
   */
  boolean isBool();

  /**
   * @return {@code true} if implementing the {@link PyByte} interface,
   *         otherwise {@code false}
   * @since 0.0.1
   */
  boolean isByte();

  /**
   * @return {@code true} if implementing the {@link PyCRowSet} interface,
   *         otherwise {@code false}
   * @since 0.0.1
   */
  boolean isCRowSet();

  /**
   * @return {@code true} if implementing the {@link PyDBRow} interface,
   *         otherwise {@code false}
   * @since 0.0.1
   */
  boolean isDBRow();

  /**
   * @return {@code true} if implementing the {@link PyDBRowDescriptor}
   *         interface, otherwise {@code false}
   * @since 0.0.1
   */
  boolean isDBRowDescriptor();

  /**
   * @return {@code true} if implementing the {@link PyDict} interface,
   *         otherwise {@code false}
   * @since 0.0.1
   */
  boolean isDict();

  /**
   * @return {@code true} if implementing the {@link PyDouble} interface,
   *         otherwise {@code false}
   * @since 0.0.1
   */
  boolean isDouble();

  /**
   * @return {@code true} if implementing the {@link PyFloat} interface,
   *         otherwise {@code false}
   * @since 0.0.1
   */
  boolean isFloat();

  /**
   * @return {@code true} if implementing the {@link PyInt} interface,
   *         otherwise {@code false}
   * @since 0.0.1
   */
  boolean isInt();

  /**
   * @return {@code true} if implementing the {@link PyList} interface,
   *         otherwise {@code false}
   * @since 0.0.1
   */
  boolean isList();

  /**
   * @return {@code true} if implementing the {@link PyBaseList}
   *         interface, otherwise {@code false}
   * @since 0.0.1
   */
  boolean isAbstractList();

  /**
   * @return {@code true} if implementing the {@link PyLong} interface,
   *         otherwise {@code false}
   * @since 0.0.1
   */
  boolean isLong();

  /**
   * @return {@code true} if implementing the {@link PyNone} interface,
   *         otherwise {@code false}
   * @since 0.0.1
   */
  boolean isNone();

  /**
   * @return {@code true} if implementing the {@link PyObject} interface,
   *         otherwise {@code false}
   * @since 0.0.1
   */
  boolean isObject();

  /**
   * @return {@code true} if implementing the {@link PyObjectEx} interface,
   *         otherwise {@code false}
   * @since 0.0.1
   */
  boolean isObjectEx();

  /**
   * @return {@code true} if implementing the {@link PyBaseObjectEx}
   *         interface, otherwise {@code false}
   * @since 0.0.1
   */
  boolean isAbstractObjectEx();

  /**
   * @return {@code true} if implementing the {@link PyShort} interface,
   *         otherwise {@code false}
   * @since 0.0.1
   */
  boolean isShort();

  /**
   * @return {@code true} if implementing the {@link PyString} interface,
   *         otherwise {@code false}
   * @since 0.0.1
   */
  boolean isString();

  /**
   * @return {@code true} if implementing the {@link PyTuple} interface,
   *         otherwise {@code false}
   * @since 0.0.1
   */
  boolean isTuple();
}

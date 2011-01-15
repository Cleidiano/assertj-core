/*
 * Created on Nov 26, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.util;

import java.lang.reflect.Array;
import java.util.AbstractList;

import org.fest.util.VisibleForTesting;

/**
 * An list-like wrapper for arrays. This class does not provide type-safety in order to handle both arrays
 * of objects and arrays of primitives.
 *
 * @author Alex Ruiz
 */
public class ArrayWrapperList extends AbstractList<Object> {

  /**
   * Wraps a given array with a <code>{@link ArrayWrapperList}</code>
   * @param array the array to wrap.
   * @return the wrapped array or {@code null} if the given array was already {@code null}.
   * @throws IllegalArgumentException if the {@code array} is not an array.
   */
  public static ArrayWrapperList wrap(Object array) {
    if (array == null) return null;
    if (!array.getClass().isArray()) throw new IllegalArgumentException("The object to wrap should be an array");
    return new ArrayWrapperList(array);
  }

  private final Object array;

  @VisibleForTesting ArrayWrapperList(Object array) {
    this.array = array;
  }

  /**
   * {@inheritDoc}
   */
  @Override public Object get(int index) {
    checkIsInRange(index);
    return Array.get(array, index);
  }

  private void checkIsInRange(int index) {
    int size = size();
    if (index < size()) return;
    throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, size));
  }

  /**
   * {@inheritDoc}
   */
  @Override public int size() {
    return Array.getLength(array);
  }

  @VisibleForTesting Object array() { return array; }
}

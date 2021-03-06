/*
 * This file is part of adventure-text-minimessage, licensed under the MIT License.
 *
 * Copyright (c) 2018-2020 KyoriPowered
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.kyori.adventure.text.minimessage;

import net.kyori.adventure.text.Component;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import net.kyori.examination.string.StringExaminer;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.stream.Stream;

/**
 * A placeholder in a message, that can be either replaced by a string or a component.
 *
 * @since 4.0.0
 */
public interface Template extends Examinable {

  /**
   * Constructs a template that gets replaced with a string.
   *
   * @param key the placeholder
   * @param value the value to replace the key with
   * @return the constructed template
   * @since 4.0.0
   */
  static @NonNull Template of(final @NonNull String key, final @NonNull String value) {
    return new StringTemplate(key, value);
  }

  /**
   * Constructs a template that gets replaced with a component.
   *
   * @param key the placeholder
   * @param value the component to replace the key with
   * @return the constructed template
   * @since 4.0.0
   */
  static @NonNull Template of(final @NonNull String key, final @NonNull Component value) {
    return new ComponentTemplate(key, value);
  }

  /**
   * A template with a value that will be parsed as a MiniMessage string.
   *
   * @since 4.0.0
   */
  class StringTemplate implements Template {
    private final String key;
    private final String value;

    StringTemplate(final @NonNull String key, final @NonNull String value) {
      this.key = key;
      this.value = value;
    }

    public @NonNull String key() {
      return this.key;
    }

    public @NonNull String value() {
      return this.value;
    }

    @Override
    public final String toString() {
      return this.examine(StringExaminer.simpleEscaping());
    }

    @Override
    public @NonNull Stream<? extends ExaminableProperty> examinableProperties() {
      return Stream.of(
              ExaminableProperty.of("key", this.key),
              ExaminableProperty.of("value", this.value)
      );
    }
  }

  /**
   * A template with a {@link Component} value that will be inserted directly.
   *
   * @since 4.0.0
   */
  class ComponentTemplate implements Template {
    private final String key;
    private final Component value;

    public ComponentTemplate(final @NonNull String key, final @NonNull Component value) {
      this.key = key;
      this.value = value;
    }

    public @NonNull String key() {
      return this.key;
    }

    public @NonNull Component value() {
      return this.value;
    }

    @Override
    public final String toString() {
      return this.examine(StringExaminer.simpleEscaping());
    }

    @Override
    public @NonNull Stream<? extends ExaminableProperty> examinableProperties() {
      return Stream.of(
              ExaminableProperty.of("key", this.key),
              ExaminableProperty.of("value", this.value)
      );
    }
  }
}

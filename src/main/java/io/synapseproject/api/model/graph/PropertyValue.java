package io.synapseproject.api.model.graph;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.jspecify.annotations.NullMarked;

@NullMarked
public sealed interface PropertyValue
    permits PropertyValue.Text,
        PropertyValue.Bool,
        PropertyValue.Number,
        PropertyValue.ListValue,
        PropertyValue.MapValue {

  record Text(String value) implements PropertyValue {
    public Text {
      Objects.requireNonNull(value, "value");
    }
  }

  record Bool(boolean value) implements PropertyValue {}

  record Number(BigDecimal value) implements PropertyValue {
    public Number {
      Objects.requireNonNull(value, "value");
    }
  }

  record ListValue(List<PropertyValue> values) implements PropertyValue {
    public ListValue {
      Objects.requireNonNull(values, "values");
      values = List.copyOf(values);
    }
  }

  record MapValue(Map<String, PropertyValue> values) implements PropertyValue {
    public MapValue {
      Objects.requireNonNull(values, "values");
      values = Map.copyOf(values);
    }
  }
}

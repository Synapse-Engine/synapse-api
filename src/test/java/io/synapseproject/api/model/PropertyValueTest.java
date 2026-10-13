package io.synapseproject.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class PropertyValueTest {

  @Test
  void mapValueIgnoresLaterChanges() {
    Map<String, PropertyValue> values = new HashMap<>();
    values.put("hp", new PropertyValue.Number(BigDecimal.ONE));
    PropertyValue.MapValue value = new PropertyValue.MapValue(values);

    values.put("mp", new PropertyValue.Number(BigDecimal.TEN));

    assertEquals(1, value.values().size());
    assertFalse(value.values().containsKey("mp"));
  }

  @Test
  void listValueIgnoresLaterChanges() {
    List<PropertyValue> values = new ArrayList<>();
    values.add(new PropertyValue.Text("hello"));
    PropertyValue.ListValue value = new PropertyValue.ListValue(values);

    values.add(new PropertyValue.Text("again"));

    assertEquals(1, value.values().size());
  }

  @Test
  @SuppressWarnings("DataFlowIssue")
  void nullElementsFail() {
    Map<String, PropertyValue> values = new HashMap<>();
    values.put("hp", null);
    List<PropertyValue> items = new ArrayList<>();
    items.add(null);

    assertThrows(NullPointerException.class, () -> new PropertyValue.Text(null));
    assertThrows(NullPointerException.class, () -> new PropertyValue.Number(null));
    assertThrows(NullPointerException.class, () -> new PropertyValue.MapValue(values));
    assertThrows(NullPointerException.class, () -> new PropertyValue.ListValue(items));
  }
}

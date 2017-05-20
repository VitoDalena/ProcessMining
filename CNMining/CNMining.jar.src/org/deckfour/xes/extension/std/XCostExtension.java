/*      */ package org.deckfour.xes.extension.std;
/*      */ 
/*      */ import java.net.URI;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import org.deckfour.xes.extension.XExtension;
/*      */ import org.deckfour.xes.extension.std.cost.XCostAmount;
/*      */ import org.deckfour.xes.extension.std.cost.XCostDriver;
/*      */ import org.deckfour.xes.extension.std.cost.XCostType;
/*      */ import org.deckfour.xes.factory.XFactory;
/*      */ import org.deckfour.xes.factory.XFactoryRegistry;
/*      */ import org.deckfour.xes.info.XGlobalAttributeNameMap;
/*      */ import org.deckfour.xes.model.XAttributable;
/*      */ import org.deckfour.xes.model.XAttribute;
/*      */ import org.deckfour.xes.model.XAttributeContinuous;
/*      */ import org.deckfour.xes.model.XAttributeLiteral;
/*      */ import org.deckfour.xes.model.XAttributeMap;
/*      */ import org.deckfour.xes.model.XEvent;
/*      */ import org.deckfour.xes.model.XTrace;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class XCostExtension
/*      */   extends XExtension
/*      */ {
/*      */   private static final long serialVersionUID = -198168699309921320L;
/*   91 */   public static final URI EXTENSION_URI = URI.create("http://www.xes-standard.org/cost.xesext");
/*      */   
/*      */   public static final String KEY_TOTAL = "cost:total";
/*      */   
/*      */   public static final String KEY_CURRENCY = "cost:currency";
/*      */   
/*      */   public static final String KEY_AMOUNT = "cost:amount";
/*      */   
/*      */   public static final String KEY_DRIVER = "cost:driver";
/*      */   
/*      */   public static final String KEY_TYPE = "cost:type";
/*      */   
/*      */   public static XAttributeContinuous ATTR_TOTAL;
/*      */   
/*      */   public static XAttributeLiteral ATTR_CURRENCY;
/*      */   
/*      */   public static XAttributeContinuous ATTR_AMOUNT;
/*      */   
/*      */   public static XAttributeLiteral ATTR_DRIVER;
/*      */   
/*      */   public static XAttributeLiteral ATTR_TYPE;
/*      */   
/*  113 */   private static transient XCostExtension singleton = new XCostExtension();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static XCostExtension instance()
/*      */   {
/*  121 */     return singleton;
/*      */   }
/*      */   
/*      */   private Object readResolve() {
/*  125 */     return singleton;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private XCostExtension()
/*      */   {
/*  132 */     super("Cost", "cost", EXTENSION_URI);
/*  133 */     XFactory factory = (XFactory)XFactoryRegistry.instance().currentDefault();
/*  134 */     ATTR_TOTAL = factory.createAttributeContinuous("cost:total", 0.0D, this);
/*  135 */     ATTR_CURRENCY = factory.createAttributeLiteral("cost:currency", "__INVALID__", this);
/*      */     
/*  137 */     ATTR_AMOUNT = factory.createAttributeContinuous("cost:amount", 0.0D, this);
/*  138 */     ATTR_DRIVER = factory.createAttributeLiteral("cost:driver", "__INVALID__", this);
/*      */     
/*  140 */     ATTR_TYPE = factory.createAttributeLiteral("cost:type", "__INVALID__", this);
/*      */     
/*  142 */     this.traceAttributes.add((XAttribute)ATTR_TOTAL.clone());
/*  143 */     this.traceAttributes.add((XAttribute)ATTR_CURRENCY.clone());
/*  144 */     this.eventAttributes.add((XAttribute)ATTR_TOTAL.clone());
/*  145 */     this.eventAttributes.add((XAttribute)ATTR_CURRENCY.clone());
/*  146 */     this.eventAttributes.add((XAttribute)ATTR_AMOUNT.clone());
/*  147 */     this.eventAttributes.add((XAttribute)ATTR_DRIVER.clone());
/*  148 */     this.eventAttributes.add((XAttribute)ATTR_TYPE.clone());
/*      */     
/*  150 */     XGlobalAttributeNameMap.instance().registerMapping("EN", "cost:total", "Total Cost");
/*      */     
/*      */ 
/*  153 */     XGlobalAttributeNameMap.instance().registerMapping("EN", "cost:currency", "Currency of Cost");
/*      */     
/*      */ 
/*  156 */     XGlobalAttributeNameMap.instance().registerMapping("EN", "cost:amount", "Cost Amount");
/*      */     
/*      */ 
/*  159 */     XGlobalAttributeNameMap.instance().registerMapping("EN", "cost:driver", "Cost Driver");
/*      */     
/*      */ 
/*  162 */     XGlobalAttributeNameMap.instance().registerMapping("EN", "cost:type", "Cost Type");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Double extractTotal(XTrace trace)
/*      */   {
/*  175 */     return extractTotalPrivate(trace);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Double extractTotal(XEvent event)
/*      */   {
/*  187 */     return extractTotalPrivate(event);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Double extractTotalPrivate(XAttributable element)
/*      */   {
/*  199 */     XAttribute attribute = (XAttribute)element.getAttributes().get("cost:total");
/*  200 */     if (attribute == null) {
/*  201 */       return null;
/*      */     }
/*  203 */     return Double.valueOf(((XAttributeContinuous)attribute).getValue());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void assignTotal(XTrace trace, Double total)
/*      */   {
/*  217 */     assignTotalPrivate(trace, total);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void assignTotal(XEvent event, Double total)
/*      */   {
/*  230 */     assignTotalPrivate(event, total);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void assignTotalPrivate(XAttributable element, Double total)
/*      */   {
/*  242 */     if ((total != null) && (total.doubleValue() > 0.0D)) {
/*  243 */       XAttributeContinuous attr = (XAttributeContinuous)ATTR_TOTAL.clone();
/*      */       
/*  245 */       attr.setValue(total.doubleValue());
/*  246 */       element.getAttributes().put("cost:total", attr);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String extractCurrency(XTrace trace)
/*      */   {
/*  259 */     return extractCurrencyPrivate(trace);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String extractCurrency(XEvent event)
/*      */   {
/*  271 */     return extractCurrencyPrivate(event);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private String extractCurrencyPrivate(XAttributable element)
/*      */   {
/*  283 */     XAttribute attribute = (XAttribute)element.getAttributes().get("cost:currency");
/*  284 */     if (attribute == null) {
/*  285 */       return null;
/*      */     }
/*  287 */     return ((XAttributeLiteral)attribute).getValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void assignCurrency(XTrace trace, String currency)
/*      */   {
/*  301 */     assignCurrencyPrivate(trace, currency);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void assignCurrency(XEvent event, String currency)
/*      */   {
/*  314 */     assignCurrencyPrivate(event, currency);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void assignCurrencyPrivate(XAttributable element, String currency)
/*      */   {
/*  326 */     if ((currency != null) && (currency.trim().length() > 0)) {
/*  327 */       XAttributeLiteral attr = (XAttributeLiteral)ATTR_CURRENCY.clone();
/*  328 */       attr.setValue(currency);
/*  329 */       element.getAttributes().put("cost:currency", attr);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Double extractAmount(XAttribute attribute)
/*      */   {
/*  342 */     XAttribute attr = (XAttribute)attribute.getAttributes().get("cost:amount");
/*  343 */     if (attr == null) {
/*  344 */       return null;
/*      */     }
/*  346 */     return Double.valueOf(((XAttributeContinuous)attr).getValue());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Map<String, Double> extractAmounts(XTrace trace)
/*      */   {
/*  389 */     return XCostAmount.instance().extractValues(trace);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Map<String, Double> extractAmounts(XEvent event)
/*      */   {
/*  431 */     return XCostAmount.instance().extractValues(event);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Map<List<String>, Double> extractNestedAmounts(XTrace trace)
/*      */   {
/*  473 */     return XCostAmount.instance().extractNestedValues(trace);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Map<List<String>, Double> extractNestedAmounts(XEvent event)
/*      */   {
/*  515 */     return XCostAmount.instance().extractNestedValues(event);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void assignAmount(XAttribute attribute, Double amount)
/*      */   {
/*  528 */     if ((amount != null) && (amount.doubleValue() > 0.0D)) {
/*  529 */       XAttributeContinuous attr = (XAttributeContinuous)ATTR_AMOUNT.clone();
/*      */       
/*  531 */       attr.setValue(amount.doubleValue());
/*  532 */       attribute.getAttributes().put("cost:amount", attr);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void assignAmounts(XTrace trace, Map<String, Double> amounts)
/*      */   {
/*  571 */     XCostAmount.instance().assignValues(trace, amounts);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void assignAmounts(XEvent event, Map<String, Double> amounts)
/*      */   {
/*  609 */     XCostAmount.instance().assignValues(event, amounts);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void assignNestedAmounts(XTrace trace, Map<List<String>, Double> amounts)
/*      */   {
/*  655 */     XCostAmount.instance().assignNestedValues(trace, amounts);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void assignNestedAmounts(XEvent event, Map<List<String>, Double> amounts)
/*      */   {
/*  701 */     XCostAmount.instance().assignNestedValues(event, amounts);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String extractDriver(XAttribute attribute)
/*      */   {
/*  713 */     XAttribute attr = (XAttribute)attribute.getAttributes().get("cost:driver");
/*  714 */     if (attr == null) {
/*  715 */       return null;
/*      */     }
/*  717 */     return ((XAttributeLiteral)attr).getValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Map<String, String> extractDrivers(XTrace trace)
/*      */   {
/*  732 */     return XCostDriver.instance().extractValues(trace);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Map<String, String> extractDrivers(XEvent event)
/*      */   {
/*  746 */     return XCostDriver.instance().extractValues(event);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Map<List<String>, String> extractNestedDrivers(XTrace trace)
/*      */   {
/*  760 */     return XCostDriver.instance().extractNestedValues(trace);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Map<List<String>, String> extractNestedDrivers(XEvent event)
/*      */   {
/*  774 */     return XCostDriver.instance().extractNestedValues(event);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void assignDriver(XAttribute attribute, String driver)
/*      */   {
/*  787 */     if ((driver != null) && (driver.trim().length() > 0)) {
/*  788 */       XAttributeLiteral attr = (XAttributeLiteral)ATTR_DRIVER.clone();
/*  789 */       attr.setValue(driver);
/*  790 */       attribute.getAttributes().put("cost:driver", attr);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void assignDrivers(XTrace trace, Map<String, String> drivers)
/*      */   {
/*  807 */     XCostDriver.instance().assignValues(trace, drivers);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void assignDrivers(XEvent event, Map<String, String> drivers)
/*      */   {
/*  823 */     XCostDriver.instance().assignValues(event, drivers);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void assignNestedDrivers(XTrace trace, Map<List<String>, String> drivers)
/*      */   {
/*  843 */     XCostDriver.instance().assignNestedValues(trace, drivers);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void assignNestedDrivers(XEvent event, Map<List<String>, String> drivers)
/*      */   {
/*  863 */     XCostDriver.instance().assignNestedValues(event, drivers);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String extractType(XAttribute attribute)
/*      */   {
/*  875 */     XAttribute attr = (XAttribute)attribute.getAttributes().get("cost:type");
/*  876 */     if (attr == null) {
/*  877 */       return null;
/*      */     }
/*  879 */     return ((XAttributeLiteral)attr).getValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Map<String, String> extractTypes(XTrace trace)
/*      */   {
/*  894 */     return XCostType.instance().extractValues(trace);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Map<String, String> extractTypes(XEvent event)
/*      */   {
/*  908 */     return XCostType.instance().extractValues(event);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Map<List<String>, String> extractNestedTypes(XTrace trace)
/*      */   {
/*  922 */     return XCostType.instance().extractNestedValues(trace);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Map<List<String>, String> extractNestedTypes(XEvent event)
/*      */   {
/*  936 */     return XCostType.instance().extractNestedValues(event);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void assignType(XAttribute attribute, String type)
/*      */   {
/*  949 */     if ((type != null) && (type.trim().length() > 0)) {
/*  950 */       XAttributeLiteral attr = (XAttributeLiteral)ATTR_TYPE.clone();
/*  951 */       attr.setValue(type);
/*  952 */       attribute.getAttributes().put("cost:type", attr);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void assignTypes(XTrace trace, Map<List<String>, String> types)
/*      */   {
/*  970 */     XCostType.instance().assignNestedValues(trace, types);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void assignTypes(XEvent event, Map<List<String>, String> types)
/*      */   {
/*  987 */     XCostType.instance().assignNestedValues(event, types);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void assignNestedTypes(XTrace trace, Map<List<String>, String> types)
/*      */   {
/* 1005 */     XCostType.instance().assignNestedValues(trace, types);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void assignNestedTypes(XEvent event, Map<List<String>, String> types)
/*      */   {
/* 1023 */     XCostType.instance().assignNestedValues(event, types);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/extension/std/XCostExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
# JSON使用工具类

## fastjson List转JSONArray以及JSONArray转List



```
1.fastjson  List转JSONArray
List<T> list = new ArrayList<T>();
JSONArray array= JSONArray.parseArray(JSON.toJSONString(list))；


2.fastjson  JSONArray转List
JSONArray array = new JSONArray();
List<EventColAttr> list = JSONObject.parseArray(array.toJSONString(), EventColAttr.class);


3.fastjson  字符串转List
String str = "";
List<T> list = JSONObject.parseArray(str,T.class);
```



```
SerializerFeature属性的枚举类如下：
值	说明
QuoteFieldNames	输出key时是否使用双引号,默认为true
WriteMapNullValue	是否输出值为null的字段,默认为false
WriteNullNumberAsZero	数值字段如果为null,输出为0,而非null
WriteNullListAsEmpty	List字段如果为null,输出为[],而非null
WriteNullStringAsEmpty	字符类型字段如果为null,输出为"",而非null
WriteNullBooleanAsFalse	Boolean字段如果为null,输出为false,而非null

JSON.toJSONString(stList, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullListAsEmpty)
```


package com.kaltsit.ascept;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NullDictSerializer extends StdSerializer<Object> implements ContextualSerializer {
    private transient String dictCode;

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty beanProperty) {
        Dict dict = beanProperty.getAnnotation(Dict.class);
        return createContextual(dict.code());
    }

    private JsonSerializer<?> createContextual(String dicCode) {
        NullDictSerializer serializer = new NullDictSerializer();
        serializer.setDictCode(dicCode);
        return serializer;
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) {
        try {
            gen.writeObject(value);
            // 在需要转换的字段上添加@Dict注解，注明需要引用的code，后端会在返回值中增加filedName_dict的key，前端只需要取对应的filedName_dict就可以直接使用
            gen.writeFieldName(gen.getOutputContext().getCurrentName() + "_dict");
            gen.writeObject(null);
        } catch (Exception e) {
            log.error("字典翻译失败:{}", e.getMessage(), e);
        }
    }

    private String getDictCode() {
        return dictCode;
    }

    private void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    protected NullDictSerializer() {
        super(Object.class);
    }
}

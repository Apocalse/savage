package com.kaltsit.ascept;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.kaltsit.ascept.annotation.Dict;
import com.kaltsit.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

@Slf4j
public class DictSerializer extends StdSerializer<Object> implements ContextualSerializer {
    private transient String dictCode;

    private transient String dictType;

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty beanProperty) {
        Dict dict = beanProperty.getAnnotation(Dict.class);
        return createContextual(dict.code(), dict.type());
    }

    private JsonSerializer<?> createContextual(String dictCode, String dictType) {
        DictSerializer serializer = new DictSerializer();
        serializer.setDictCode(dictCode);
        serializer.setDictType(dictType);
        return serializer;
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) {
        String dictCode = getDictCode();
        String dictType = getDictType();
        if (StringUtils.isBlank(dictCode)) {
            return;
        }
        if (Objects.isNull(value)) {
            return;
        }
        try {
            // 字典code
            String codeStr = value.toString();
            Map<Object, Object> dictMap = translateDictValue(dictCode);
            String label = null;
            if(dictMap != null){
                if("1".equals(dictType)){ //单个翻译
                    label = (String) dictMap.get(codeStr);
                }else if("2".equals(dictType)){ //多个翻译，逗号拼接
                    String[] list = codeStr.split(",");
                    StringBuilder sb = new StringBuilder();
                    for (String str: list) {
                        String val = dictMap.get(str) == null ? "null" : (String) dictMap.get(str);
                        sb.append(val).append(",");
                    }
                    label = sb.deleteCharAt(sb.length() - 1).toString();
                }
            }
            gen.writeObject(codeStr);
            // 在需要转换的字段上添加@Dict注解，注明需要引用的code，后端会在返回值中增加filedName_dict的key，前端只需要取对应的filedName_dict就可以直接使用
            gen.writeFieldName(gen.getOutputContext().getCurrentName() + "_dict");
            gen.writeObject(label);
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

    private String getDictType() {
        return dictType;
    }

    private void setDictType(String dictType) {
        this.dictType = dictType;
    }

    protected DictSerializer() {
        super(Object.class);
    }

    private Map<Object, Object> translateDictValue(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        return RedisUtil.getHashEntries(code);
    }
}

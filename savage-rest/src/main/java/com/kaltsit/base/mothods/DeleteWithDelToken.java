package com.kaltsit.base.mothods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public class DeleteWithDelToken extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = "";
        if (tableInfo.isWithLogicDelete()) {
            // 逻辑删除、del_token 字段是为了解决unique无效添加的、当删除时设置为主键的值
            // SQL: SET del_token = #{ID}, del_flag = 1 where ID=#{ID} and del_flag = 0
            sql = "UPDATE %s %s WHERE %s=#{%s} %s";
            String sqlSet = "SET del_token = #{" + tableInfo.getKeyProperty() + "}," + tableInfo.getLogicDeleteSql(false, false);
            sql = String.format(sql, tableInfo.getTableName(), sqlSet, tableInfo.getKeyColumn(), tableInfo.getKeyProperty(), tableInfo.getLogicDeleteSql(true, true));
        } else {
            //物理删除
            // SQL: DELETE FROM %s WHERE %s=#{%s}
            sql = "DELETE FROM %s WHERE %s=#{%s}";
            sql = String.format(sql, tableInfo.getTableName(), tableInfo.getKeyColumn(), tableInfo.getKeyProperty());
        }
        String method = "deleteWithDelToken";
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, modelClass);
        return this.addUpdateMappedStatement(mapperClass, modelClass, method, sqlSource);
    }

}
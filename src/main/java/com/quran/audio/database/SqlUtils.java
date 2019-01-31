package com.quran.audio.database;


/**
 * 帮助生成Sql语句
 */
public class SqlUtils {

    /**
     * 添加列
     * @param builder 被添加的String容器
     * @param column    被添加的列
     * @return 添加之后的String容器
     */
    public static StringBuilder appendColumn(StringBuilder builder, String column) {
        builder.append('\'').append(column).append('\'');
        return builder;
    }

    /**
     * 添加列
     * @param builder 被添加的String容器
     * @param tableAlias 表别名
     * @param column 被添加的列
     * @return 添加之后的String容器
     */
    public static StringBuilder appendColumn(StringBuilder builder, String tableAlias, String column) {
        builder.append(tableAlias).append(".'").append(column).append('\'');
        return builder;
    }

    /**
     * 添加列
     * @param builder 被添加的String容器
     * @param tableAlias 表别名
     * @param columns 被添加的列
     * @return 添加之后的String容器
     */
    public static StringBuilder appendColumns(StringBuilder builder, String tableAlias, String[] columns) {
        int length = columns.length;
        for (int i = 0; i < length; i++) {
            appendColumn(builder, tableAlias, columns[i]);
            if (i < length - 1) {
                builder.append(',');
            }
        }
        return builder;
    }

    /**
     * 添加列
     * @param builder 被添加的String容器
     * @param columns 被添加的列
     * @return 添加之后的String容器
     */
    public static StringBuilder appendColumns(StringBuilder builder, String[] columns) {
        int length = columns.length;
        for (int i = 0; i < length; i++) {
            builder.append('\'').append(columns[i]).append('\'');
            if (i < length - 1) {
                builder.append(',');
            }
        }
        return builder;
    }

    /**
     * 创建列
     * @param builder 被添加的String容器
     * @param columns 被添加的列
     * @return 添加之后的String容器
     */
    public static StringBuilder appendCreateColumns(StringBuilder builder, String[] columns) {
        int length = columns.length;
        for (int i = 0; i < length; i++) {
            builder.append(columns[i]);
            if (i < length - 1) {
                builder.append(',');
            }
        }
        return builder;
    }

    /**
     * 添加占位符语句
     * @param builder 被添加的String容器
     * @param count 占位符（？）
     * @return 添加之后的String容器
     */
    public static StringBuilder appendPlaceholders(StringBuilder builder, int count) {
        for (int i = 0; i < count; i++) {
            if (i < count - 1) {
                builder.append("?,");
            } else {
                builder.append('?');
            }
        }
        return builder;
    }

    /**
     * 添加添加语句
     * @param builder 被添加的String容器
     * @param columns 条件列
     * @return 添加之后的String容器
     */
    public static StringBuilder appendColumnsEqualPlaceholders(StringBuilder builder, String[] columns) {
        for (int i = 0; i < columns.length; i++) {
            appendColumn(builder, columns[i]).append("=?");
            if (i < columns.length - 1) {
                builder.append(',');
            }
        }
        return builder;
    }

    /**
     * 添加添加语句
     * @param builder 被添加的String容器
     * @param tableAlias 表别名
     * @param columns 条件列
     * @return 添加之后的String容器
     */
    public static StringBuilder appendColumnsEqValue(StringBuilder builder, String tableAlias, String[] columns) {
        for (int i = 0; i < columns.length; i++) {
            appendColumn(builder, tableAlias, columns[i]).append("=?");
            if (i < columns.length - 1) {
                builder.append(',');
            }
        }
        return builder;
    }

    /**
     * 创建插入的Sql语句
     * @param tableName 表名
     * @param columns 列名
     * @return Sql语句
     */
    public static String createSqlInsert(String tableName, String[] columns) {
        StringBuilder builder = new StringBuilder("INSERT INTO ");
        builder.append(tableName).append(" (");
        appendColumns(builder, columns);
        builder.append(") VALUES (");
        appendPlaceholders(builder, columns.length);
        builder.append(')');
        return builder.toString();
    }

    /**
     * 创建插入的Sql语句
     * @param insertInto into语句
     * @param tableName 表名
     * @param columns 列名
     * @return Sql语句
     */
    public static String createSqlInsert(String insertInto, String tableName, String[] columns) {
        StringBuilder builder = new StringBuilder(insertInto);
        builder.append(tableName).append(" (");
        appendColumns(builder, columns);
        builder.append(") VALUES (");
        appendPlaceholders(builder, columns.length);
        builder.append(')');
        return builder.toString();
    }

    /**
     * 创建查询的Sql语句
     * @param tableName 表名
     * @param columns 列名
     * @return Sql语句
     */
    public static String createSqlSelect(String tableName, String[] columns) {
        StringBuilder builder = new StringBuilder("SELECT ");
        SqlUtils.appendColumns(builder, columns).append(" FROM ");
        builder.append(tableName);
        return builder.toString();
    }

    /** Creates an select for given columns with a trailing space */
    public static String createSqlSelect(String tableName, String tableAlias, String[] columns) {
        StringBuilder builder = new StringBuilder("SELECT ");
        if (tableAlias == null || tableAlias.length() < 0) {
            throw new RuntimeException("Table alias required");
        }

        SqlUtils.appendColumns(builder, tableAlias, columns).append(" FROM ");
        builder.append(tableName).append(' ').append(tableAlias).append(' ');
        return builder.toString();
    }

    /**
     *  创建求和的Sql语句
     * @param tableName 表名
     * @param tableAliasOrNull 别名
     * @return Sql语句
     */
    public static String createSqlSelectCountStar(String tableName, String tableAliasOrNull) {
        StringBuilder builder = new StringBuilder("SELECT COUNT(*) FROM ");
        builder.append(tableName).append(' ');
        if(tableAliasOrNull != null) {
            builder.append(tableAliasOrNull).append(' ');
        }
        return builder.toString();
    }

    /**
     * 创建删除的Sql语句
     * @param tableName 表名
     * @param columns 列名
     * @return  Sql语句
     */
    public static String createSqlDelete(String tableName, String[] columns) {
        StringBuilder builder = new StringBuilder("DELETE FROM ");
        builder.append(tableName);
        if (columns != null && columns.length > 0) {
            builder.append(" WHERE ");
            appendColumnsEqValue(builder, tableName, columns);
        }
        return builder.toString();
    }

    /**
     * 创建更新的Sql语句
     * @param tableName 表名
     * @param updateColumns 升级的列名
     * @param whereColumns  条件的列名
     * @return  Sql语句
     */
    public static String createSqlUpdate(String tableName, String[] updateColumns, String[] whereColumns) {
        StringBuilder builder = new StringBuilder("UPDATE ");
        builder.append(tableName).append(" SET ");
        appendColumnsEqualPlaceholders(builder, updateColumns);
        builder.append(" WHERE ");
        appendColumnsEqValue(builder, tableName, whereColumns);
        return builder.toString();
    }
    /**
     * 创建建表的Sql语句
     * @param tableName 表名
     * @param columns 列名
     * @return  Sql语句
     */
    public static String createSqlCreate(String tableName, String[] columns) {
        StringBuilder builder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        builder.append(tableName).append(" ( ");
        appendCreateColumns(builder, columns);
        builder.append(" )");
        return builder.toString();
    }
}

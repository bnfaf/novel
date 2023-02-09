package com.java2nb.novel.userr.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class BookContent4DynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final BookContent4 bookContent4 = new BookContent4();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = bookContent4.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> indexId = bookContent4.indexId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> content = bookContent4.content;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class BookContent4 extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> indexId = column("index_id", JDBCType.BIGINT);

        public final SqlColumn<String> content = column("content", JDBCType.LONGVARCHAR);

        public BookContent4() {
            super("book_content4");
        }
    }
}
package com.java2nb.novel.userr.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class BookContent5DynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final BookContent5 bookContent5 = new BookContent5();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = bookContent5.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> indexId = bookContent5.indexId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> content = bookContent5.content;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class BookContent5 extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> indexId = column("index_id", JDBCType.BIGINT);

        public final SqlColumn<String> content = column("content", JDBCType.LONGVARCHAR);

        public BookContent5() {
            super("book_content5");
        }
    }
}
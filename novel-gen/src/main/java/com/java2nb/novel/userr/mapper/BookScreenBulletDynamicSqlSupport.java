package com.java2nb.novel.userr.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class BookScreenBulletDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final BookScreenBullet bookScreenBullet = new BookScreenBullet();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = bookScreenBullet.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> contentId = bookScreenBullet.contentId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> screenBullet = bookScreenBullet.screenBullet;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = bookScreenBullet.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class BookScreenBullet extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> contentId = column("content_id", JDBCType.BIGINT);

        public final SqlColumn<String> screenBullet = column("screen_bullet", JDBCType.VARCHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public BookScreenBullet() {
            super("book_screen_bullet");
        }
    }
}
package com.wax.blogsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.domain.Blog;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BlogMapper extends BaseMapper<Blog> {

    @Select("SELECT sys_blog.*,user.pic_url `authorPicUrl` FROM sys_blog,user " +
            "WHERE sys_blog.author=user.id and sys_blog.del_tag='1'" +
            " and sys_blog.id = #{id} ")
    Blog selectById(@Param("id") String id);

    @Select("SELECT sys_blog.*,user.pic_url `authorPicUrl` FROM sys_blog,user " +
            "WHERE sys_blog.author=user.id and sys_blog.del_tag='1' and sys_blog.status = 'released'" +
            "order by sys_blog.release_time desc")
    IPage<Blog> selectAll(Page<Blog> page);


    @Select("select * from sys_blog where id in (\n" +
                "select tt.id from (\n" +
                    "select blog_id \"id\" from sys_blog_view \n" +
                    "WHERE (TIME_TO_SEC(TIMEDIFF(SYSDATE(),sys_blog_view.create_time)))/3600<=48 \n" +
                    "GROUP BY blog_id order by count(sys_blog_view.id) desc LIMIT 10 ) tt\n" +
                    "\n" +
            ")")
    IPage<Blog> get48HoursViewBlogList(Page<Blog> page);


    @Select("select sys_blog.*,user.pic_url `authorPicUrl` from sys_blog,user where sys_blog.author=user.id and sys_blog.del_tag='1' and sys_blog.status = 'released'" +
            "and sys_blog.id in (" +
                        "select tt.blog_id from" +
                        "(" +
                            "select DISTINCT(blog_id) from sys_comment where sender = #{userId} " +
                        ") tt"+
            ")")
    IPage<Blog> getMyCommentList(Page<Blog> page,@Param("userId")String userId);

    @Select("select sys_blog.*,user.pic_url `authorPicUrl` from sys_blog,user where sys_blog.author=user.id and sys_blog.del_tag='1' and sys_blog.status = 'released'" +
            "and sys_blog.id in (" +
            "select tt.blog_id from" +
            "(" +
            "select blog_id from sys_blog_like where user_id = #{userId} " +
            ") tt"+
            ")")
    IPage<Blog> getILikeList(Page<Blog> page,@Param("userId")String userId);

    @Select("select sys_blog.*,user.pic_url `authorPicUrl` from sys_blog,user where sys_blog.author=user.id and sys_blog.del_tag='1' and sys_blog.status = 'released'" +
            "and sys_blog.id in (" +
            "select tt.blog_id from" +
            "(" +
            "select blog_id from sys_blog_favorite where user_id = #{userId} " +
            ") tt"+
            ")")
    IPage<Blog> getMyFavoriteList(Page<Blog> page,@Param("userId")String userId);


    @Select("SELECT sys_blog.*,user.pic_url `authorPicUrl` FROM sys_blog,user " +
            "WHERE sys_blog.author=user.id and sys_blog.del_tag='1' and sys_blog.status = 'released'" +
            "and sys_blog.category = #{category} order by sys_blog.release_time desc")
    IPage<Blog> getBlogByCategory(Page<Blog> page,@Param("category")String category);

    @Select("SELECT sys_blog.*,user.pic_url `authorPicUrl` FROM sys_blog,user " +
            "WHERE sys_blog.author=user.id and sys_blog.del_tag='1' and sys_blog.status = 'released'" +
            "and sys_blog.is_recommend = '1' order by sys_blog.release_time desc")
    IPage<Blog> getEditorRecommendBlog(Page<Blog> page);

}

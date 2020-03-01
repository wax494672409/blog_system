package com.wax.blogsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.domain.Blog;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BlogMapper extends BaseMapper<Blog> {

    @Select("SELECT sys_blog.*,user.pic_url `authorPicUrl` FROM sys_blog,user " +
            "WHERE sys_blog.author=user.id and sys_blog.del_tag='1' and sys_blog.status = 'released'" +
            "order by sys_blog.release_time desc")
    IPage<Blog> selectAll(Page<Blog> page);


}

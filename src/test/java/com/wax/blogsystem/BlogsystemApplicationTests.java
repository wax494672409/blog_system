package com.wax.blogsystem;

import com.wax.blogsystem.domain.Blog;
import com.wax.blogsystem.mapper.BlogMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogsystemApplicationTests {

    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Blog> userList = blogMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }


    @Test
    public void contextLoads() {
    }

}

package cn.yun.mapper;

import cn.yun.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CourseMapper {

    ArrayList<Course> selectByStudent(Integer id);

}

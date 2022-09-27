package cn.yun.mapper;

import cn.yun.entity.CourseInfo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CourseInfoMapper {

    ArrayList<CourseInfo> selectByCourseId(Integer id);

}

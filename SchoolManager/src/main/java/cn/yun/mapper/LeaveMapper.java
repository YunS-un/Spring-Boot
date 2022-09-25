package cn.yun.mapper;

import cn.yun.entity.Leave;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveMapper {

    List<Leave> selectLeave(Integer studentId);

    int deleteOne(Integer id);

    Leave selectOne(Integer id);

    boolean updateOne(Leave leave);

    boolean insertOne(Leave leave);

}

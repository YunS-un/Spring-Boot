package cn.yun.mapper;

import cn.yun.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {

    Student selectByCourse(String email);

    Student selectByClazz(Integer id);

    int updateOne(Student student);

    boolean insertAward(String info,Integer studentId,Integer awardId);

    boolean insertPunishment(Integer punishmentId,String info);

    List<Punishment> selectPunishmentById(Integer id);

    List<PunishmentInfo> selectPunishmentInfoById(Integer id);

    List<Award> selectByStudentId(Integer id);

    List<Punishment> selectPunishmentByStudentId(Integer id);

    List<AwardInfo> selectByAwardId(Integer id);

    List<AwardInfo> selectAll();

}

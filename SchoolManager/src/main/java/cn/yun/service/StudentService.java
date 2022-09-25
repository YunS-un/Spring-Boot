package cn.yun.service;

import cn.yun.entity.Award;
import cn.yun.entity.AwardInfo;
import cn.yun.entity.Punishment;
import cn.yun.entity.Student;

import java.util.HashMap;
import java.util.List;

public interface StudentService {

    Student getByCourse(String email);

    List<AwardInfo> getAwardInfoAll();

    List<AwardInfo> getAwardInfoByName(String name);

    List<Award> getByStudentId(Integer id);

    List<HashMap> getMyAward(Integer id);

    List<Punishment> getPunishmentByStudentId(Integer id);

    List<Punishment> getPunishmentById(Integer id);

    List<HashMap> getApplyPunishment(Integer id);

    boolean addAward(String info,Integer studentId,Integer awardId);

    boolean addPunishment(Integer punishmentId,String info);

    int modOne(Student student);

}

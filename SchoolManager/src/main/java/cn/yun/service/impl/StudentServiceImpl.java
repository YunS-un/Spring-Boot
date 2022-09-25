package cn.yun.service.impl;

import cn.yun.entity.*;
import cn.yun.mapper.StudentMapper;
import cn.yun.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student getByCourse(String email) {
        return studentMapper.selectByCourse(email);
    }

    @Override
    public List<AwardInfo> getAwardInfoAll() {
        return studentMapper.selectAll();
    }

    @Override
    public List<AwardInfo> getAwardInfoByName(String name) {
        List<AwardInfo> awardInfoAll = getAwardInfoAll();
        List<AwardInfo> list = new ArrayList<>();
        for (AwardInfo a: awardInfoAll) {
            if (a.getName().contains(name)){
                list.add(a);
            }
        }
        return list;
    }

    @Override
    public List<Award> getByStudentId(Integer id) {
        return studentMapper.selectByStudentId(id);
    }

    @Override
    public List<HashMap> getMyAward(Integer id) {
        List<Award> byStudentId = getByStudentId(id);
        List<HashMap> list = new ArrayList<>();
        for (Award a:byStudentId) {
            HashMap hashMap = new HashMap();
            List<AwardInfo> awardInfos = a.getAwardInfos();
            AwardInfo awardInfo = awardInfos.get(0);
            hashMap.put("id",a.getId());
            hashMap.put("info",a.getInfo());
            hashMap.put("status",a.getStatus());
            hashMap.put("name",awardInfo.getName());
            hashMap.put("money",awardInfo.getMoney());
            hashMap.put("name",awardInfo.getName());
            list.add(hashMap);
        }
        return list;
    }

    @Override
    public List<Punishment> getPunishmentByStudentId(Integer id) {
        return studentMapper.selectPunishmentByStudentId(id);
    }

    @Override
    public List<Punishment> getPunishmentById(Integer id) {
        return studentMapper.selectPunishmentById(id);
    }

    @Override
    public List<HashMap> getApplyPunishment(Integer id) {
        List<Punishment> punishmentById = getPunishmentById(id);
        List<HashMap> list = new ArrayList<>();
        for (Punishment p: punishmentById) {
            HashMap hashMap = new HashMap();
            List<PunishmentInfo> punishmentInfosList = p.getList();
            if (!punishmentInfosList.isEmpty()){
                PunishmentInfo punishmentInfo = punishmentInfosList.get(0);
                hashMap.put("id",punishmentInfo.getId());
                hashMap.put("info",punishmentInfo.getInfo());
                hashMap.put("status",punishmentInfo.getStatus());
                hashMap.put("name",p.getName());
                hashMap.put("punishmentTime",p.getPunishmentTime());
                list.add(hashMap);
            }
        }
        return list;
    }

    @Override
    public boolean addAward(String info, Integer studentId, Integer awardId) {
        return studentMapper.insertAward(info,studentId,awardId);
    }

    @Override
    public boolean addPunishment(Integer punishmentId, String info) {
        return studentMapper.insertPunishment(punishmentId,info);
    }


//    @Override
//    public Student getByEmail(String email) {
//        return studentMapper.selectOne(email);
//    }

    @Override
    public int modOne(Student student) {
        return studentMapper.updateOne(student);
    }
}

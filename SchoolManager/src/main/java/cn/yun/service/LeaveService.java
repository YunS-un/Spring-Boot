package cn.yun.service;

import cn.yun.entity.Leave;

import java.util.List;

public interface LeaveService {

    List<Leave> getLeave(Integer studentId);

    int removeOne(Integer id);

    Leave getOne(Integer id);

    boolean modOne(Leave leave);

    boolean addOne(Leave leave);

}

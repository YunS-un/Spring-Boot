package cn.yun.service.impl;

import cn.yun.entity.Leave;
import cn.yun.mapper.LeaveMapper;
import cn.yun.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveMapper leaveMapper;

    @Override
    public List<Leave> getLeave(Integer studentId) {
        return leaveMapper.selectLeave(studentId);
    }

    @Override
    public int removeOne(Integer id) {
        return leaveMapper.deleteOne(id);
    }

    @Override
    public Leave getOne(Integer id) {
        return leaveMapper.selectOne(id);
    }

    @Override
    public boolean modOne(Leave leave) {
        return leaveMapper.updateOne(leave);
    }

    @Override
    public boolean addOne(Leave leave) {
        return leaveMapper.insertOne(leave);
    }
}

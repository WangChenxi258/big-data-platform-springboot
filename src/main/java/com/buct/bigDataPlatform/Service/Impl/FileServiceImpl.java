package com.buct.bigDataPlatform.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buct.bigDataPlatform.Mapper.ArticleMapper;
import com.buct.bigDataPlatform.Mapper.DataMapper;
import com.buct.bigDataPlatform.Pojo.Data;
import com.buct.bigDataPlatform.Pojo.User;
import com.buct.bigDataPlatform.Service.FileService;
import com.buct.bigDataPlatform.Util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private DataMapper dataMapper;
    @Override
    public void add(String fileName, String s) {
        Data data=new Data();
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        data.setCreateuser(id);
        data.setName(fileName);
        data.setDir(s);
        System.out.println("where");
        dataMapper.insert(data);
    }

    @Override
    public String getById(String id) {
        QueryWrapper<Data> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("id",id);
        Data data=dataMapper.selectOne(queryWrapper);
        return data.getDir();
    }
}

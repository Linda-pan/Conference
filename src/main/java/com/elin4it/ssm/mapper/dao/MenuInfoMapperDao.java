package com.elin4it.ssm.mapper.dao;

import com.elin4it.ssm.mapper.MenuInfoMapper;
import com.elin4it.ssm.pojo.MenuInfo;
import org.apache.http.entity.mime.MIME;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jpan on 2016/5/4.
 */

@Repository
public interface MenuInfoMapperDao extends MenuInfoMapper {
    List<MenuInfo> selectByLevel(int level);
}

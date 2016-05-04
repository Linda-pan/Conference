package com.elin4it.ssm.service;

import com.elin4it.ssm.mapper.dao.CommentMapperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jpan on 2016/5/4.
 */
@Service
public class ConferenceService {
    @Autowired
    private CommentMapperDao commentMapperDao;


}

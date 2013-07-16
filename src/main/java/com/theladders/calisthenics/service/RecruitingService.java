package com.theladders.calisthenics.service;

import com.theladders.calisthenics.domain.Job;
import com.theladders.calisthenics.domain.Recruiter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lamigud
 * Date: 7/15/13
 * Time: 6:00 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RecruitingService {

    public boolean post(List<Job> jobs);
    public List<Job> list(Recruiter recruiter);

}

package com.theladders.calisthenics.domain;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/15/13
 * Time: 5:23 PM
 */
public class JobSeeker {

    private Resumes resumes = new Resumes();

    public boolean addResume(Resume resume) {
        return resumes.add(resume);
    }

    public boolean isOwner(Resume resume) {
        return resumes.contains(resume);
    }


}

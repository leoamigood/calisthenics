package com.theladders.calisthenics.resume;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 12:53 PM
 */
public class Resumes {

    private Set<Resume> resumes = new LinkedHashSet<>();

    public boolean add(Resume resume) {
        return resumes.add(resume);
    }

    public boolean contains(Resume resume) {
        return resumes.contains(resume);
    }
}

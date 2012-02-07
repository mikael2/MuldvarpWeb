/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarp.web;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import no.hials.muldvarp.web.domain.Course;
import no.hials.muldvarp.web.service.CourseService;

/**
 *
 * @author kristoffer
 */
@Named
@SessionScoped
public class CourseController implements Serializable {
    @Inject CourseService service;
    Course newCourse;
    List<Course> courses;
    Course selected;
    Course filter;
}

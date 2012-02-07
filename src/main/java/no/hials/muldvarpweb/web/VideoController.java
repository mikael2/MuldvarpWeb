/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.web;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import no.hials.muldvarpweb.domain.Video;
import no.hials.muldvarpweb.service.VideoService;

/**
 *
 * @author johan
 */
@Named
@SessionScoped
public class VideoController {
    @Inject VideoService videoService;
    Video newVideo;
    List<Video> videoList;
    Video selectedVideo;
    Video filter;
}

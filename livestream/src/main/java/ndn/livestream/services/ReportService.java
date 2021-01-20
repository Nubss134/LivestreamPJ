package ndn.livestream.services;

import ndn.livestream.entities.ReportEntity;
import ndn.livestream.entities.VideoEntity;
import ndn.livestream.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private VideoService videoService;

    public void report(ReportEntity reportEntity) {
        reportEntity.setCreatedTime(new Date());
        reportEntity.setUpdatedTime(new Date());
        reportRepository.save(reportEntity);
    }


    public ReportEntity findById(Long id) {
        ReportEntity entity = reportRepository.findById(id).orElse(new ReportEntity());
        VideoEntity videoEntity = videoService.detailVideo(videoService.findById(entity.getVideoId()));
        entity.setVideoEntity(videoEntity);
        return entity;
    }

    public void delete(Long id) {
        reportRepository.deleteById(id);
    }

    public List<ReportEntity> getAll() {
        List<ReportEntity> list = reportRepository.findAll();
//        list.forEach(report -> {
//            VideoEntity videoEntity = videoService.detailVideo(videoService.findById(report.getVideoId()));
//            report.setVideoEntity(videoEntity);
//        });
        return list;
    }



}
